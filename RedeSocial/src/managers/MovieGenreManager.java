package managers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import persistence.MovieGenreDAO;
import valueObjects.Genero;

public class MovieGenreManager {
	
	private static MovieGenreManager instance= null;
	private MovieGenreManager(){
		generosMap = new HashMap<String, Genero>();
	}
	
	public static MovieGenreManager getInstance(){
		if(instance == null){
			instance = new MovieGenreManager();
		}
		return instance;
	}
	
	private Map<String, Genero> generosMap;
	
	public void addGenero(Genero g){
		if(g!= null && g.getNome() != null && !generosMap.containsKey(g.getNome())){
			generosMap.put(g.getNome(), g);
		}		
	}
	
	public void persistGeneros() {
		MovieGenreDAO generoDAO = new MovieGenreDAO();
		for(Entry<String, Genero> entry: generosMap.entrySet()){
			generoDAO.save(entry.getValue());
			Genero g = generoDAO.findByName(entry.getKey());
			entry.getValue().setId(g.getId());
		}
	}
}
