package br.unisul.trabalho.springboot.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.UnidadeFederativa;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c where c.endereco.cidade = :cidade and c.endereco.uf = :uf")
	List<Cliente> obterClientesPorCidadeEstado(@Param("cidade") String cidade, @Param("uf") UnidadeFederativa uf);
}
