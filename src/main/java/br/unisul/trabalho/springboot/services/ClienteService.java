package br.unisul.trabalho.springboot.services;

import java.util.Optional;

import br.unisul.trabalho.springboot.modelo.Cliente;

public interface ClienteService {
	
	public Iterable<Cliente> obterTodos();
	
	public Optional<Cliente> obterPorId(Long id);
	
	public Cliente salvar(Cliente cliente);
	
	public void removerPorId(Long id);
	
}
