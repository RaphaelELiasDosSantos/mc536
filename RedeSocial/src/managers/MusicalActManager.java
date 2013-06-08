package managers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import externSources.LastFMRequest;

import application.SocialNetworkUtils;

import persistence.MusicalActDAO;
import valueObjects.MusicalAct;
import valueObjects.EstiloMusical;
import valueObjects.Pair;

public class MusicalActManager {

	private static MusicalActManager  instance = null;
		private Map<String, MusicalAct> atosMap;
		private List<MusicalAct> atosList;
		
		
		private MusicalActManager (){
			atosMap = new HashMap<String, MusicalAct>();
			atosList = new ArrayList<MusicalAct>();
		}
		
		public static MusicalActManager  getInstance(){
			if (instance == null){
				instance = new MusicalActManager();
			}
			return instance;		
		}
		
		public void addAto(String nome, MusicalAct a){
			if(a != null && !atosMap.containsKey(nome)){
				atosMap.put(nome, a);
				atosList.add(a);
			}		
		}
		
		public void createAto(String nome){
			MusicalAct ato = new MusicalAct(nome);
			addAto(nome, ato);
		}
		
		public void persistEstiloAto(){
			List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
			for(MusicalAct ato: atosList){
				for(EstiloMusical estilo: ato.getEstilosMusicais()){
					Pair<Integer, Integer> p = new Pair<Integer, Integer>(ato.getId(), estilo.getId());
					pairs.add(p);
				}
			}
			MusicalActDAO atosDAO = new MusicalActDAO();
			atosDAO.saveEstilos(pairs);
		}
		
		public void persistAto(){
			MusicalActDAO atosDAO = new MusicalActDAO ();
			for(MusicalAct a: atosList){
				atosDAO.save(a);
			}
		}
		
		/**
		 * Recovery all objects from database.
		 */
		public void loadAll(){
			MusicalActDAO atosDAO = new MusicalActDAO();			
			List<MusicalAct> la = (List<MusicalAct>) atosDAO.findAll();
			
			for(MusicalAct ato: la){
				ato.setNome(SocialNetworkUtils.cleanName(ato.getNome()));
				addAto(ato.getNome(), ato);
				LocaleManager.getInstance().createLocale(ato.getPais());
			}			
		}
		
		public void updateAll(){
			MusicalActDAO atosDAO = new MusicalActDAO();			
			atosDAO.update(atosList);
		}
		
		public void findInLastFM(){
			LastFMRequest lastFM = new LastFMRequest();
			lastFM.findBasicInformations(atosList);			
		}
		
		
}
