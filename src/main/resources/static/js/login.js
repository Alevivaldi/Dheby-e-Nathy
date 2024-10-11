<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.querySelector('form');
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const usuario = document.getElementById('usuario').value;
            const senha = document.getElementById('senha').value;

            fetch('http://localhost:8080/cliente/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: usuario,
                    password: senha,
                }),
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Falha no login');
                }
            })
            .then(data => {
                console.log('Login bem-sucedido:', data);
                alert('Login realizado com sucesso!');
                window.location.href = 'painel.html';
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Usuário ou senha incorretos.');
            });
        });
    });
</script>
