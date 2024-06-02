package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;


// PARA DETALHAR APENAS UM UNICO MEDICO POIS O OUTRO ESTA COM AS VALIDACOES

public record DadosDetalhamentoMedico(
        Long id, String nome,
        String email, String crm,
        String telefone, Especialidade especialidade,
        Endereco endereco) {


    // CONSTRUTOR
    public DadosDetalhamentoMedico (Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
                medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}