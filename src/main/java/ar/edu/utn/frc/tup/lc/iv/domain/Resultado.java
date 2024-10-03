package ar.edu.utn.frc.tup.lc.iv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
    private Long id;
    private String eleccionTipo;
    private String recuentoTipo;
    private String padronTipo;
    private Long distritoId;
    private String distritoNombre;
    private Long seccionProvincialId;
    private String seccionProvincialNombre;
    private Long seccionId;
    private String seccionNombre;
    private String circuitoId;
    private String circuitoNombre;
    private Long mesaId;
    private String mesaTipo;
    private Integer mesaElectores;
    private Long cargoId;
    private String cargoNombre;
    private Long agrupacionId;
    private String agrupacionNombre;
    private String listaNumero;
    private String listaNombre;
    private String votosTipo;
    private Integer votosCantidad;
    private Integer año;
}
