package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@AutoConfigureMockMvc // injetar o mock mvc
@SpringBootTest  // test com conrollers
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;  // entrada
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamenoConsultaJson;  // saida
    @MockBean // não injete um agenda de consultas de verdade
    private AgendaDeConsultas agendaDeConsultas;



    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informações estão inválidas")
    @WithMockUser  // simular usuario logado
    void agendar_cenario1() throws Exception {
        var respose = mvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(respose.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }



    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informações estão válidas")
    @WithMockUser  // simular usuario logado
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 1l, data);

        when(agendaDeConsultas.agendar(any())).thenReturn(
                dadosDetalhamento);

        var respose = mvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson.write(
                                new DadosAgendamentoConsulta(2l,1l, data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(respose.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamenoConsultaJson.write(
                dadosDetalhamento
        ).getJson();

        assertThat(respose.getContentAsString()).isEqualTo(jsonEsperado);
    }





}