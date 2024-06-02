package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

// TODOS OS METODOS DEVOLVENDO UM CORPO E UM CODIGO HTTP

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repositorio;

    // Request BODY sempre que for receber um corpo de fora
    // CADASTRAR
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){  //chamar este uri para o spring criar a uri
        Medico medico = new Medico(dados);
        repositorio.save(medico);

        // criando o objeto uri
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }


    // LISTAR
    // ?size=1 ----> controlar a quantidade  &page=1 ------> ir pegando de cada em cada
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> listarMedicos(@PageableDefault Pageable pageable) {
       var page = repositorio.findAllByAtivoTrue(pageable).map(DadosListagemMedicos::new);
       return ResponseEntity.ok(page);
    }


    // DETALHAR MEDICO
    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var medico = repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    // ATUALIZAR
    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repositorio.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }


    // DELETAR NAO PERMANENTE
    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity desativarMedico(@PathVariable Long id) {
        var medico = repositorio.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }


    // ATIVAR
    @DeleteMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity reativarMedico(@PathVariable Long id) {
        var medico = repositorio.getReferenceById(id);
        medico.ativar();
        return ResponseEntity.noContent().build();
    }
}
