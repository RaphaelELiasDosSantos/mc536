package externSources;

import java.io.IOException;
import java.util.List;
import org.json.JSONException;

import valueObjects.Film;

import application.Constants;

import net.sf.jtmdb.GeneralSettings;


/**
 * Classe que gerencia as requisições para o TheMovieDB.
 */
public class TheMovieDBRequest implements IRequest<Film> {
	
	@Override
	public void findBasicInformations(Film t) {
		if(t == null || t.getNome()==null){ return;}
		GeneralSettings.setApiKey(Constants.THE_MOVIE_DB_KEY);
		try {			
			List<net.sf.jtmdb.Movie> movies = net.sf.jtmdb.Movie.deepSearch(t.getNome());
			if(movies != null && !movies.isEmpty()){
				for(net.sf.jtmdb.Movie m: movies){
					if(m != null && m.getName() != null && m.getName().equals(t.getNome())){
						if(m.getRating()>0){
							t.setExternalRating((float)m.getRating());
						}
					}
				}
			}
		} catch (JSONException | IOException e) {			
			e.printStackTrace();
		}		
	}

	@Override
	public void findBasicInformations(List<Film> list) {
		for(Film f: list){
			findBasicInformations(f);
		}		
	}
}
