package valueObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.Constants;
import application.SocialNetworkUtils;


public class Film implements IRecordable{
	private int id;
	private float externalRating;
	private String nome, sinopse, imdbId, country;
	private Date data_lancamento;
	private List<Diretor> diretores;
	private List<Genero> generos;
		
	public Film(int id, String nome, Date data_lancamento, List<Diretor> diretores,
			List<Genero> generos, String imdbId) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_lancamento = data_lancamento;
		this.diretores = diretores;
		this.generos = generos;
		this.imdbId = imdbId;
	}
	
	public Film (String imdbId){
		super();
		this.imdbId = imdbId;
		this.nome = null;
		this.sinopse= null;
		this.data_lancamento = null;
		this.country = null;
		this.externalRating = 0f;
		this.diretores = new ArrayList<Diretor>();
		this.generos = new ArrayList<Genero>();
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
	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		
		this.sinopse = sinopse;
	}

	public Date getData_lancamento() {
		return data_lancamento;
	}
	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}
	public List<Diretor> getDiretores() {
		return diretores;
	}
	
	public void addDiretor(Diretor d){
		if (d != null){
			diretores.add(d);
		}
	}
	
	public void addGenero(Genero g){
		if(g != null){
			generos.add(g);
		}
	}
	
	public List<Genero> getGeneros() {
		return generos;
	}
	

	@Override
	public String getTableName() {
		return Constants.FILME_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.FILME_TABLE_ID_FIELD_NAME;	
	}
	
	@Override
	public String getFieldsName() {
		String fields = String.format("( %s, %s, %s, %s, %s, %s)", "nome",
				"data_lancamento", "sinopse", "imdb_id", "external_rating", "country");
		return fields;
	}

	@Override
	public String getValues() {
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		
		//nome
		if(this.nome==null){ sb.append("null");	}
		else{ sb.append("'"+this.nome.replace("'", " ")+"'"); }
		sb.append(", ");
		
		//data lançamento
		if(this.data_lancamento==null){ sb.append("null");	}
		else{ sb.append("'"+SocialNetworkUtils.convertDate(this.data_lancamento)+"'"); }
		sb.append(", ");
					
		//sinopse
		if(this.sinopse==null){ sb.append("null");	}
		else{ sb.append("'"+this.sinopse.replace("'", " ")+"'"); }
		sb.append(", ");
		
		//imdb_id
		if(this.imdbId==null){ sb.append("null");	}
		else{ sb.append("'"+this.imdbId+"'"); }		
		sb.append(", ");
		
		//external rating
		sb.append("'"+this.externalRating+"'");
		sb.append(", ");
		
		//country
		if(this.country==null){sb.append("null");}
		else{sb.append("'"+this.country.replace("'", " ")+"'");}
		
		sb.append(")");
				
		return sb.toString();
	}

	
	@Override
	public String getRecordId() {
		return null;
	}

	public float getExternalRating() {
		return externalRating;
	}

	public void setExternalRating(float externalRating) {
		this.externalRating = externalRating;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String separator = " - ";
		
		sb.append(this.nome);
		sb.append(separator);
		
		sb.append(this.country);
		sb.append(separator);
		
		sb.append(this.externalRating);
		sb.append(separator);
		
		sb.append("[ ");
		for(Genero g: this.generos){
			sb.append(g.getNome());
			sb.append(", ");
		}
		sb.append("] ");
		sb.append(separator);
		
		sb.append(this.sinopse);
		
		return sb.toString();
	}
}
