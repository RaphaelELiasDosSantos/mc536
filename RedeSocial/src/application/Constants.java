package application;

public class Constants {
	
	/**
	 * Persistence constants.
	 */
	public static final String BANDA_TABLE_NAME          = "banda";
	public static final String BANDA_TABLE_ID_FIELD_NAME = "id";
	public static final String BANDA_TABLE_FIELDS_NAME   = "(id_atos_musicais)";	
	
	public static final String PESSOA_TABLE_NAME          = "pessoa";
	public static final String PESSOA_TABLE_ID_FIELD_NAME = "id";
	
	public static final String CONHECE_TABLE_NAME          = "conhece";
	public static final String CONHECE_TABLE_ID_FIELD_NAME = "id";
	
	public static final String CURTIR_ATOS_TABLE_NAME          = "curtiratos";
	public static final String CURTIR_ATOS_TABLE_ID_FIELD_NAME = "id";
	
	public static final String CURTIR_FILME_TABLE_NAME          = "curtirfilme";
	public static final String CURTIR_FILME_TABLE_ID_FIELD_NAME = "id";
	
	public static final String FILME_TABLE_NAME          = "filme";
	public static final String FILME_TABLE_ID_FIELD_NAME = "id";
	
	public static final String GENERO_TABLE_NAME          = "genero";
	public static final String GENERO_TABLE_ID_FIELD_NAME = "id";
	
	public static final String ESTILO_MUSICAL_TABLE_NAME          = "estilo_musical";
	public static final String ESTILO_MUSICAL_TABLE_ID_FIELD_NAME = "id";
	
	public static final String ATO_MUSICAL_TABLE_NAME          = "atos_musicais";
	public static final String ATO_MUSICAL_TABLE_ID_FIELD_NAME = "id";
	
	public static final String LOCALE_TABLE_NAME          = "Locales";
	public static final String LOCALE_TABLE_ID_FIELD_NAME = "id";	
	
	public static final int ID_DIRETOR_VAZIO = 1;
	public static final int ID_GENERO_VAZIO = 1;
	
	/**
	 * Files Path 
	 */
	public static String XML_PERSONS_PATH = "/RedeSocial/xml/persons.xml";
	public static String XML_KNOWS_PATH = "/RedeSocial/xml/knows.xml";
	public static String XML_LIKES_MOVIES_PATH = "/RedeSocial/xml/likesMovie.xml";
	public static String XML_LIKES_MUSIC_PATH = "/RedeSocial/xml/likesMusic.xml";
	public static String CONFIG_FILE_PATH = "configFile";
	
	
	/**
	 * WEB APIs CONSTANTS 
	 */
	
	public static final String LAST_FM_KEY ="197febcb1e6fda8e2575c49eb1802095";
	public static final int LAST_FM_TIMEOUT = 10; //miliseconds
	
	public static final String THE_MOVIE_DB_KEY="40eee1ef45ecb0c2a6ac6320f04a26d7";
	
	
	public static final String IMDB_GENRES = "genres";
	public static final String IMDB_TITLE = "title";
	public static final String IMDB_RATING_COUNT= "rating_count";
	public static final String IMDB_RATING= "rating";
	public static final String IMDB_DIRECTORS = "directors";
	public static final String IMDB_PLOT = "plot_simple"; //sinopse
	public static final String IMDB_YEAR = "year";
	public static final String IMDB_RELEASE_DATE = "release_date";
	public static final String IMDB_ALSO_KNOW_AS = "also_known_as";
	public static final String IMDB_COUNTRY = "country";
	
	
}

