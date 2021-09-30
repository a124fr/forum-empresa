package br.com.empresa.forum.empresa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.empresa.forum.empresa.modelo.Curso;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE) -> Em caso de eu quero utilizar o banco mysql
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveriaCarregarUmObjetoCursoAoBuscarPeloSeuNome() {
		String nomeCurso = "Spring Boot";
				
		Curso spring = new Curso();
		spring.setNome(nomeCurso);
		spring.setCategoria("Programação");		
		em.persist(spring);				
		
		Curso curso = this.cursoRepository.findByNome(nomeCurso);
		
		assertNotNull(curso);
		assertEquals(nomeCurso, curso.getNome());
	}
	
	@Test
	void naoDeveriaCarregarUmObjetoCursoCujoNomeNaoEstejaCadastrado() {
		String nomeCurso = "JPA";
		Curso curso = this.cursoRepository.findByNome(nomeCurso);
		
		assertNull(curso);		
	}
}
