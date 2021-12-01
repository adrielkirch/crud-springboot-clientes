package br.unisul.trabalho.springboot.api;

import java.io.Console;
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

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.Endereco;
import br.unisul.trabalho.springboot.modelo.UnidadeFederativa;
import br.unisul.trabalho.springboot.services.ClienteService;
import br.unisul.trabalho.springboot.services.EnderecoService;
import br.unisul.trabalho.springboot.services.EnderecoServiceImpl;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoApi {

	@Autowired
	private EnderecoService enderecoService;
	@Autowired
	private ClienteService clienteService;

	public EnderecoApi() {

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
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Endereco endereco) {
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.salvar(endereco));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Endereco EnderecoBody,@PathVariable(value = "id") Long id){
		Optional<Endereco> endereco = enderecoService.obterPorId(id);
		if(!endereco.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Endereco não encontrado\"}");
		}
		endereco.get().setLogradouro(EnderecoBody.getLogradouro());
		endereco.get().setBairro(EnderecoBody.getBairro());
		endereco.get().setCidade(EnderecoBody.getCidade());
		endereco.get().setCep(EnderecoBody.getCep());
		endereco.get().setUf(UnidadeFederativa.valueOf(EnderecoBody.getUf().toString()));
	
		return ResponseEntity.status(HttpStatus.OK).body(enderecoService.salvar(endereco.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable(value = "id") Long id){
		Cliente cliente = enderecoService.obterClienteVinculado(id);
		if(cliente != null) {
			cliente.setEndereco(null);
			clienteService.salvar(cliente);
		}
		enderecoService.removerPorId(id);
		return ResponseEntity.ok().build();
	}
	
	
	
}
