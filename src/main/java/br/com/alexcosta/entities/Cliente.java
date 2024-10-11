package br.com.alexcosta.entities;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor@AllArgsConstructor@Getter@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Instant data;
    private String username;
    private String password;
    private String telefone;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="produto_id")
    private List<Produto> produto=new ArrayList<>();

    private String formaPagamento;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="vendas_id")
    private Set<Vendas> vendas=new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(Long id, String nome, String username, String telefone, Endereco endereco, String password) {
        this.id = id;
        this.nome = nome;
        this.username = username;
        this.telefone = telefone;
        this.endereco = endereco;
        this.password = password;
        this.data = Instant.now(); // Inicializa com a data atual
    }

}
