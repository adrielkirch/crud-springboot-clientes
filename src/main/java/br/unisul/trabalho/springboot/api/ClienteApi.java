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

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.Endereco;
import br.unisul.trabalho.springboot.repositorio.EnderecoRepositorio;
import br.unisul.trabalho.springboot.services.ClienteService;
import br.unisul.trabalho.springboot.services.ClienteServiceImpl;
import br.unisul.trabalho.springboot.services.EnderecoService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteApi {

	@Autowired
	private ClienteService clinteService;
	@Autowired
	private EnderecoService enderecoService;

	public ClienteApi() {

	}

	

	@GetMapping("/{id}")
	public ResponseEntity<?> obterPorId(@PathVariable(value = "id") Long id) {
		Optional<Cliente> novoCliente = clinteService.obterPorId(id);
		if (!novoCliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Cliente não encontrado\"}");
		}
		return ResponseEntity.ok(novoCliente);
	}
	
	
	@GetMapping()
	public List<Cliente> obterTodos() {
		List<Cliente> clientes = StreamSupport.stream(clinteService.obterTodos().spliterator(), false)
				.collect(Collectors.toList());
		return clientes;
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cliente cliente) {
		Endereco endereco = endereco = new Endereco();
		endereco.setBairro(cliente.getEndereco().getBairro());
		endereco.setCep(cliente.getEndereco().getCep());
		endereco.setCidade(cliente.getEndereco().getCidade());
		endereco.setUf(cliente.getEndereco().getUf());
		endereco.setLogradouro(cliente.getEndereco().getLogradouro());

		enderecoService.salvar(endereco);
		cliente.setEndereco(endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(clinteService.salvar(cliente));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@RequestBody Cliente clienteBody, @PathVariable(value = "id") Long id) {
		Optional<Cliente> cliente = clinteService.obterPorId(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Cliente não encontrado\"}");
		}
		cliente.get().setNome(clienteBody.getNome());
		cliente.get().setNumero(clienteBody.getNumero());
		cliente.get().setComplemento(clienteBody.getComplemento());

		return ResponseEntity.status(HttpStatus.CREATED).body(clinteService.salvar(cliente.get()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable(value = "id") Long id) {
		Optional<Cliente> cliente = clinteService.obterPorId(id);
		if (!cliente.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Cliente não encontrado\"}");
		}
		clinteService.removerPorId(id);
		return ResponseEntity.ok().build();
	}

}
