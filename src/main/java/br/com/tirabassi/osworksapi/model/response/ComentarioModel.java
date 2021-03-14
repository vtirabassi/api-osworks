package br.com.tirabassi.osworksapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ComentarioModel {
    private Long id;
    private String descricao;
    private OffsetDateTime dataEnvio;
}
