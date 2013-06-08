package valueObjects;

import java.util.List;

import application.Constants;


public class Pessoa implements IRecordable{
	private final static String default_uri = "http://www.ic.unicamp.br/MC536/2013/1/";
	private int id;
	private String nome, login, cidade;
	private List<Pessoa> conhecidos;
		
	public Pessoa(int id, String nome, String login, String cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.cidade = cidade;
	}
	
	@Override
	public String getTableName() {
		return Constants.PESSOA_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.PESSOA_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		String fields = String.format("( %s,  %s, %s)", "nome", "login", "cidade");
		return fields;
	}

	@Override
	public String getValues() {
		String values = String.format("( '%s', '%s', '%s')", this.nome, this.login, this.cidade);
		return values;
	}

	@Override
	public String getRecordId() {
		return String.valueOf(this.id);
	}
	
	public int getId() {
		return id;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public static String getDefaultUri() {
		return default_uri;
	}
}
