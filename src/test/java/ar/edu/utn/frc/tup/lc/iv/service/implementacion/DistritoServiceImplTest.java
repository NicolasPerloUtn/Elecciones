package ar.edu.utn.frc.tup.lc.iv.service.implementacion;

import ar.edu.utn.frc.tup.lc.iv.clients.MicroApiClient;
import ar.edu.utn.frc.tup.lc.iv.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DistritoServiceImplTest {

    @Mock
    private MicroApiClient microApiClient;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DistritoServiceImpl distritoService;

    private Distrito[] mockDistritos;

    private DistritoDto[] mockDistritosDto;

    @Mock
    private ModelMapper seccionMapper;

    private Resultado createResultado(Long distritoId, Long seccionId, String distritoNombre,
                                      String seccionNombre, String agrupacionNombre, int votosCantidad) {
        Resultado resultado = new Resultado();
        resultado.setDistritoId(distritoId);
        resultado.setSeccionId(seccionId);
        resultado.setDistritoNombre(distritoNombre);
        resultado.setSeccionNombre(seccionNombre);
        resultado.setAgrupacionNombre(agrupacionNombre);
        resultado.setVotosCantidad(votosCantidad);
        return resultado;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockDistritos = new Distrito[] {
                new Distrito(1L, "Distrito1"),
                new Distrito(2L, "Distrito2")
        };
        mockDistritosDto = new DistritoDto[] {
                new DistritoDto(1L, "Distrito1"),
                new DistritoDto(2L, "Distrito2")
        };
    }

    @Test
    void getDistritos() {
        // Given
        List<Distrito> distritos = Arrays.asList(mockDistritos);
        when(microApiClient.getMicro()).thenReturn(distritos);

//        DistritoDto expectedDistritoDto1 = new DistritoDto(1L, "Distrito1");
//        DistritoDto expectedDistritoDto2 = new DistritoDto(2L, "Distrito2");
        List<DistritoDto> expectedDistritosDto = Arrays.asList(mockDistritosDto[0], mockDistritosDto[1]);

        when(modelMapper.map(mockDistritos[0], DistritoDto.class)).thenReturn(mockDistritosDto[0]);
        when(modelMapper.map(mockDistritos[1], DistritoDto.class)).thenReturn(mockDistritosDto[1]);

        // When
        List<DistritoDto> actualDistritosDto = distritoService.getDistritos();

        // Then
        assertEquals(expectedDistritosDto, actualDistritosDto);

//        List<Distrito> listaDistritos = new ArrayList<>();
//        Distrito distrito1 = new Distrito();
//        Distrito distrito2 = new Distrito();
//        listaDistritos.add(mockDistritos[0]);
//        listaDistritos.add(mockDistritos[1]);
//
//        when(microApiClient.getMicro()).thenReturn(listaDistritos);
//
//        DistritoDto distritoDto1 = new DistritoDto();
//        DistritoDto distritoDto2 = new DistritoDto();
//
//        // Mockear el comportamiento del modelMapper
//        when(modelMapper.map(mockDistritos[0], DistritoDto.class)).thenReturn(mockDistritosDto[0]);
//        when(modelMapper.map(mockDistritos[1], DistritoDto.class)).thenReturn(mockDistritosDto[1]);
//
//        // Ejecutar el método a probar
//        List<DistritoDto> resultado = distritoService.getDistritos();
//
//        // Verificar que el resultado sea correcto
//        assertEquals(2, resultado.size());
//        assertSame(mockDistritosDto[0], resultado.get(0));
//        assertSame(mockDistritosDto[1], resultado.get(1));
    }

//    @Test
//    public void testGetDistritos_WhenListIsEmpty_ShouldThrowEntityNotFoundException() {
//        // Arrange
//        when(microApiClient.getMicro()).thenReturn(null);
//
//        // Act and Assert
//        assertThrows(EntityNotFoundException.class, () -> distritoService.getDistritos());
//    }

    @Test
    void testGetDistritosByNombre() {
        // Arrange
//        Distrito distrito1 = new Distrito(1L, "Distrito Norte");
//        Distrito distrito2 = new Distrito(2L, "Distrito Sur");
//        Distrito distrito3 = new Distrito(3L, "Distrito Este");
//        List<Distrito> distritos = Arrays.asList(distrito1, distrito2, distrito3);
        List<Distrito> distritos = Arrays.asList(mockDistritos);

//        DistritoDto distritoDto1 = new DistritoDto(1L, "Distrito Norte");
//        DistritoDto distritoDto2 = new DistritoDto(2L, "Distrito Sur");
        DistritoDto expectedDistritoDto1 = new DistritoDto(1L, "Distrito1");
        DistritoDto expectedDistritoDto2 = new DistritoDto(2L, "Distrito2");

        when(microApiClient.getMicro()).thenReturn(distritos);
        when(modelMapper.map(mockDistritos[0], DistritoDto.class)).thenReturn(expectedDistritoDto1);
        when(modelMapper.map(mockDistritos[1], DistritoDto.class)).thenReturn(expectedDistritoDto2);

        // Act
        List<DistritoDto> result = distritoService.getDistritosByNombre("distrito");

        // Assert
        assertEquals(2, result.size());
        assertEquals(expectedDistritoDto1, result.get(0));
        assertEquals(expectedDistritoDto2, result.get(1));
//
//        // Act again with a more specific search
//        result = distritoService.getDistritosByNombre("norte");
//
//        // Assert
////        assertEquals(2, result.size());
////        assertEquals(mockDistritos[0], result.get(0));
    }

    @Test
    void testGetDistritoCargo() {
        // Arrange
        Long distritoId = 1L;
//        Distrito distrito1 = new Distrito(1L, "Distrito 1");
//        Distrito distrito2 = new Distrito(2L, "Distrito 2");
        List<Distrito> distritos = Arrays.asList(mockDistritos[0], mockDistritos[1]);

        Cargo cargo1 = new Cargo(1L, "Cargo 1", 1L);
        Cargo cargo2 = new Cargo(2L, "Cargo 2", 1L);
        Cargo cargo3 = new Cargo(3L, "Cargo 3", 2L);
        List<Cargo> cargos = Arrays.asList(cargo1, cargo2, cargo3);

        DistritoDto distritoDto1 = new DistritoDto(1L, "Distrito 1");

        when(microApiClient.getMicro()).thenReturn(distritos);
        when(microApiClient.getCargos()).thenReturn(cargos);
        when(modelMapper.map(mockDistritos[0], DistritoDto.class)).thenReturn(distritoDto1);

        // Act
        DistritoCargo result = distritoService.getDistritoCargo(distritoId);

        // Assert
        assertNotNull(result);
        assertEquals(distritoDto1, result.getDistrito());
        assertEquals(2, result.getCargos().size());
        assertTrue(result.getCargos().contains(cargo1));
        assertTrue(result.getCargos().contains(cargo2));
        assertFalse(result.getCargos().contains(cargo3));

        // Verify that the methods were called
//        verify(microApiClient, times(1)).getMicro();
//        verify(microApiClient, times(1)).getCargos();
//        verify(modelMapper, times(1)).map(distrito1, DistritoDto.class);
    }

    @Test
    void testGetSeccionesWithDistritoAndSeccion() {
        // Arrange
        Long distritoId = 1L;
        Long seccionId = 2L;
        Seccion seccion1 = new Seccion(1L, "Seccion 1", 1L);
        Seccion seccion2 = new Seccion(2L, "Seccion 2", 1L);
        Seccion seccion3 = new Seccion(3L, "Seccion 3", 2L);
        List<Seccion> secciones = Arrays.asList(seccion1, seccion2, seccion3);

        SeccionDto seccionDto2 = new SeccionDto(2L, "Seccion 2");

        when(microApiClient.getSecciones()).thenReturn(secciones);
        when(seccionMapper.map(seccion2, SeccionDto.class)).thenReturn(seccionDto2);

        // Act
        List<SeccionDto> result = distritoService.getSecciones(distritoId, seccionId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(seccionDto2, result.get(0));

        // Verify
//        verify(microApiClient, times(1)).getSecciones();
//        verify(seccionMapper, times(1)).map(seccion2, SeccionDto.class);
//        verify(seccionMapper, never()).map(seccion1, SeccionDto.class);
//        verify(seccionMapper, never()).map(seccion3, SeccionDto.class);
    }

    @Test
    void testGetResultado() {
        // Arrange
        Long distritoId = 1L;
        Long seccionId = 1L;

        Resultado resultado1 = createResultado(1L, 1L, "Distrito 1", "Seccion 1", "Partido A", 100);
        Resultado resultado2 = createResultado(1L, 1L, "Distrito 1", "Seccion 1", "Partido B", 150);
        Resultado resultado3 = createResultado(1L, 1L, "Distrito 1", "Seccion 1", "Partido A", 50);
        Resultado resultado4 = createResultado(2L, 1L, "Distrito 2", "Seccion 1", "Partido C", 200);

        List<Resultado> resultados = Arrays.asList(resultado1, resultado2, resultado3, resultado4);

        when(microApiClient.getResultados()).thenReturn(resultados);

        // Act
        ResultadoDto result = distritoService.getResultado(distritoId, seccionId);

        // Assert
        assertNotNull(result);
        assertEquals("Distrito 1", result.getDistrito());
        assertEquals("Seccion 1", result.getSeccion());

        List<ResultadosDto> partidosResultados = result.getResultados();
        assertEquals(2, partidosResultados.size());

        // Verificar que el partido con más votos esté primero
        ResultadosDto primerPartido = partidosResultados.get(0);
        assertEquals("Partido B", primerPartido.getNombre());
        assertEquals(150, primerPartido.getVotos());
        assertEquals(1L, primerPartido.getOrden());
        assertEquals(50.0, primerPartido.getPorcentaje(), 0.01);

        ResultadosDto segundoPartido = partidosResultados.get(1);
        assertEquals("Partido A", segundoPartido.getNombre());
        assertEquals(150, segundoPartido.getVotos());
        assertEquals(2L, segundoPartido.getOrden());
        assertEquals(50.0, segundoPartido.getPorcentaje(), 0.01);
    }
}