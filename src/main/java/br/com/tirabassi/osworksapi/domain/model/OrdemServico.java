package br.com.tirabassi.osworksapi.domain.model;

import br.com.tirabassi.osworksapi.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Data
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroup.ClienteId.class)
    @ManyToOne
    @NotNull
    private Cliente cliente;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusOrdemServico status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data_abertura")
    private OffsetDateTime dataAbertura;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "data_finalizacao")
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios = new ArrayList<>();

    public void finalizar() {

        if(!StatusOrdemServico.ABERTA.equals(getStatus()))
            throw new NegocioException("O status da ordem não pode ser finalizada");

        setStatus(StatusOrdemServico.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
