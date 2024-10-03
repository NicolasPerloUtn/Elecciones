package ar.edu.utn.frc.tup.lc.iv.domain;

import lombok.Data;

@Data
public class ResultadosDto {
    private Long orden;
    private String nombre;
    private int votos;
    private double porcentaje;
}
