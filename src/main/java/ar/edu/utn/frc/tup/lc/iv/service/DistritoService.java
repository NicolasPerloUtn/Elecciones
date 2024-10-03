package ar.edu.utn.frc.tup.lc.iv.service;

import ar.edu.utn.frc.tup.lc.iv.domain.*;

import java.util.List;


public interface DistritoService {
    List<DistritoDto> getDistritos();
    List<DistritoDto> getDistritosByNombre(String nombre);
    DistritoCargo getDistritoCargo(Long id);
    List<SeccionDto> getSecciones(Long distrito_id, Long seccion_id);
    ResultadoDto getResultado(Long distrito_id, Long seccion_id);
}
