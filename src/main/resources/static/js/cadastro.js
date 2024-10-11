 document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById('cadastro-form');

        document.getElementById('cep').addEventListener('blur', function() {
            const cep = this.value.replace(/\D/g, '');
            if (cep.length === 8) {
                fetch(`https://viacep.com.br/ws/${cep}/json/`)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('CEP não encontrado');
                        }
                        return response.json();
                    })
                    .then(data => {
                        document.getElementById('endereco').value = data.logradouro;
                        document.getElementById('cidade').value = data.localidade;
                        document.getElementById('estado').value = data.uf;
                    })
                    .catch(error => {
                        alert(error.message);
                    });
            } else {
                alert('CEP inválido. Deve conter 8 dígitos.');
            }
        });

        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = {
                nome: document.getElementById('nome').value,
                username: document.getElementById('email').value,
                telefone: document.getElementById('telefone').value,
                endereco: {
                    cep: document.getElementById('cep').value,
                    logradouro: document.getElementById('endereco').value,
                    cidade: document.getElementById('cidade').value,
                    estado: document.getElementById('estado').value,
                },
                password: document.getElementById('senha').value,
            };

            // Envia os dados para o servidor
            fetch('http://localhost:8080/cliente/cadastro', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Falha no cadastro');
                }
            })
            .then(data => {
                console.log('Cadastro bem-sucedido:', data);
                alert('Cadastro realizado com sucesso!');
                window.location.href = './login.html';
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Ocorreu um erro no cadastro. Tente novamente.');
            });
        });
    });





    /*
    Nathy e debhy esse é o formato JSON
    {
        "nome": "Nome do Usuário",
        "email": "email@exemplo.com",
        "telefone": "123456789",
        "endereco": {
            "cep": "12345678",
            "logradouro": "Rua Exemplo, 123",
            "cidade": "Cidade Exemplo",
            "estado": "Estado"
        },
        "senha": "senha_secreta"
    }
*/