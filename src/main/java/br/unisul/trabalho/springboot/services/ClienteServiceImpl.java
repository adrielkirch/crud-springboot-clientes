package br.unisul.trabalho.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.repositorio.ClienteRepositorio;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Iterable<Cliente> obterTodos() {
		return clienteRepositorio.findAll();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Optional<Cliente> obterPorId(Long id) {
		return clienteRepositorio.findById(id);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Cliente salvar(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void removerPorId(Long id) {
		clienteRepositorio.deleteById(id);
	}
	

}
