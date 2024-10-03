package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.domain.*;
import ar.edu.utn.frc.tup.lc.iv.service.DistritoService;
import ar.edu.utn.frc.tup.lc.iv.service.implementacion.DistritoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/controlador")
public class DistritoController {

    @Autowired
    private DistritoService distritoService;

    @GetMapping("/distritos")
    public List<DistritoDto> getDistritos(@RequestParam (required = false) String nombre) {
        if (nombre == null) {
            return distritoService.getDistritos();
        }
        else {
            return distritoService.getDistritosByNombre(nombre);
        }
    }

    @GetMapping("/cargos/{id}")
    public DistritoCargo getDistritoCargoById(@PathVariable Long id) {
        return distritoService.getDistritoCargo(id);
    }

    @GetMapping("/secciones/{distrito_id}")
    public List<SeccionDto> getSecciones(@RequestParam Long distrito_id, @RequestParam (required = false) Long seccion_id) {
        return distritoService.getSecciones(distrito_id, seccion_id);
    }

    @GetMapping("/resultados/{distrito_id}/{seccion_id}")
    public ResultadoDto getResultados(@RequestParam Long distrito_id, @RequestParam (required = false) Long seccion_id) {
        return distritoService.getResultado(distrito_id, seccion_id);
    }
}
