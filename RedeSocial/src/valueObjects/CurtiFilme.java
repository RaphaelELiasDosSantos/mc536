package valueObjects;

import application.Constants;

public class CurtiFilme implements IRecordable {
	private String login, filme;
	private int rating;
	
	public CurtiFilme(String login, String filme, int rating) {
		super();
		this.login = login;
		this.filme = filme;
		this.rating = rating;
	}

	@Override
	public String getTableName() {		
		return Constants.CURTIR_FILME_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.CURTIR_ATOS_TABLE_ID_FIELD_NAME;
	}
	
	@Override
	public String getFieldsName() {
		String fields = String.format("( %s,  %s, %s)", "login", "filme", "rating");
		return fields;
	}

	@Override
	public String getValues() {
		String values= String.format("( '%s', '%s', %d)", this.login, this.filme, this.rating);
		return values;
	}
	
	@Override
	public String getRecordId() {
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFilme() {
		return filme;
	}

	public void setFilme(String filme) {
		this.filme = filme;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
