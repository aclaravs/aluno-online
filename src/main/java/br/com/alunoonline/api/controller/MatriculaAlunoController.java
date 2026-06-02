package br.com.alunoonline.api.controller;


import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService matriculaAlunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarMatricula(@RequestBody MatriculaAluno matriculaAluno) {
        matriculaAlunoService.criarMatricula(matriculaAluno);
    }

    @GetMapping("/aluno/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MatriculaAluno> listarMatriculasPorAluno(@PathVariable Long alunoId) {
        return matriculaAlunoService.listarMatriculasPorAluno(alunoId);
    }

    @PatchMapping("/trancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void trancarMatricula(@PathVariable Long id) {
        matriculaAlunoService.trancarMatricula(id);
    }

    @PatchMapping("/destrancar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destrancarMatricula(@PathVariable Long id) {
        matriculaAlunoService.destrancarMatricula(id);
    }

    @PatchMapping("/atualizarNotas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarNotas(@PathVariable Long id, @RequestBody AtualizarNotasRequestDTO dto) {
        matriculaAlunoService.atualizarNotas(id, dto);
    }

    @GetMapping("/emitirHistorico/{alunoId}")
    @ResponseStatus(HttpStatus.OK)
    public HistoricoAlunoResponseDTO emitirHistorico(
            @PathVariable Long alunoId) {
        return matriculaAlunoService.emitirHistorico(alunoId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMatriculaPorId(@PathVariable Long id) {
        matriculaAlunoService.deletarMatriculaPorId(id);
    }
}