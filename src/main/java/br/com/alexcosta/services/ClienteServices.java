package br.com.alexcosta.services;

import br.com.alexcosta.dto.CadastroDTO;
import br.com.alexcosta.entities.Cliente;
import br.com.alexcosta.entities.Endereco;
import br.com.alexcosta.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ClienteServices {
    @Autowired
    private ClienteRepository clienteRepository;

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Usuário ou senha não podem ser nulos");
        }

        Cliente user = clienteRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return password.equals(user.getPassword());
    }
    public Cliente cadastrar(CadastroDTO cadastroDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(cadastroDTO.getEndereco().getCep());
        endereco.setLogradouro(cadastroDTO.getEndereco().getLogradouro());
        endereco.setCidade(cadastroDTO.getEndereco().getCidade());
        endereco.setEstado(cadastroDTO.getEndereco().getEstado());
        Cliente cliente = new Cliente();
        cliente.setData(Instant.now());
        cliente.setNome(cadastroDTO.getNome());
        cliente.setUsername(cadastroDTO.getUsername());
        cliente.setTelefone(cadastroDTO.getTelefone());
        cliente.setPassword(cadastroDTO.getPassword());
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }
}
