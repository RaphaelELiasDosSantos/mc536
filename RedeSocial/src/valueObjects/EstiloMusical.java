package valueObjects;

import application.Constants;

public class EstiloMusical implements IRecordable {
	private int id;
	private String nome;
	
	public EstiloMusical(String nome) {
		super();		
		setNome(nome);
	}
	public EstiloMusical(String nome, int id) {
		super();		
		setNome(nome);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome.replace("'", "");
	}

	@Override
	public String getTableName() {		
		return Constants.ESTILO_MUSICAL_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {		
		return Constants.ESTILO_MUSICAL_TABLE_ID_FIELD_NAME;
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
