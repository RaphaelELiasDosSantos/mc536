package externSources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import managers.MovieGenreManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;
import org.json.JSONArray;
import org.json.JSONObject;

import application.Constants;
import application.SocialNetworkUtils;

import valueObjects.Film;
import valueObjects.Genero;


public class ImdbRequest implements IRequest<Film> {
	private HttpParams params = null;	
	private MovieGenreManager generoManager = MovieGenreManager.getInstance();
	
	@Override
	public void findBasicInformations(Film f) {
		if(params == null){
			params = new BasicHttpParams();
			HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
			paramsBean.setVersion(HttpVersion.HTTP_1_1);
			paramsBean.setContentCharset("UTF-8");
			paramsBean.setUseExpectContinue(true);
			paramsBean.setUserAgent("Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.14) Gecko/20080418 Ubuntu/12.10 (gutsy) Firefox/2.0.0.14");
		}		
	
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://imdbapi.org/?id="+f.getImdbId());
		httpGet.setParams(params);		
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				BufferedReader buffer = new BufferedReader(new InputStreamReader(
                                        entity.getContent()));
				String json = buffer.readLine();
				buffer.close();
				
				JSONObject jobj = new JSONObject(json);
				handleJSONData(f, jobj);
				System.out.println(json);			    
			}
			
			else{
				System.out.println("Erro de response para o filme: " + f.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}					
		
	}

	@Override
	public void findBasicInformations(List<Film> filmes) {				
		for(Film f: filmes){
			findBasicInformations(f);			
		}		
	}

	@Override
	public void findAlternativeInformation(Film filme) {
		
		
	}
	
	private void handleJSONData(Film f, JSONObject jobj){
		try{
			if(jobj != null){
				//Title
				if(jobj.has(Constants.IMDB_TITLE)){
					String title = jobj.getString(Constants.IMDB_TITLE);
					if (title != null){
						f.setNome(title);
					}
				}
				
				//Release date
				if(jobj.has(Constants.IMDB_RELEASE_DATE)){
					Integer releaseDateInt = (Integer) jobj.get(Constants.IMDB_RELEASE_DATE);
					String releaseDate = releaseDateInt.toString();
					if(releaseDate != null){
						int year = Integer.valueOf(releaseDate.substring(0, 4));
						int month = Integer.valueOf(releaseDate.substring(4, 6));
						int day = Integer.valueOf(releaseDate.substring(6,8));			
								
						Calendar c = Calendar.getInstance();	
						c.set(Calendar.YEAR, year);
						c.set(Calendar.MONTH, month);
						c.set(Calendar.DAY_OF_MONTH, day);
						Date date = c.getTime();
						f.setData_lancamento(date);				
					}
				}
				
				//Sinopse
				if(jobj.has(Constants.IMDB_PLOT)){
					String sinopse = jobj.getString(Constants.IMDB_PLOT);
					if(sinopse != null){
						f.setSinopse(sinopse);
					}	
				}
				
				//Genres
				
				if(jobj.has(Constants.IMDB_GENRES)){
					JSONArray genres = jobj.getJSONArray(Constants.IMDB_GENRES);
					
					for(int index = 0; index < genres.length(); index++){
						if(!genres.isNull(index)){
							String genre = genres.getString(index);
							Genero g = new Genero(genre);							
							f.addGenero(g);				
							generoManager.addGenero(g);
						}
					}					
				}
			
				
				//Rating
				if(jobj.has(Constants.IMDB_RATING)){
					try{
						Double rating = (Double) jobj.get(Constants.IMDB_RATING);
						if(rating != null){
							f.setExternalRating(rating.floatValue());
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}					
				}
				
				//Country
				if(jobj.has(Constants.IMDB_COUNTRY)){
					JSONArray countrys = jobj.getJSONArray(Constants.IMDB_COUNTRY);
					StringBuilder sb = new StringBuilder();
					String country;
					for(int index=0; index < countrys.length(); index++){
						if(!countrys.isNull(index)){
							country = countrys.getString(index);
							sb.append(country + " ");
						}
					}
					if(!sb.toString().isEmpty()){
						f.setCountry(sb.toString());
					}				
				}
			}
			else{
				System.out.println("JSON OBJETC NULL FOR MOVIE: "+f.getNome());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private String handleTitleStr(String source){
		String title = SocialNetworkUtils.takeString(source, "title", ',');
		title = SocialNetworkUtils.extractContent(title, '"', '"', 1);
		return title;
	}

}
