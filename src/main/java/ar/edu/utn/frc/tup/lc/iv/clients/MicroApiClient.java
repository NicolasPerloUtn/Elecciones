package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.domain.Cargo;
import ar.edu.utn.frc.tup.lc.iv.domain.Distrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

//    public ResponseEntity<Distrito[]> getMicro() {
//        return restTemplate.getForEntity(baseUrl, Distrito[].class);
//    }
}
