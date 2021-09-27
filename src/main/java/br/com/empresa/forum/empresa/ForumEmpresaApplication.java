package br.com.empresa.forum.empresa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ForumEmpresaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumEmpresaApplication.class, args);
	}

}
