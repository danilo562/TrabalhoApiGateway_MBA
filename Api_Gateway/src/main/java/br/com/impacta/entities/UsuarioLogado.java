package br.com.impacta.entities;

import java.io.Serializable;

public class UsuarioLogado implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long user_id;
	private String name;
	private String email;
	private Long role_id;
	private String roleName;
	
	public UsuarioLogado() {}

	public UsuarioLogado(Long id, Long user_id, String name, String email, Long role_id, String roleName) {
		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.role_id = role_id;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
