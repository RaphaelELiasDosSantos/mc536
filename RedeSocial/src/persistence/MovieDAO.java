package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import valueObjects.Pair;


public class MovieDAO extends BaseImplementationDAO {

	public void saveGenero(List<Pair<Integer, Integer>> list){
		try {
			Connection c = getConnection();
			c.setAutoCommit(false);
			PreparedStatement ptmt;
			for(Pair<Integer, Integer> p: list){
				String query = String.format("insert into GeneroFilme (id_genero, id_filme) values (%d , %d)", 
						p.getfirst().intValue(), p.getSecond().intValue());			
				ptmt = c.prepareStatement(query);						
				ptmt.executeUpdate();
			}						
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	
	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
