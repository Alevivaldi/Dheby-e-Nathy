package br.com.alexcosta.services;

import br.com.alexcosta.dto.ProdutoDTO;
import br.com.alexcosta.entities.Produto;

import br.com.alexcosta.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServices {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }
}
