package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorMedicoOutraConsultaMesmoHorario implements ValidadorAgendamentoDeConsultas{


    @Autowired
    private ConsultaRepository consultaRepository;


    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaMesmoHorario) {
            throw new ValidacaoException("Medico ja possui outra consulta neste horario");
        }
    }
}
