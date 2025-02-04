package br.com.LeoChiarelli.api.domain.controller;

import br.com.LeoChiarelli.api.domain.dto.AtualizarConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.CadastrarConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.DetalhesConsultaDTO;
import br.com.LeoChiarelli.api.domain.dto.ListaConsultasDTO;
import br.com.LeoChiarelli.api.domain.service.ConsultasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ConsultasControllerTest {

    @InjectMocks
    private ConsultasController controller;

    @Mock
    private ConsultasService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Cadastrar consulta deve retornar 201 quando consulta cadastrada")
    void cadastrarConsulta_deveRetornar201QuandoConsultaCadastrada() {
        // Arrange
        CadastrarConsultaDTO dto = new CadastrarConsultaDTO(1L, 1L, 1L, LocalDateTime.now().plusDays(1), "Detalhes");
        DetalhesConsultaDTO detalhesConsultaDTO = new DetalhesConsultaDTO(1L, "Pet", "Veterinário", dto.dataHora(), dto.detalhes(), null);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        when(service.agendar(dto)).thenReturn(detalhesConsultaDTO);

        // Act
        ResponseEntity<DetalhesConsultaDTO> response = controller.cadastrarConsulta(dto, uriBuilder);

        // Assert
        assertEquals(201, response.getStatusCode().value());
        assertEquals(detalhesConsultaDTO, response.getBody());
        verify(service).agendar(dto);
    }

    @Test
    @DisplayName("Mudar horário deve retornar 200 quando horário alterado")
    void mudarHorario_deveRetornar200QuandoHorarioAlterado() {
        // Arrange
        AtualizarConsultaDTO dto = new AtualizarConsultaDTO(1L, LocalDateTime.now().plusDays(1), "Detalhes");
        DetalhesConsultaDTO detalhesConsultaDTO = new DetalhesConsultaDTO(1L, "Pet", "Veterinário", dto.dataHora(), dto.detalhes(), null);

        when(service.mudarHorario(dto)).thenReturn(detalhesConsultaDTO);

        // Act
        ResponseEntity<DetalhesConsultaDTO> response = controller.mudarHorario(dto);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(detalhesConsultaDTO, response.getBody());
        verify(service).mudarHorario(dto);
    }

    @Test
    @DisplayName("Confirmar consulta deve retornar 200 quando consulta confirmada")
    void confirmarConsulta_deveRetornar200QuandoConsultaConfirmada() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = controller.confirmarConsulta(id);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Consulta confirmada", response.getBody());
        verify(service).confirmar(id);
    }

    @Test
    @DisplayName("Concluir consulta deve retornar 204 quando consulta concluída")
    void concluirConsulta_deveRetornar204QuandoConsultaConcluida() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<?> response = controller.concluirConsulta(id);

        // Assert
        assertEquals(204, response.getStatusCode().value());
        verify(service).concluir(id);
    }

    @Test
    @DisplayName("Listar consultas deve retornar 200 quando consultas listadas")
    void listarConsultas_deveRetornar200QuandoConsultasListadas() {
        // Arrange
        Long idVeterinario = 1L;
        Pageable pageable = Pageable.ofSize(10);
        Page<ListaConsultasDTO> page = mock(Page.class);
        when(service.listar(idVeterinario, pageable)).thenReturn(page);

        // Act
        ResponseEntity<Page<ListaConsultasDTO>> response = controller.listarConsultas(idVeterinario, pageable);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(page, response.getBody());
        verify(service).listar(idVeterinario, pageable);
    }

    @Test
    @DisplayName("Detalhar consulta deve retornar 200 quando consulta detalhada")
    void detalharConsulta_deveRetornar200QuandoConsultaDetalhada() {
        // Arrange
        Long id = 1L;
        DetalhesConsultaDTO detalhesConsultaDTO = new DetalhesConsultaDTO(1L, "Pet", "Veterinário", LocalDateTime.now(), "Detalhes", null);
        when(service.detalhar(id)).thenReturn(detalhesConsultaDTO);

        // Act
        ResponseEntity<DetalhesConsultaDTO> response = controller.detalharConsulta(id);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(detalhesConsultaDTO, response.getBody());
        verify(service).detalhar(id);
    }

    @Test
    @DisplayName("Cancelar consulta deve retornar 204 quando consulta cancelada")
    void cancelarConsulta_deveRetornar204QuandoConsultaCancelada() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<?> response = controller.cancelarConsulta(id);

        // Assert
        assertEquals(204, response.getStatusCode().value());
        verify(service).cancelar(id);
    }
}