package ar.edu.utn.frc.tup.lc.iv.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultadoDto {
    String distrito;
    String seccion;
    List<ResultadosDto> resultados = new ArrayList<>();
}
