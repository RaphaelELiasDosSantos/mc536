package externSources;

import java.util.List;

import valueObjects.IRecordable;

/**
 * This is a interface that provide a common interface for my application access externs data sources. 
 * It was inspired in adaptar design pattern.
 * 
 * @author André Pinheiro Borba
 * @param <T>
 */
public interface IRequest<T> {
	public void findBasicInformations(T t);
	public void findBasicInformations (List<T> list);	
}