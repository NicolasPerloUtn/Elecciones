package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.domain.Cargo;
import ar.edu.utn.frc.tup.lc.iv.domain.Distrito;
import ar.edu.utn.frc.tup.lc.iv.domain.Resultado;
import ar.edu.utn.frc.tup.lc.iv.domain.Seccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MicroApiClient {

    @Autowired
    private RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8080/distritos";

    public List<Distrito> getMicro() {
        ResponseEntity<List<Distrito>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Distrito>>() {}
        );

        System.out.println(response.getBody());
        return response.getBody();
    }

    private String urlCargos = "http://localhost:8080/cargos";

    public List<Cargo> getCargos() {
        ResponseEntity<List<Cargo>> response = restTemplate.exchange(
                urlCargos,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Cargo>>() {}
        );

        return response.getBody();
    }

    private String urlSecciones = "http://localhost:8080/secciones";

    public List<Seccion> getSecciones() {
        ResponseEntity<List<Seccion>> response = restTemplate.exchange(
                urlSecciones,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Seccion>>() {}
        );

        return response.getBody();
    }

    private String urlResultados = "http://localhost:8080/resultados?seccionId=26";

    public List<Resultado> getResultados() {
        ResponseEntity<List<Resultado>> response = restTemplate.exchange(
                urlResultados,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Resultado>>() {}
        );

        return response.getBody();
    }
//    public ResponseEntity<Distrito[]> getMicro() {
//        return restTemplate.getForEntity(baseUrl, Distrito[].class);
//    }
}
