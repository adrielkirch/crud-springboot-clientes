package br.unisul.trabalho.springboot.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisul.trabalho.springboot.modelo.Endereco;
import br.unisul.trabalho.springboot.modelo.UnidadeFederativa;
import br.unisul.trabalho.springboot.services.EnderecoService;
import br.unisul.trabalho.springboot.services.EnderecoServiceImpl;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoApi {

	@Autowired
	private EnderecoService enderecoService;

	public EnderecoApi() {

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Endereco Endereco) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvar(Endereco));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obterPorId(@PathVariable(value = "id") Long id) {
		Optional<Endereco> novoEndereco = enderecoService.obterPorId(id);
		if(!novoEndereco.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Endereco não encontrado\"}");
		}
		return ResponseEntity.ok(novoEndereco);
	}
	
	@GetMapping()
	public List<Endereco> obterTodos(){
		List <Endereco> Enderecos = StreamSupport
				.stream(enderecoService.obterTodos().spliterator(), false)
				.collect(Collectors.toList());
		return Enderecos;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Endereco EnderecoBody,@PathVariable(value = "id") Long id){
		Optional<Endereco> Endereco = enderecoService.obterPorId(id);
		if(!Endereco.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Endereco não encontrado\"}");
		}
		Endereco.get().setLogradouro(EnderecoBody.getLogradouro());
		Endereco.get().setBairro(EnderecoBody.getBairro());
		Endereco.get().setCidade(EnderecoBody.getCidade());
		Endereco.get().setCep(EnderecoBody.getCep());
		Endereco.get().setUf(UnidadeFederativa.valueOf(EnderecoBody.getUf().toString()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvar(Endereco.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable(value = "id") Long id){
		Optional<Endereco> Endereco = enderecoService.obterPorId(id);
		if(!Endereco.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Endereco não encontrado\"}");
		}
		enderecoService.removerPorId(id);
		return ResponseEntity.ok().build();
	}
	
}
