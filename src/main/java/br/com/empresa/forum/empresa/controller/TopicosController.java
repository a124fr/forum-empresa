package br.com.empresa.forum.empresa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.empresa.forum.empresa.controller.dto.DetalhesDoTopicoDTO;
import br.com.empresa.forum.empresa.controller.dto.TopicoDTO;
import br.com.empresa.forum.empresa.controller.form.AtuzalizacaoTopicoForm;
import br.com.empresa.forum.empresa.controller.form.TopicoForm;
import br.com.empresa.forum.empresa.modelo.Topico;
import br.com.empresa.forum.empresa.repository.CursoRepository;
import br.com.empresa.forum.empresa.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {

		List<Topico> topicos;
		if (nomeCurso == null) {
			topicos = this.topicoRepository.findAll();
		} else {
			topicos = this.topicoRepository.carregarPorNomeDoCurso2(nomeCurso);
		}

		return TopicoDTO.converter(topicos);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

		Topico topico = form.converter(this.cursoRepository);
		this.topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoTopicoDTO> detalhar(@PathVariable Long id) {
		
		Optional<Topico> optionalTopico = this.topicoRepository.findById(id);
		if (optionalTopico.isPresent()) {			
			return ResponseEntity.ok(new DetalhesDoTopicoDTO(optionalTopico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtuzalizacaoTopicoForm form) {
		
		Optional<Topico> optionalTopico = this.topicoRepository.findById(id);
		if (optionalTopico.isPresent()) {			
			Topico topico = form.atualizar(id, this.topicoRepository);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optionalTopico = this.topicoRepository.findById(id);
		if (optionalTopico.isPresent()) {	
			this.topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();			
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
