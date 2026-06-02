package br.com.alunoonline.api.service;



import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.Disciplina;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.DisciplinaRepository;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void criarDisciplina(Disciplina disciplina) {
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarDisciplinasDoProf(Long professorId) {
        return disciplinaRepository.findByProfessorId(professorId);
    }

    public List<Disciplina> listarTodasDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public List<Disciplina> listarDisciplinasDoAluno(Long alunoId) {
        return matriculaAlunoRepository.findByAlunoId(alunoId)
                .stream()
                .map(MatriculaAluno::getDisciplina)
                .toList();
    }

    public Optional<Disciplina> buscarDisciplinaPorId(Long id) {
        return disciplinaRepository.findById(id);
    }

    public void atualizarDisciplina(Long id, Disciplina disciplina) {
        disciplina.setId(id);
        disciplinaRepository.save(disciplina);
    }

    public void deletarDisciplinaPorId(Long id) {
        disciplinaRepository.deleteById(id);
    }
}
