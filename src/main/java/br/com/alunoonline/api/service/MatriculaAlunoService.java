package br.com.alunoonline.api.service;


import br.com.alunoonline.api.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.dtos.DisciplinasAlunoResponseDTO;
import br.com.alunoonline.api.dtos.HistoricoAlunoResponseDTO;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    @Autowired
    AlunoRepository alunoRepository;

    public void criarMatricula(MatriculaAluno matriculaAluno) {

        Aluno aluno = alunoRepository.findById(matriculaAluno.getAluno().getId())
                .orElseThrow();

        matriculaAluno.setAluno(aluno);
        matriculaAluno.setNomeAluno(aluno.getNomeCompleto());
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);

        matriculaAlunoRepository.save(matriculaAluno);
    }

    private static final Double MEDIA_PARA_APROVACAO = 7.0;

    public void atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {

        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Matricula não encontrada"));

        if (dto.getNota1() != null)
            matriculaAluno.setNota1(dto.getNota1());
        if (dto.getNota2() != null)
            matriculaAluno.setNota2(dto.getNota2());

        if (matriculaAluno.getNota1() != null
                && matriculaAluno.getNota2() != null) {
            Double media = (matriculaAluno.getNota1()
                    + matriculaAluno.getNota2()) / 2;
            matriculaAluno.setStatus(
                    media >= MEDIA_PARA_APROVACAO
                            ? MatriculaAlunoStatusEnum.APROVADO
                            : MatriculaAlunoStatusEnum.REPROVADO);
        }

        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long id) {

        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Matricula não encontrada"));

        if (matriculaAluno.getStatus()
                .equals(MatriculaAlunoStatusEnum.MATRICULADO)) {
            matriculaAluno.setStatus(
                    MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Só é possível trancar com status MATRICULADO");
        }
    }

    public void destrancarMatricula(Long id) {

        MatriculaAluno matriculaAluno =
                matriculaAlunoRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Matrícula não encontrada"));

        if (matriculaAluno.getStatus().equals(MatriculaAlunoStatusEnum.TRANCADO)) {

            matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
            matriculaAlunoRepository.save(matriculaAluno);

        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Só é possível destrancar matrícula com o status TRANCADO");
        }
    }

    public List<MatriculaAluno> listarMatriculasPorAluno(Long alunoId) {
        return matriculaAlunoRepository.findByAlunoId(alunoId);
    }

    public HistoricoAlunoResponseDTO emitirHistorico(Long alunoId) {

        // 1) Buscar todas as matrículas desse aluno
        List<MatriculaAluno> matriculas =
                matriculaAlunoRepository.findByAlunoId(alunoId);

        if (matriculas.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nenhuma matrícula encontrada para esse aluno");
        }

        Aluno aluno = matriculas.get(0).getAluno();

        List<DisciplinasAlunoResponseDTO> disciplinas =
                new ArrayList<>();

        for (MatriculaAluno matricula : matriculas) {
            DisciplinasAlunoResponseDTO disc =
                    new DisciplinasAlunoResponseDTO();

            disc.setNomeDisciplina(matricula.getDisciplina().getNome());
            disc.setNomeProfessor(
                    matricula.getDisciplina().getProfessor().getNomeCompleto());
            disc.setNota1(matricula.getNota1());
            disc.setNota2(matricula.getNota2());

            if (matricula.getNota1() != null
                    && matricula.getNota2() != null) {
                Double media = (matricula.getNota1()
                        + matricula.getNota2()) / 2;
                disc.setMedia(media);
            }

            disc.setStatus(matricula.getStatus());
            disciplinas.add(disc);
        }

        HistoricoAlunoResponseDTO historico = new HistoricoAlunoResponseDTO();
        historico.setNomeAluno(aluno.getNomeCompleto());
        historico.setEmailAluno(aluno.getEmail());
        historico.setCpfAluno(aluno.getCpf());
        historico.setDisciplinas(disciplinas);

        return historico;
    }

    public void deletarMatriculaPorId(Long id) {
        matriculaAlunoRepository.deleteById(id);
    }

}
