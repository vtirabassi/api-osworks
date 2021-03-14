package br.com.tirabassi.osworksapi.model.mappers;

import br.com.tirabassi.osworksapi.domain.model.Comentario;
import br.com.tirabassi.osworksapi.domain.model.OrdemServico;
import br.com.tirabassi.osworksapi.model.request.OrdemServicoRequest;
import br.com.tirabassi.osworksapi.model.response.ComentarioModel;
import br.com.tirabassi.osworksapi.model.response.OrdemServicoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ComentarioMapper {

    private static ModelMapper modelMapperStatic;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    private void init(){
        modelMapperStatic = modelMapper;
    }

    public static ComentarioModel toModel(Comentario comentario){
        ComentarioModel comentarioModel = modelMapperStatic.map(comentario, ComentarioModel.class);

        return  comentarioModel;
    }
}
