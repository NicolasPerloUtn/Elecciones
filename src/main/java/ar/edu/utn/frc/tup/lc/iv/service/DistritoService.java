package ar.edu.utn.frc.tup.lc.iv.service;

import ar.edu.utn.frc.tup.lc.iv.domain.DistritoCargo;
import ar.edu.utn.frc.tup.lc.iv.domain.DistritoDto;

import java.util.List;


public interface DistritoService {
    List<DistritoDto> getDistritos();
    List<DistritoDto> getDistritosByNombre(String nombre);
    DistritoCargo getDistritoCargo(Long id);
}
