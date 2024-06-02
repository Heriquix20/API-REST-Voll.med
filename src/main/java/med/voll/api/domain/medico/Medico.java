package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;


// CLASSE MEDICO PRINCIPAL

@Getter
@NoArgsConstructor  // construtor padrao
@AllArgsConstructor // construtos com todos os campos
@EqualsAndHashCode(of = "id")  // gerar o hashcode

@Entity(name = "Medico")
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded   // para mapear no banco
    private Endereco endereco;

    private boolean ativo;  // ATIVO OU NAO


    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;  // JA SETA COMO TRUE POIS ESTA CADASTRANDO
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    // ASSIM QUE FOR CHAMADO VAI EXCLUIR
    public void desativar() {
        this.ativo = false;
    }

    // ASSIM QUE FOR CHAMADO VAI ATIVAR
    public void ativar() {
        this.ativo = true;
    }

}
