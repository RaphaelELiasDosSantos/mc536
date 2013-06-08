package valueObjects;
/**
 * This interface must be implemented by all models that can be recordable in database.
 * @author André Pinheiro Borba
 */
public interface IRecordable {
	public String getTableName();
	public String getIdFieldName();
	public String getFieldsName();
	public String getValues();
	public String getRecordId();
}
