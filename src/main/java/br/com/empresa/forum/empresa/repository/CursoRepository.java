package br.com.empresa.forum.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.empresa.forum.empresa.modelo.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

	Curso findByNome(String nomeCurso);
}
