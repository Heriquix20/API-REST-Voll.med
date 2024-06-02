package med.voll.api.domain.medico;


// PARA DETALHAR UM MEDICO MAS SEM SER TODAS INFORMACOES

public record DadosListagemMedicos(
        Long id, String nome,
        String email,
        String crm,
        Especialidade especialidade) {


    // Construtor para voltar apena alguns atributos no GET
    public DadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
