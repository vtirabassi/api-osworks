package br.com.tirabassi.osworksapi.domain.service;

import br.com.tirabassi.osworksapi.domain.model.Cliente;
import br.com.tirabassi.osworksapi.domain.model.Comentario;
import br.com.tirabassi.osworksapi.domain.model.OrdemServico;
import br.com.tirabassi.osworksapi.domain.model.StatusOrdemServico;
import br.com.tirabassi.osworksapi.domain.repository.ClienteRepository;
import br.com.tirabassi.osworksapi.domain.repository.ComentarioRepository;
import br.com.tirabassi.osworksapi.domain.repository.OrdemServicoRepository;
import br.com.tirabassi.osworksapi.exception.EntidadeNaoEncontradaException;
import br.com.tirabassi.osworksapi.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class OrdemServicoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public OrdemServico save(OrdemServico ordem){

        Cliente cliente = clienteRepository.findById(ordem.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliete não encontrado"));

        ordem.setCliente(cliente);

        ordem.setStatus(StatusOrdemServico.ABERTA);
        ordem.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordem);
    }

    public Comentario addComentario(Long idOrdemServico, String descricao){
        OrdemServico ordemServico = getOrdem(idOrdemServico);

        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);
    }

    public void finalizar(Long idOrdemServico){
        OrdemServico ordemServico = getOrdem(idOrdemServico);

        ordemServico.finalizar();

        ordemServicoRepository.save(ordemServico);

    }

    private OrdemServico getOrdem(Long idOrdemServico){
        return ordemServicoRepository.findById(idOrdemServico)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
    }
}
