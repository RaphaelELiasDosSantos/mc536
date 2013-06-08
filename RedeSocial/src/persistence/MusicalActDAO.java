package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import valueObjects.MusicalAct;
import valueObjects.Pair;



public class MusicalActDAO extends BaseImplementationDAO {

	@Override
	public Object find(int id) {
		
		return null;
	}
	
	public void saveEstilos(List<Pair<Integer, Integer>> list){
		try {
			Connection c = getConnection();
			c.setAutoCommit(false);
			PreparedStatement ptmt;
			for(Pair<Integer, Integer> p: list){
				String query = String.format("insert into AtoMusicalEstilo (id_ato, id_estilo) values (%d , %d)", 
						p.getfirst().intValue(), p.getSecond().intValue());			
				ptmt = c.prepareStatement(query);						
				ptmt.executeUpdate();
			}						
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();			
		}	

	}

	
	public void update(List<MusicalAct> atos){
		
		try {
			Connection c = getConnection();
			c.setAutoCommit(false);
			PreparedStatement ptmt;
			for(MusicalAct ato: atos){
				String query = String.format("update atos_musicais set nome='%s', pais='%s', play_count= '%d' , " +
												"listeners= '%d' where id = %d", ato.getNome(), ato.getPais(), ato.getPlayCount(), ato.getListeners(), ato.getId());			
				ptmt = c.prepareStatement(query);						
				ptmt.executeUpdate();
			}						
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List findAll() {
		List<MusicalAct> atos = new ArrayList<MusicalAct>();
		
		try {
			Connection c = getConnection();
			String query = String.format("select * from atos_musicais");
			
			PreparedStatement ptmt = c.prepareStatement(query);						
			ResultSet rs = ptmt.executeQuery(query);
			
			while(rs.next()){
				String nome = rs.getString("nome");
				int id = rs.getInt("id");
				String pais = rs.getString("pais");				
				MusicalAct ato = new MusicalAct(nome);
				ato.setPais(pais);
				ato.setId(id);				
				atos.add(ato);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}	
				
		return atos;
	}

}
