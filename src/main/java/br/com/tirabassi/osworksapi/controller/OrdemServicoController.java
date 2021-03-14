package br.com.tirabassi.osworksapi.controller;

import br.com.tirabassi.osworksapi.domain.model.Comentario;
import br.com.tirabassi.osworksapi.domain.model.OrdemServico;
import br.com.tirabassi.osworksapi.domain.repository.ComentarioRepository;
import br.com.tirabassi.osworksapi.domain.repository.OrdemServicoRepository;
import br.com.tirabassi.osworksapi.domain.service.OrdemServicoService;
import br.com.tirabassi.osworksapi.model.request.ComentarioRequest;
import br.com.tirabassi.osworksapi.model.request.OrdemServicoRequest;
import br.com.tirabassi.osworksapi.model.response.ComentarioModel;
import br.com.tirabassi.osworksapi.model.response.OrdemServicoModel;
import br.com.tirabassi.osworksapi.model.mappers.OrdemServicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ordens_servicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrdemServicoModel> addCliente(@Valid @RequestBody OrdemServicoRequest ordemServicoRequest){

        OrdemServico ordemServico = OrdemServicoMapper.toEntity(ordemServicoRequest);

        var a = ordemServicoService.save(ordemServico);

        return ResponseEntity.ok(OrdemServicoMapper.toMapper(a));
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoModel>> getAll(){

        List<OrdemServicoModel> ordemServicoModelList = ordemServicoRepository.findAll()
                .stream()
                .map(x -> OrdemServicoMapper.toMapper(x))
                .collect(Collectors.toList());

        return ResponseEntity.ok(ordemServicoModelList);
    }

    @GetMapping("/{id_ordem}")
    public ResponseEntity<OrdemServicoModel> getOdem(@PathVariable("id_ordem") Long idOrdem){

        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(idOrdem);

        if(ordemServico.isPresent()){

            return ResponseEntity.ok(OrdemServicoMapper.toMapper(ordemServico.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id_ordem/finalizacao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity finalizarOrdem(@PathVariable("id_ordem") Long idOrdem){

        ordemServicoService.finalizar(idOrdem);

        return ResponseEntity.noContent().build();
    }


}
