package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import valueObjects.IRecordable;


/**
 * Default implementation of DAO.
 * @author André Pinheiro Borba
 */
public abstract class BaseImplementationDAO implements IBaseDAO {
	private Connection connection = null;
    protected PreparedStatement ptmt = null;
    protected ResultSet resultSet = null;
    
    protected Connection getConnection() throws SQLException {
    	if(connection == null || connection.isClosed()){
    		connection = ConnectionFactory.getInstance().getConnection();
    	}
    	
    	return connection;
    }

	@Override
	public void save(IRecordable record) {
		try{
			
			String query = String.format("INSERT INTO %s %s VALUES %s", 
											record.getTableName(), //tableName
											record.getFieldsName(),//Fields
											record.getValues());   //Values
			//System.out.println(query);			
			Connection c = getConnection();
			c.setAutoCommit(false);
			ptmt = c.prepareStatement(query);
			ptmt.executeUpdate();
			c.commit();
			//System.out.printf("Registro inserido com sucesso na tabela %s \n!",record.getTableName());
			
		}
		catch (SQLException e) {
            e.printStackTrace();
		} finally {
            /*try {
            	if (ptmt != null)
            		ptmt.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                    e.printStackTrace();
            } catch (Exception e) {
                    e.printStackTrace();
            }*/
		}		
	}

	@Override
	public void save(List<IRecordable> records) {
		String query="";
		try{
			Connection c = getConnection();
			c.setAutoCommit(false);
			for(IRecordable record: records){
				query = String.format("INSERT INTO %s %s VALUES %s", 
												record.getTableName(), //tableName
												record.getFieldsName(),//Fields
												record.getValues());   //Values				
				
				ptmt = c.prepareStatement(query);
				try{
					ptmt.executeUpdate();
				}
				catch(Exception e){
					//e.printStackTrace();
				}				
			}
			c.commit();
		}
		catch (SQLException e) {
			System.out.printf(query);
            e.printStackTrace();
		} 		
	}	
	

	@Override
	public abstract Object find(int id);

	@Override
	public abstract List findAll();

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
				
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub
		
	}


}
