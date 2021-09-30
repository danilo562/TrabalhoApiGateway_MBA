package br.com.impacta.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name="cliente")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Transient
	private String porta;
    
    @Column(nullable = false)
	private String nome;
    
   
    @Column(nullable = false)
	private String datanasc;

	public Cliente(Long id, String porta, String nome, String datanasc) {
		this.id = id;
		this.porta = porta;
		this.nome = nome;
		this.datanasc = datanasc;
	}

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(String datanasc) {
		this.datanasc = datanasc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(datanasc, id, nome, porta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(datanasc, other.datanasc) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(porta, other.porta);
	}
	

	
	

}
