package ar.edu.utn.frc.tup.lc.iv.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
    private Long cargoId;

    private String cargoNombre;

    private Long distritoId;
}
