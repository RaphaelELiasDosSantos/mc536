package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import valueObjects.Genero;


public class MovieGenreDAO extends BaseImplementationDAO {

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Genero findByName(String name){
		Genero genero = null;
		
		try {
			Connection c = getConnection();
			String query = String.format("select * from genero where nome = '%s'", name);
			
			PreparedStatement ptmt = c.prepareStatement(query);						
			ResultSet rs = ptmt.executeQuery(query);
			
			
			if(rs.next()){
				String nome = rs.getString("nome");
				int id = rs.getInt("id");
				genero = new Genero(nome, id);
			}			
			else{
				System.out.println("Genero não encontrado. " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return genero;
	}

	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
