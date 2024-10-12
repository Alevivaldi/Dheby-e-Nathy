package br.com.alexcosta.controllers;

import br.com.alexcosta.dto.ProdutoDTO;

import br.com.alexcosta.entities.Produto;
import br.com.alexcosta.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllers {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(value="/listar")
    public List<ProdutoDTO> listarProdutos() {

        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }
}
