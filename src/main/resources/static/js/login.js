document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const usuario = document.getElementById('usuario').value;
        const senha = document.getElementById('senha').value;

        const loginData = {
            username: usuario,
            password: senha,
        };

        fetch('http://localhost:8080/cliente/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData),
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Usuário ou senha inválidos');
            }
        })
        .then(data => {
            console.log('Login bem-sucedido:', data);
            showSnackbar('Login realizado com sucesso!');

            // Atrasar o redirecionamento em 3 segundos (3000 milissegundos)
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 3000);
        })
        .catch(error => {
            console.error('Erro:', error);
            showSnackbar('Usuário ou senha incorretos.');
        });
    });

    function showSnackbar(message) {
        const snackbar = document.getElementById("snackbar");
        snackbar.textContent = message;
        snackbar.className = "show";
        setTimeout(() => { snackbar.className = snackbar.className.replace("show", ""); }, 3000);
    }
});
