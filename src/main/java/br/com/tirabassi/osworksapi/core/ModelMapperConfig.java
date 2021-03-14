package br.com.tirabassi.osworksapi.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Componente do spring com objetivo de configuracao de beans
public class ModelMapperConfig {


    @Bean //metodo insntancia para disp injevao de depen
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
