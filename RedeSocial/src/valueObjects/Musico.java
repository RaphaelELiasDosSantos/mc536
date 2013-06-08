package valueObjects;

import java.util.List;

public class Musico {
	private int id;
	private String nome;
	private EstiloMusical estilo;

	//Um músico pode ser um cantor solo além de estar em uma banda
	private Cantor cantor;
	//um musico pode compor mais de de uma banda
	private List<Banda> bandas;
	
	public Musico(int id, String nome, EstiloMusical estilo, Cantor cantor,
			List<Banda> bandas) {
		super();
		this.id = id;
		this.nome = nome;
		this.estilo = estilo;
		this.cantor = cantor;
		this.bandas = bandas;
	}
	
	

}
