package br.edu.ifg.academico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifg.academico.entities.Aluno;
import br.edu.ifg.academico.exceptions.ResourceNotFoundException;
import br.edu.ifg.academico.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRep;

	public List<Aluno> findAll() {
		return alunoRep.findAll();
	}

	public Aluno findById(Integer id) {
		return alunoRep.findById(id)
					   .orElseThrow(
							   () -> new ResourceNotFoundException(id)
					   );
	}

	public Aluno insert(Aluno a) {
		return alunoRep.save(a);
	}

	public void delete(Integer id) {
		alunoRep.deleteById(id);
	}

	public Aluno update(Integer id, Aluno alunoAlt) {
		return alunoRep.findById(id).map(
										alunoDB -> {
											alunoDB.setNome(alunoAlt.getNome());
											alunoDB.setSexo(alunoAlt.getSexo());
											alunoDB.setDt_nasc(alunoAlt.getDt_nasc());
											return alunoRep.save(alunoDB);
										}
					)
					.orElseThrow(
							() -> new ResourceNotFoundException(id)
					);
	}
}
