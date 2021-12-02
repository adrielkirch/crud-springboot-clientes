package br.unisul.trabalho.springboot.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.unisul.trabalho.springboot.modelo.Cliente;
import br.unisul.trabalho.springboot.modelo.UnidadeFederativa;
import br.unisul.trabalho.springboot.repositorio.ClienteRepositorio;
import br.unisul.trabalho.springboot.repositorio.EnderecoRepositorio;

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

	@Override
	public String obterClientesPorCidadeEstado(String cidade, String uf)  {
		if( cidade == null || uf == null) {
			return null;
		}
		
		Gson gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		JsonObject mainJson = new JsonObject();
		mainJson.addProperty("cidade", cidade);
		mainJson.addProperty("uf", uf);
		
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) clienteRepositorio.obterClientesPorCidadeEstado(cidade, UnidadeFederativa.valueOf(uf));
		String strJson = gsonBuilder.toJson(listaClientes);
		
		JsonElement jsonElement = new JsonElement() {
			@Override
			public JsonElement deepCopy() {
				return null;
			}
		};
		
		jsonElement = JsonParser.parseString(strJson);
		mainJson.add("clientes", jsonElement);
		strJson = gsonBuilder.toJson(mainJson);
		return strJson;
	}

	


}
