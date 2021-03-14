package br.com.tirabassi.osworksapi.model.request;

import br.com.tirabassi.osworksapi.model.response.ClienteResumoModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrdemServicoRequest {

    @NotNull
    @JsonProperty("id_cliente")
    private Long idCliente;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;
}
