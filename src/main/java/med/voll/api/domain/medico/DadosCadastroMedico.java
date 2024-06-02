package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;


// PARA CADASTRAR UM MEDICO E VALIDAR TODOS OS ATRIBUTOS

public record DadosCadastroMedico(

// VALIDACOES

        @NotBlank(message = "Nome é obrigatório")  // ---> APENAS STRING
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "email.invalido") // validar email
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        @NotBlank(message = "crm é obrigatório")
        @Pattern(regexp = "\\d{4,6}", message = "Formato de cep inserido é inválido")  // expressao regular para validar
        String crm,

        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid
        DadosEndereco endereco) {
}
