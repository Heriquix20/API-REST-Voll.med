package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

// RECORD PARA O CANCELAMENTO
public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}