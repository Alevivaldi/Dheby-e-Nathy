package br.com.alexcosta.controllers;

import br.com.alexcosta.dto.ProdutoDTO;

import br.com.alexcosta.services.ProdutoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllers {

    @Autowired
    private ProdutoServices produtoService;

    @GetMapping(value="/listar")
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.buscarTodos();
    }
}
