package ar.edu.utn.frc.tup.lc.iv.service.implementacion;

import ar.edu.utn.frc.tup.lc.iv.clients.MicroApiClient;
import ar.edu.utn.frc.tup.lc.iv.domain.*;
import ar.edu.utn.frc.tup.lc.iv.service.DistritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistritoServiceImpl implements DistritoService {

    @Autowired
    private MicroApiClient microApiClient;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("seccionMapper")
    private ModelMapper seccionMapper;

    @Override
    public List<DistritoDto> getDistritos() {
        List<Distrito> lista = microApiClient.getMicro();
        List<DistritoDto> listaDistritosDto = new ArrayList<>();

        for (Distrito d : lista) {
            listaDistritosDto.add(modelMapper.map(d, DistritoDto.class));
        }

        return listaDistritosDto;
    }

    @Override
    public List<DistritoDto> getDistritosByNombre(String nombre) {
        List<Distrito> lista = microApiClient.getMicro();

        // Filtrar la lista para que contenga solo los distritos que tengan el nombre especificado
//        List<DistritoDto> resultado = lista.stream()
//                .filter(distrito -> distrito.getDistritoNombre().toLowerCase().contains(nombre.toLowerCase())) // Filtrar por nombre
//                .map(distrito -> {
//                    DistritoDto dto = new DistritoDto(); // Crear una nueva instancia de DistritoDto
//                    dto.setId(distrito.getDistritoId()); // Establecer el id
//                    dto.setNombre(distrito.getDistritoNombre()); // Establecer el nombre
//                    return dto; // Retornar el DTO
//                })
//                .collect(Collectors.toList()); // Recoger los resultados en una lista
//
//        return resultado; // Retornar la lista filtrada
        List<DistritoDto> listaDistritosDto = new ArrayList<>();
        for (Distrito d : lista) {
            if (d.getDistritoNombre().toLowerCase().contains(nombre)) {
                listaDistritosDto.add(modelMapper.map(d, DistritoDto.class));
            }
        }

        return listaDistritosDto;
    }

    @Override
    public DistritoCargo getDistritoCargo(Long id) {
        Distrito distrito = new Distrito();
        List<Distrito> listaDistritos = microApiClient.getMicro();

        for (Distrito d : listaDistritos) {
            if (d.getDistritoId().equals(id)) {
                distrito.setDistritoId(d.getDistritoId());
                distrito.setDistritoNombre(d.getDistritoNombre());
                break;
            }
        }

        List<Cargo> lista = microApiClient.getCargos();
        DistritoCargo distritoCargo = new DistritoCargo();
        distritoCargo.setDistrito(modelMapper.map(distrito, DistritoDto.class));
        List<Cargo> cargos = new ArrayList<>();

        for (Cargo c : lista) {
            if (c.getDistritoId().equals(distrito.getDistritoId())) {
                cargos.add(c);
            }
        }

        System.out.println(cargos);
        distritoCargo.setCargos(cargos);
        return distritoCargo;
    }

    @Override
    public List<SeccionDto> getSecciones(Long distrito_id, Long seccion_id) {
        List<Seccion> lista = microApiClient.getSecciones();
        List<SeccionDto> secciones = new ArrayList<>();

        for (Seccion s : lista) {
            if (s.getDistritoId().equals(distrito_id) && seccion_id == null) {
                secciones.add(seccionMapper.map(s, SeccionDto.class));
            }
            else if (s.getDistritoId().equals(distrito_id) && s.getSeccionId().equals(seccion_id)) {
                secciones.add(seccionMapper.map(s, SeccionDto.class));
            }
        }
        return secciones;
    }

    @Override
    public ResultadoDto getResultado(Long distrito_id, Long seccion_id) {
        List<Resultado> resultados = microApiClient.getResultados();
        List<Resultado> listaResultados = new ArrayList<>();

        // Filtrar por distrito y sección
        for (Resultado r : resultados) {
            if (r.getDistritoId().equals(distrito_id) && r.getSeccionId().equals(seccion_id)) {
                listaResultados.add(r);
            }
        }

        ResultadoDto resultadoDto = new ResultadoDto(); // Resultado final que contiene a todos
        resultadoDto.setDistrito(listaResultados.get(0).getDistritoNombre()); // Se asume que todos tienen el mismo distrito
        resultadoDto.setSeccion(listaResultados.get(0).getSeccionNombre()); // Igual para la sección

        // Mapa para almacenar los votos de cada partido por su nombre
        Map<String, ResultadosDto> partidoResultados = new HashMap<>();
        int totalVotos = 0;

        // Sumamos los votos por partido y calculamos el total de votos
        for (Resultado r : listaResultados) {
            String partidoNombre = r.getAgrupacionNombre();
            int votos = r.getVotosCantidad();
            totalVotos += votos;

            // Si ya existe el partido en el mapa, sumar los votos
            partidoResultados.computeIfAbsent(partidoNombre, key -> {
                ResultadosDto nuevoResultado = new ResultadosDto();
                nuevoResultado.setNombre(partidoNombre);
                nuevoResultado.setOrden(1L); // Orden provisional, lo actualizaremos después
                return nuevoResultado;
            }).setVotos(partidoResultados.get(partidoNombre).getVotos() + votos);
        }

        // Ahora que tenemos el total de votos, calculamos el porcentaje para cada partido
        for (ResultadosDto dto : partidoResultados.values()) {
            int votosPartido = dto.getVotos();
            double porcentaje = (votosPartido * 100.0) / totalVotos;
            dto.setPorcentaje(porcentaje);
        }

        // Ordenar los resultados de mayor a menor por número de votos
        List<ResultadosDto> resultadosOrdenados = new ArrayList<>(partidoResultados.values());
        resultadosOrdenados.sort(Comparator.comparing(ResultadosDto::getVotos).reversed());

        // Actualizar el orden de los partidos según su posición
        long orden = 1;
        for (ResultadosDto dto : resultadosOrdenados) {
            dto.setOrden(orden++);
        }

        // Agregar los resultados al resultado final
        resultadoDto.setResultados(resultadosOrdenados);

        return resultadoDto;
    }


}
