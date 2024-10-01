package ar.edu.utn.frc.tup.lc.iv.service.implementacion;

import ar.edu.utn.frc.tup.lc.iv.clients.MicroApiClient;
import ar.edu.utn.frc.tup.lc.iv.domain.*;
import ar.edu.utn.frc.tup.lc.iv.service.DistritoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private MicroApiClient microApiClient;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<DistritoDto> getDistritos() {
        List<Distrito> lista = microApiClient.getMicro();

        if (lista == null) {
            throw new EntityNotFoundException("La lista esta vacia");
        }

        List<DistritoDto> listaDistritosDto = new ArrayList<>();

        for (Distrito d : lista) {
            listaDistritosDto.add(modelMapper.map(d, DistritoDto.class));
        }

        return listaDistritosDto;
//        ResponseEntity<Distrito[]> lista = microApiClient.getMicro();
//        Distrito[] distritos = lista.getBody();
//
//        if (distritos == null) {
//            throw new EntityNotFoundException("La lista esta vacia");
//        }
//
//        List<DistritoDto> listaDistritosDto = new ArrayList<>();
//
//        for (Distrito d : distritos) {
//            listaDistritosDto.add(modelMapper.map(d, DistritoDto.class));
//        }
//
//        return listaDistritosDto;
    }

    @Override
    public List<DistritoDto> getDistritosByNombre(String nombre) {
        List<Distrito> lista = microApiClient.getMicro();

        // Filtrar la lista para que contenga solo los distritos que tengan el nombre especificado
//        List<DistritoDto> resultado = lista.stream()
//                .filter(distrito -> distrito.getDistritoNombre().toLowerCase().contains(nombre.toLowerCase())) // Filtrar por nombre
//                .map(distrito -> {
//                    DistritoDto dto = new DistritoDto(); // Crear una nueva instancia de DistritoDto
//                    dto.setId(distrito.getDistritoId()); // Establecer el id
//                    dto.setNombre(distrito.getDistritoNombre()); // Establecer el nombre
//                    return dto; // Retornar el DTO
//                })
//                .collect(Collectors.toList()); // Recoger los resultados en una lista
//
//        return resultado; // Retornar la lista filtrada
        List<DistritoDto> listaDistritosDto = new ArrayList<>();
        for (Distrito d : lista) {
            if (d.getDistritoNombre().toLowerCase().contains(nombre)) {
                listaDistritosDto.add(modelMapper.map(d, DistritoDto.class));
            }
        }

        return listaDistritosDto;
    }

    @Override
    public DistritoCargo getDistritoCargo(Long id) {
        Distrito distrito = new Distrito();
        List<Distrito> listaDistritos = microApiClient.getMicro();

        for (Distrito d : listaDistritos) {
            if (d.getDistritoId().equals(id)) {
                distrito.setDistritoId(d.getDistritoId());
                distrito.setDistritoNombre(d.getDistritoNombre());
                break;
            }
        }

        List<Cargo> lista = microApiClient.getCargos();
        DistritoCargo distritoCargo = new DistritoCargo();
        distritoCargo.setDistrito(modelMapper.map(distrito, DistritoDto.class));
        List<Cargo> cargos = new ArrayList<>();

        for (Cargo c : lista) {
            if (c.getDistritoId().equals(distrito.getDistritoId())) {
                cargos.add(c);
            }
        }

        System.out.println(cargos);
        distritoCargo.setCargos(cargos);
        return distritoCargo;
    }


}
