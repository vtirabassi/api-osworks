package br.com.tirabassi.osworksapi.model.mappers;

import br.com.tirabassi.osworksapi.domain.model.OrdemServico;
import br.com.tirabassi.osworksapi.model.request.OrdemServicoRequest;
import br.com.tirabassi.osworksapi.model.response.OrdemServicoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrdemServicoMapper {

    private static ModelMapper modelMapperStatic;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    private void init(){
        modelMapperStatic = modelMapper;
    }

    public static OrdemServicoModel toMapper(OrdemServico ordemServico){
        OrdemServicoModel ordemServicoModel = modelMapperStatic.map(ordemServico, OrdemServicoModel.class);

        return  ordemServicoModel;
    }

    public static OrdemServico toEntity(OrdemServicoRequest ordemServicoRequest){
        OrdemServico ordemServicoModel = modelMapperStatic.map(ordemServicoRequest, OrdemServico.class);

        return  ordemServicoModel;
    }
}
