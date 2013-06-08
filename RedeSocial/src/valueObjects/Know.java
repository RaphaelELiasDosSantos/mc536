package valueObjects;

import application.Constants;

public class Know implements IRecordable {
	private String loginPessoa, loginConhecido;
	
	public Know(String loginPessoa, String loginConhecido) {
		super();
		this.loginPessoa = loginPessoa;
		this.loginConhecido = loginConhecido;
	}

	@Override
	public String getTableName() {		
		return Constants.CONHECE_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.CONHECE_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		String fields = String.format("( %s,  %s)", "login_pessoa", "login_conhecido");
		return fields;
	}

	@Override
	public String getValues() {
		String values = String.format("( '%s', '%s')", this.loginPessoa, this.loginConhecido);
		return values;
	}

	@Override
	public String getRecordId() {		
		return null;
	}

	public String getLoginPessoa() {
		return loginPessoa;
	}

	public void setLoginPessoa(String loginPessoa) {
		this.loginPessoa = loginPessoa;
	}

	public String getLoginConhecido() {
		return loginConhecido;
	}

	public void setLoginConhecido(String loginConhecido) {
		this.loginConhecido = loginConhecido;
	}

}
