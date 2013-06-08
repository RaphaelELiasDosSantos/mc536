package valueObjects;

import java.util.ArrayList;
import java.util.List;

import application.Constants;


public class MusicalAct implements IRecordable {
	private int id, playCount, listeners;
	private String nome, pais, mbid;
	private List<EstiloMusical> estilosMusicais;
	
	public MusicalAct(String nome){
		this.nome = nome;		
		estilosMusicais = new ArrayList<EstiloMusical>();
	}
	
	public MusicalAct(int id, String nome, String pais,
			List<EstiloMusical> estilosMusicais) {
		super();
		this.id = id;
		this.nome = nome;
		this.pais = pais;		
		this.estilosMusicais = estilosMusicais;
	}
	
	public void addEstiloMusical(EstiloMusical estilo){
		if(!estilosMusicais.contains(estilo)){
			estilosMusicais.add(estilo);
		}		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<EstiloMusical> getEstilosMusicais() {
		return estilosMusicais;
	}

	public void setEstiloMusical(List<EstiloMusical> estilosMusicais) {
		this.estilosMusicais = estilosMusicais;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}

	public int getListeners() {
		return listeners;
	}

	public void setListeners(int listeners) {
		this.listeners = listeners;
	}

	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	@Override
	public String getTableName() {
		return Constants.ATO_MUSICAL_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.ATO_MUSICAL_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		
		return null;
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordId() {
		// TODO Auto-generated method stub
		return null;
	}
}
