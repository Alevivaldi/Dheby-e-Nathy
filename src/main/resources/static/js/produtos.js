async function buscarProdutos() {
    try {
        const response = await fetch('http://localhost:8080/produtos');
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

document.addEventListener('DOMContentLoaded', buscarProdutos);


document.addEventListener('click', (event) => {
    if (event.target.classList.contains('botao-compra')) {
        event.preventDefault();
        const produto = JSON.parse(event.target.getAttribute('data-produto'));
        armazenarNoLocalStorage(produto);
        alert(`${produto.nome} foi adicionado ao carrinho!`);
    }
});
