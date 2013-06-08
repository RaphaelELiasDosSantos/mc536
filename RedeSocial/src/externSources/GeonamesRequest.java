package externSources;

import java.util.List;


import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import valueObjects.Locale;

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
	
	
	@Override
	public void findAlternativeInformation(Locale t) {
		
		
	}

}
