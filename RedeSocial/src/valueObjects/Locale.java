package valueObjects;

import application.Constants;

public class Locale implements IRecordable {
	private int id;
	private double longitude, latitude;
	private String locale;
	
	public Locale(String locale) {
		super();
		this.locale = locale;
	}
	
	public Locale(double longitude, double latitude, String locale) {
		super();		
		this.longitude = longitude;
		this.latitude = latitude;
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String getTableName() {
		return Constants.LOCALE_TABLE_NAME;
	}

	@Override
	public String getIdFieldName() {
		return Constants.LOCALE_TABLE_ID_FIELD_NAME;
	}

	@Override
	public String getFieldsName() {
		String fields = String.format("(%s, %s, %s)", "locale", "latitude", "longitude");
		return fields;		
	}

	@Override
	public String getValues() {
		String longi = String.valueOf(this.longitude);
		longi = longi.replace(',','.');
		
		String lati = String.valueOf(this.latitude);
		lati = lati.replace(',','.');
		
		String values = String.format(" ('%s', %s, %s) ", this.locale, lati, longi);
		return values;	
	}

	@Override
	public String getRecordId() {		
		return String.valueOf(this.id);
	}

}
