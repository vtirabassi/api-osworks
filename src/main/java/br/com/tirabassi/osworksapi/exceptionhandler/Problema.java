package br.com.tirabassi.osworksapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problema {

    private Integer status;
    private OffsetDateTime dataHora;
    private String titulo;
    private List<Campos> campos;

    @Getter
    @Setter
    public static class Campos{
        private String nome;
        private String mensagem;

        public Campos(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
        }
    }
}
