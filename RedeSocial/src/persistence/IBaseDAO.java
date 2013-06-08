package persistence;

import java.util.List;

import valueObjects.IRecordable;


/**
 * Default DAO interface.
 * @author André Pinheiro Borba
 */
//DAO = DATA ACCESS OBJECT
public interface IBaseDAO {
	public void save(IRecordable record);
	public void save(List<IRecordable> records);
	public Object find(int id);
	public List findAll();
	public void delete(int id);
	public void deleteAll();
	public void delete(Object object);
	
}
