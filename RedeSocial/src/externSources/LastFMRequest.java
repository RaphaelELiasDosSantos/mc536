package externSources;

import java.util.List;

import application.Constants;

import valueObjects.MusicalAct;

import managers.MusicalStyleManager;
import de.umass.lastfm.Artist;

public class LastFMRequest implements IRequest<MusicalAct> {
	int ok =0, nok=0;
	@Override
	public void findBasicInformations(MusicalAct ato) {		
		Artist a = Artist.getInfo(ato.getNome(), Constants.LAST_FM_KEY);
		if(a != null){			
			int listeners = a.getListeners();
			int playCount = a.getPlaycount();			
			ato.setListeners(listeners);
			ato.setPlayCount(playCount);
			
			//Add estilos
			for(String tag: a.getTags()){
				if(tag != null && !tag.isEmpty()){
					ato.addEstiloMusical(MusicalStyleManager.getInstance().createEstilo(tag));
				}
			 }
			
			System.out.printf("%d %d \n", listeners, playCount);
			ok++;
		}		
		else{
			nok++;
		}
	}

	@Override
	public void findBasicInformations(List<MusicalAct> list) {
		for(MusicalAct ato: list){
			findBasicInformations(ato);
			try {
				Thread.sleep(35);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		System.out.printf("ok:%d nok:%d\n", ok, nok);
	}
}
