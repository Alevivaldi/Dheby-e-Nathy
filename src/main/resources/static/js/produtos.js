async function buscarProdutos() {
    try {
        const response = await fetch('http://localhost:8080/produtos/listar');
        if (!response.ok) {
            throw new Error('Erro ao buscar produtos');
        }
        const produtos = await response.json();
        injetarProdutos(produtos);
    } catch (error) {
        console.error(error);
    }
}

function criarProduto(produto) {
    const divProduto = document.createElement('div');
    divProduto.classList.add('produto');

    divProduto.innerHTML = `
        <img src="${produto.imagem}" alt="${produto.nome}">
        <h3>${produto.nome}</h3>
        <p>${produto.descricao}</p>
        <p class="preco">${produto.valor ? `R$ ${produto.valor.toFixed(2)}` : 'Preço indisponível'}</p>
        <div class="botoes">
            <a href="#" class="botao-compra" data-produto='${JSON.stringify(produto)}'>Comprar</a>
            <a href="${produto.linkEncomenda}" class="botao-encomenda">Encomendar</a>
        </div>
    `;

    return divProduto;
}

function injetarProdutos(produtos) {
    const container = document.getElementById('produtos-container');
    produtos.forEach(produto => {
        const produtoElement = criarProduto(produto);
        container.appendChild(produtoElement);
    });
}

function armazenarNoLocalStorage(produto) {
    const produtosNoLocalStorage = JSON.parse(localStorage.getItem('produtos')) || [];
    produtosNoLocalStorage.push(produto);
    localStorage.setItem('produtos', JSON.stringify(produtosNoLocalStorage));
}

async function gerarPDF(produto) {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();


    const img = new Image();
    img.src = produto.imagem;
    img.onload = function() {
        doc.addImage(img, 'JPEG', 10, 10, 50, 50);
        doc.text(`Produto: ${produto.nome}`, 10, 70);
        doc.text(`Descrição: ${produto.descricao}`, 10, 80);
        doc.text(`Preço: R$ ${produto.valor.toFixed(2)}`, 10, 90);


        doc.save(`${produto.nome}.pdf`);
    };
}

function redirecionarWhatsApp(produto) {
    const mensagem = `Estou interessado no produto: ${produto.nome}. Detalhes: ${produto.descricao} - Preço: R$ ${produto.valor.toFixed(2)}`;
    const numeroWhatsApp = '5521977461637';
    const urlWhatsApp = `https://wa.me/${numeroWhatsApp}?text=${encodeURIComponent(mensagem)}`;
    window.open(urlWhatsApp, '_blank');
}

document.addEventListener('DOMContentLoaded', buscarProdutos);

document.addEventListener('click', (event) => {
    if (event.target.classList.contains('botao-compra')) {
        event.preventDefault();
        const produto = JSON.parse(event.target.getAttribute('data-produto'));
        armazenarNoLocalStorage(produto);
        alert(`${produto.nome} foi adicionado ao carrinho!`);

        gerarPDF(produto);
        redirecionarWhatsApp(produto);
    }
});
