package valueObjects;

import application.Constants;

public class Genero implements IRecordable {
	private String nome;
	private int id;
		
	public Genero(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
	
	public Genero(String nome){
		super();
		this.nome = nome;
		this.id = Constants.ID_GENERO_VAZIO;
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
	public void setId(int id){
		this.id = id;
	}

	@Override
	public String getTableName() {
		return Constants.GENERO_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {		
		return Constants.GENERO_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		String fields = String.format("(%s)", "nome");
		return fields;
	}

	@Override
	public String getValues() {
		String values = String.format(" ('%s') ", this.nome);
		return values;
	}

	@Override
	public String getRecordId() {		
		return String.valueOf(this.id);
	}
}
