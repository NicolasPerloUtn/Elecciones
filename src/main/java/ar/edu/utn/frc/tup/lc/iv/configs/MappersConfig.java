package ar.edu.utn.frc.tup.lc.iv.configs;

import ar.edu.utn.frc.tup.lc.iv.domain.Seccion;
import ar.edu.utn.frc.tup.lc.iv.domain.SeccionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean("mergerMapper")
    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean("seccionMapper")
    public ModelMapper seccionMapper() {
        ModelMapper mapper = new ModelMapper();

        // Configuración para mapear propiedades con nombres diferentes
        mapper.addMappings(new PropertyMap<Seccion, SeccionDto>() {
            @Override
            protected void configure() {
                map().setId(source.getSeccionId());         // Mapea seccionId a id
                map().setNombre(source.getSeccionNombre()); // Mapea seccionNombre a nombre
            }
        });

        // Configuración para evitar sobrescribir propiedades con null
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());

        return mapper;
    }

}
