package br.com.tirabassi.osworksapi.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdemServicoModel {

    private Long id;
    private ClienteResumoModel cliente;
    private String descricao;
    private BigDecimal preco;
    private String status;
    private List<ComentarioModel> comentarios;
    private OffsetDateTime dataAbertura;
    private OffsetDateTime dataFinalizacao;
}
