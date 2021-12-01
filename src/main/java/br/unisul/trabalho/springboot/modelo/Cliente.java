package br.unisul.trabalho.springboot.modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity 
@Table (name = "cliente")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -1231391146194508024L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String complemento;
	private Integer numero;
	
	@OneToOne
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
	private Endereco endereco;

	public Cliente() {

	}

	public Cliente(Long id, String nome, String complemento, Integer numero, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.complemento = complemento;
		this.numero = numero;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente{" + "id=" + id + ", nome='" + nome + '\'' + ", complemento='" + complemento + '\'' + ", numero="
				+ numero + ", endereco=" + endereco + '}';
	}

}