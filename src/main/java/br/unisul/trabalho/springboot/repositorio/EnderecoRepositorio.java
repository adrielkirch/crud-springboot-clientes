package br.unisul.trabalho.springboot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.Endereco;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long>{

}
