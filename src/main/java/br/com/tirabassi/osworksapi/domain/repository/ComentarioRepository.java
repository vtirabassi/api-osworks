package br.com.tirabassi.osworksapi.domain.repository;

import br.com.tirabassi.osworksapi.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
