package br.com.alexcosta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter@NoArgsConstructor@AllArgsConstructor
public class CadastroDTO {
    private String nome;
    private String username;
    private String telefone;
    private EnderecoDTO endereco;
    private String password;


}
