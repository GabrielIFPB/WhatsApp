package com.inteligenciadigital.whatsapp.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.inteligenciadigital.whatsapp.firebase.ConfiguracaoFirebase;

public class Usuario {
	private String id, nome, email, senha, tipo;

	public Usuario() {
	}

	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Usuario(String nome, String email, String senha, String tipo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Usuario(String id, String nome, String email, String senha, String tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public void salvar() {
		DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseBanco();
		firebase.child("usuarios")
				.child(this.getId())
				.setValue(this);
	}

	@Exclude
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Exclude
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
