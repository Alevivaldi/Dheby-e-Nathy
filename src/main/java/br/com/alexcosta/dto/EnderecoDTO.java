package br.com.alexcosta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter@NoArgsConstructor@AllArgsConstructor
public class EnderecoDTO {
    private String cep;
    private String logradouro;
    private String cidade;
    private String estado;


}
