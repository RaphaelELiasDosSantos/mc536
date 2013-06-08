package managers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import externSources.IRequest;
import externSources.ImdbRequest;
import externSources.TheMovieDBRequest;

import persistence.MusicalActDAO;
import persistence.MovieDAO;
import valueObjects.MusicalAct;
import valueObjects.EstiloMusical;
import valueObjects.Film;
import valueObjects.Genero;
import valueObjects.IRecordable;
import valueObjects.Pair;

public class MovieManager {
	private static MovieManager instance = null;
	private Map<String, Film> filmesMap;
	private List<Film> filmesList;
	
	
	private MovieManager(){
		filmesMap = new HashMap<String, Film>();
		filmesList = new ArrayList<Film>();
	}
	
	public static MovieManager getInstance(){
		if (instance == null){
			instance = new MovieManager();
		}
		return instance;		
	}
	
	public void addFilme(String nome, Film f){
		if(f != null && !filmesMap.containsKey(nome)){
			filmesMap.put(nome, f);
			filmesList.add(f);
		}		
	}
	
	private void persistFilme(Film f){
		MovieDAO filmeDAO = new MovieDAO();
		filmeDAO.save(f);
	}
	
	public void persistFilmes(){
		MovieDAO filmeDAO = new MovieDAO();
		List<IRecordable> filmes = new ArrayList<IRecordable>();
		for(Film f: filmesList){
			filmes.add(f);
		}
		filmeDAO.save(filmes);		
	}
	
	public void persistGeneroFilme(){
		List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
		for(Film filme: filmesList){
			for(Genero genero: filme.getGeneros()){
				Pair<Integer, Integer> p = new Pair<Integer, Integer>(filme.getId(), genero.getId());
			}
		}
		MovieDAO filmesDAO = new MovieDAO();
		filmesDAO.saveGenero(pairs);
	}
	
	public void loadAll(){
		
	}
	
	
	public void findInIMDB(){
		IRequest<Film> imdb = new ImdbRequest();		
		for(Film f: filmesList){
			imdb.findBasicInformations(f);

		}		
	}
	
	public void findInTheMovieDB(){
		IRequest<Film> theMovieDB = new TheMovieDBRequest();
		for(Film f: filmesList){
			theMovieDB.findBasicInformations(f);
		}				
	}

}
