package br.unisul.trabalho.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.Endereco;
import br.unisul.trabalho.springboot.repositorio.ClienteRepositorio;
import br.unisul.trabalho.springboot.repositorio.EnderecoRepositorio;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Iterable<Endereco> obterTodos() {
		return enderecoRepositorio.findAll();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Optional<Endereco> obterPorId(Long id) {
		return enderecoRepositorio.findById(id);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Endereco salvar(Endereco Endereco) {
		return enderecoRepositorio.save(Endereco);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void removerPorId(Long id) {
		enderecoRepositorio.deleteById(id);
	}

	@Override
	public Cliente obterClienteVinculado(Long id) {
		List<Cliente> clientes = clienteRepositorio.findAll();
		Optional<Endereco> endereco = enderecoRepositorio.findById(id);
		
		if(!endereco.isPresent()) {
			return null;
		}
		
		for (Cliente cliente : clientes) {
			if(cliente.getEndereco().getId() == id) {
				return  cliente;
			}
		}
		return null;
	}
	
	
	

}
