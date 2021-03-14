package br.com.tirabassi.osworksapi.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Data
@Table(name = "Cliente")
public class Cliente {

    @Id
    @NotNull(groups = ValidationGroup.ClienteId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @Column(name = "fone")
    @NotBlank
    @Size(max = 20)
    private String telefone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
