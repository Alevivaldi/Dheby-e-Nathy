package br.com.alexcosta.controllers;
import br.com.alexcosta.dto.CadastroDTO;
import br.com.alexcosta.dto.ClienteDTO;
import br.com.alexcosta.entities.Cliente;
import br.com.alexcosta.entities.Endereco;
import br.com.alexcosta.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:8080") 

@RestController
@RequestMapping(value="/cliente")
public class ClienteControllers {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(value="/login")
    public boolean login(@RequestBody ClienteDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) {
            throw new IllegalArgumentException("Usuário ou senha não podem ser nulos");
        }

        Cliente user = clienteRepository.findByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return dto.getPassword().equals(user.getPassword());
    }
    @PostMapping(value="/cadastro")
    public Cliente cadastrar(@RequestBody CadastroDTO cadastroDTO) {
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

