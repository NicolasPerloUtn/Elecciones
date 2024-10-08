package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.domain.Cargo;
import ar.edu.utn.frc.tup.lc.iv.domain.Distrito;
import ar.edu.utn.frc.tup.lc.iv.domain.Resultado;
import ar.edu.utn.frc.tup.lc.iv.domain.Seccion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MicroApiClientTest {
    @Autowired
    private MicroApiClient microApiClient;

    @Test
    void getDistritos() {
        List<Distrito> result = microApiClient.getMicro();
        assertEquals(24, Objects.requireNonNull(result.toArray().length));
    }

    @Test
    void getCargos() {
        List<Cargo> result = microApiClient.getCargos();
        assertEquals(118, Objects.requireNonNull(result.toArray().length));
    }

    @Test
    void getSecciones() {
        List<Seccion> result = microApiClient.getSecciones();
        assertEquals(118, Objects.requireNonNull(result.toArray().length));
    }

    @Test
    void getResultados() {
        List<Resultado> result = microApiClient.getResultados();
        assertEquals(2930, Objects.requireNonNull(result.toArray().length));
    }
}