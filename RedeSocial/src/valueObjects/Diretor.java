package valueObjects;

import application.Constants;

public class Diretor {
	private String nome;
	private int id;
	
	
	public Diretor(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	
	public Diretor(String nome){
		super();
		this.nome = nome;
		this.id = Constants.ID_DIRETOR_VAZIO;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}	
}
