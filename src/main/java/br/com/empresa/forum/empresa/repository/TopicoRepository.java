package br.com.empresa.forum.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.empresa.forum.empresa.modelo.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);
	
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> carregarPorNomeDoCurso(String nomeCurso);
	
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nome")
	List<Topico> carregarPorNomeDoCurso2(@Param("nome") String nomeCurso);

}
