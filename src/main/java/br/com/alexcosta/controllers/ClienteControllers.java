package br.com.alexcosta.controllers;

import br.com.alexcosta.dto.CadastroDTO;
import br.com.alexcosta.dto.ClienteDTO;
import br.com.alexcosta.entities.Cliente;
import br.com.alexcosta.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080") 

@RestController
@RequestMapping(value="/cliente")
public class ClienteControllers {

    @Autowired
    private ClienteServices clienteServices;

    @PostMapping(value="/login")
    public ResponseEntity<String> login(@RequestBody ClienteDTO dto) {
        try {
            boolean isAuthenticated = clienteServices.login(dto.getUsername(), dto.getPassword());
            if (isAuthenticated) {
                return ResponseEntity.ok("Login bem-sucedido");
            } else {
                return ResponseEntity.status(401).body("Usuário ou senha incorretos");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @PostMapping(value="/cadastro")
    public ResponseEntity<Cliente> cadastrar(@RequestBody CadastroDTO cadastroDTO) {
        Cliente cliente = clienteServices.cadastrar(cadastroDTO);
        return ResponseEntity.ok(cliente);
    }

}

