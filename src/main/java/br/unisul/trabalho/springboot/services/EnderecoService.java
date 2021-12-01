package br.unisul.trabalho.springboot.services;

import java.util.Optional;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.Endereco;

public interface EnderecoService {
	
	public Iterable<Endereco> obterTodos();
	
	public Optional<Endereco> obterPorId(Long id);
	
	public Endereco salvar(Endereco Endereco);
	
	public void removerPorId(Long id);
	
	public Cliente obterClienteVinculado(Long id);
}
