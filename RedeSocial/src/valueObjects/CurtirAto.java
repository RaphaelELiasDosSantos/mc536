package valueObjects;

import application.Constants;

public class CurtirAto implements IRecordable {
	private String login, ato;
	private int rating;
	

	public CurtirAto(String login, String ato, int rating) {
		super();
		this.login = login;
		this.ato = ato;
		this.rating = rating;
	}

	@Override
	public String getTableName() {
		return Constants.CURTIR_ATOS_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.CURTIR_ATOS_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		String fields = String.format("( %s,  %s, %s)", "login", "ato", "rating");
		return fields;
	}

	@Override
	public String getValues() {
		String values= String.format("( '%s', '%s', %d)", this.login, this.ato, this.rating);
		return values;
	}

	@Override
	public String getRecordId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAto() {
		return ato;
	}

	public void setAto(String ato) {
		this.ato = ato;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}	
}
