package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import valueObjects.EstiloMusical;


public class MusicalStyleDAO extends BaseImplementationDAO {

	public EstiloMusical findByName(String name){
		EstiloMusical estilo = null;
		
		try {
			Connection c = getConnection();
			String query = String.format("select * from estilo_musical where nome = '%s'", name);
			
			PreparedStatement ptmt = c.prepareStatement(query);						
			ResultSet rs = ptmt.executeQuery(query);
			
			
			if(rs.next()){
				String nome = rs.getString("nome");
				int id = rs.getInt("id");
				estilo = new EstiloMusical(nome, id);
			}			
			else{
				System.out.println("Estilo não encontrado. " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return estilo;
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
