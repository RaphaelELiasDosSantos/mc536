package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import persistence.MusicalStyleDAO;
import valueObjects.EstiloMusical;

public class MusicalStyleManager {
	private static MusicalStyleManager instance = null;
	private MusicalStyleManager(){
		estilosMap = new HashMap<String, EstiloMusical>();
		estilosList = new ArrayList<EstiloMusical>();
	}
	
	private Map<String, EstiloMusical> estilosMap;
	private List<EstiloMusical> estilosList;
	
	public static MusicalStyleManager getInstance(){
		if(instance == null){
			instance = new MusicalStyleManager();
		}
		return instance;
	}
	
	public void addEstilo(String nome, EstiloMusical estilo){
		if(nome != null && estilo != null && !estilosMap.containsKey(nome)){
			estilosMap.put(nome, estilo);
			estilosList.add(estilo);
		}
	}
	
	public EstiloMusical createEstilo(String nomeEstilo){
		EstiloMusical estilo = estilosMap.get(nomeEstilo);
		
		if(estilo == null){
			estilo = new EstiloMusical(nomeEstilo);
			addEstilo(nomeEstilo, estilo);
		}		
		return estilo;
	}
	
	public void persistEstilos() {
		MusicalStyleDAO estiloDAO = new MusicalStyleDAO ();
		for(Entry<String, EstiloMusical> entry: estilosMap.entrySet()){
			if(entry.getValue() !=null && entry.getKey()!=null && entry.getValue().getNome().length() < 40){
				estiloDAO.save(entry.getValue());
				EstiloMusical e = estiloDAO.findByName(entry.getValue().getNome());
				entry.getValue().setId(e.getId());
			}
		}
	}
	
}
