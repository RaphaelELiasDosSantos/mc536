package managers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import externSources.GeonamesRequest;

import persistence.LocaleDAO;
import valueObjects.Locale;


public class LocaleManager {
	private static LocaleManager instance;	
	private Map<String, Locale> localesMap;
	private List<Locale> localesList;
	private LocaleDAO localeDAO;
	
	private LocaleManager(){
		localesMap = new HashMap<String, Locale>();	
		localesList = new ArrayList<Locale>();
		localeDAO = new LocaleDAO();
	};
	
	public static LocaleManager getInstance(){
		if(instance == null){
			instance = new LocaleManager(); 
		}
		return instance;
	}
	
	public void addLocale(String name,Locale locale){
		if(!localesMap.containsKey(name)){
			localesList.add(locale);
			localesMap.put(name, locale);
		}
	}
	
	public Locale createLocale(String locale){
		if(locale==null || locale.isEmpty())return null;
		Locale l = localesMap.get(locale); 
		if(l== null){
			l = new Locale(locale);
			addLocale(locale, l);
		}
		return l;
	}
	
	public void persistLocale(){		
		for(Locale l: localesList){
			localeDAO.save(l);
		}		
	}
	
	public void findInGeonames(){
		GeonamesRequest geonames = new GeonamesRequest();
		geonames.findBasicInformations(localesList);
	}
	
	public void removeAll(){
		localeDAO.deleteAll();		
	}
	
}