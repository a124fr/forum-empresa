package br.com.empresa.forumempresa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {br.com.empresa.forum.empresa.ForumEmpresaApplication.class})
class ForumEmpresaApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
