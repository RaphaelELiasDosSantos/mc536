package externSources;

import java.util.List;


import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import valueObjects.Locale;


/**
 * Class that provide to the application access to Geonames informations.
 * Here, a wrapper is used to do it.
 * @author André Pinheiro Borba 
 */
public class GeonamesRequest implements IRequest<Locale> {

	@Override
	public void findBasicInformations(Locale l) {
		if(l==null || l.getLocale().isEmpty()) return;
		WebService.setUserName("andrebixa");
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setQ(l.getLocale());
		ToponymSearchResult searchResult = null;
		try {
			searchResult = WebService.search(searchCriteria);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(searchResult.getToponyms()!= null && !searchResult.getToponyms().isEmpty()){
			Toponym toponym = searchResult.getToponyms().get(0);
			
			if(toponym != null){
				l.setLatitude(toponym.getLatitude());
				l.setLongitude(toponym.getLongitude());
				l.setGnCountry(toponym.getCountryName());
				l.setGnName(toponym.getName());
			}
		}	
	}

	@Override
	public void findBasicInformations(List<Locale> list) {
		for(Locale l: list){
			findBasicInformations(l);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}
}
