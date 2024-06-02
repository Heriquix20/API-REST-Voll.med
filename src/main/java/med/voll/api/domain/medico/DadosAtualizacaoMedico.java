package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;


// PARA ATUALIZAR DADOS DE UM MEDICO

public record DadosAtualizacaoMedico(

        @NotNull
        Long id,

        String telefone,
        String nome,
        DadosEndereco endereco) {
}
