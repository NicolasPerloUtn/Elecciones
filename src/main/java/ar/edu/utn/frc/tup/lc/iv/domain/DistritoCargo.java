package ar.edu.utn.frc.tup.lc.iv.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DistritoCargo {
    private DistritoDto distrito;

    private List<Cargo> cargos = new ArrayList<>();
}
