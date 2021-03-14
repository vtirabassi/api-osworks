package br.com.tirabassi.osworksapi.controller;

import br.com.tirabassi.osworksapi.domain.model.Comentario;
import br.com.tirabassi.osworksapi.domain.model.OrdemServico;
import br.com.tirabassi.osworksapi.domain.repository.OrdemServicoRepository;
import br.com.tirabassi.osworksapi.domain.service.OrdemServicoService;
import br.com.tirabassi.osworksapi.exception.EntidadeNaoEncontradaException;
import br.com.tirabassi.osworksapi.exception.NegocioException;
import br.com.tirabassi.osworksapi.model.mappers.ComentarioMapper;
import br.com.tirabassi.osworksapi.model.request.ComentarioRequest;
import br.com.tirabassi.osworksapi.model.response.ComentarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ordens_servicos/{id_ordem}/comentarios")
public class ComentarioController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ComentarioModel> addComentario(@PathVariable("id_ordem") Long idOrdem,
                                                         @Valid @RequestBody ComentarioRequest comentarioRequest){

        Comentario comentario = ordemServicoService.addComentario(idOrdem, comentarioRequest.getComentario());

        return ResponseEntity.ok(ComentarioMapper.toModel(comentario));

    }

    @GetMapping
    public ResponseEntity<List<ComentarioModel>> getAll(@PathVariable("id_ordem") Long idOrdem){

        OrdemServico ordemServico = ordemServicoRepository.findById(idOrdem)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        List<ComentarioModel> comentarioModelList = ordemServico.getComentarios()
                .stream()
                .map(c -> ComentarioMapper.toModel(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(comentarioModelList);
    }

}
