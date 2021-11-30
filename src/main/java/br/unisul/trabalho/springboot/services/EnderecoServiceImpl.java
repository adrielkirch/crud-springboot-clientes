package br.unisul.trabalho.springboot.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.trabalho.springboot.modelo.Endereco;
import br.unisul.trabalho.springboot.repositorio.EnderecoRepositorio;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepositorio EnderecoRepositorio;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Iterable<Endereco> obterTodos() {
		return EnderecoRepositorio.findAll();
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Optional<Endereco> obterPorId(Long id) {
		return EnderecoRepositorio.findById(id);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Endereco salvar(Endereco Endereco) {
		return EnderecoRepositorio.save(Endereco);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public void removerPorId(Long id) {
		EnderecoRepositorio.deleteById(id);
	}
	

}
