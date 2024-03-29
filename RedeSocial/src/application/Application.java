package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import managers.LocaleManager;
import managers.MovieGenreManager;
import managers.MovieManager;
import managers.MusicalActManager;
import managers.MusicalStyleManager;

import org.xml.sax.SAXException;

import persistence.KnowDAO;
import persistence.LikeMoviesDAO;
import persistence.LikeMusicalActsDAO;
import persistence.PersonDAO;
import valueObjects.IRecordable;

/**
 * Main class of the application.
 * 
 * @author André Pinheiro Borba
 */
public class Application {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		searchOnWeb();
	}

	public static void clearDB() {

	}

	/**
	 * Method that looking for informations and metadata in web sources.
	 */
	public static void searchOnWeb() {
		searchMusicInformation();
		searchMovieInformations(false);
		searchLocale();
	}

	/**
	 * Parse all XML's with informations about people, knows and likes.
	 */
	public static void parseXML() {
		try {
			parsePessoa();
			parseKnows();
			parseLikesMovie();
			parseLikesMusic();

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void parsePessoa() throws SAXException, IOException, ParserConfigurationException {
		List<IRecordable> pessoas = XMLParser.parsePersons();
		PersonDAO pessoaDAO = new PersonDAO();
		pessoaDAO.save(pessoas);
	}

	public static void parseKnows() throws SAXException, IOException, ParserConfigurationException {
		List<IRecordable> knows = XMLParser.parseKnows();
		KnowDAO conheceDAO = new KnowDAO();
		conheceDAO.save(knows);
	}

	public static void parseLikesMovie() throws SAXException, IOException, ParserConfigurationException {
		List<IRecordable> likes = XMLParser.parseLikesMovie(true);
		LikeMoviesDAO curtirFilesDAO = new LikeMoviesDAO();
		curtirFilesDAO.save(likes);
	}

	public static void parseLikesMusic() throws SAXException, IOException, ParserConfigurationException {
		List<IRecordable> bands = new ArrayList<IRecordable>();
		List<IRecordable> likes = XMLParser.parseLikesMusic(bands);
		LikeMusicalActsDAO curtirAtosDAO = new LikeMusicalActsDAO();
		curtirAtosDAO.save(likes);
	}

	public static void searchMovieInformations(boolean loadFromDB) {
		MovieGenreManager generoManager = MovieGenreManager.getInstance();
		MovieManager filmeManager = MovieManager.getInstance();

		if (loadFromDB) {
			filmeManager.loadAll();
		}

		filmeManager.findInIMDB();
		filmeManager.findInTheMovieDB();
		generoManager.persistGeneros();
		filmeManager.persistFilmes();
		filmeManager.persistGeneroFilme();
	}

	public static void searchMusicInformation() {
		MusicalActManager atoManager = MusicalActManager.getInstance();
		MusicalStyleManager estiloManager = MusicalStyleManager.getInstance();

		atoManager.loadAll();
		atoManager.findInLastFM();
		atoManager.updateAll();

		estiloManager.persistEstilos();
		atoManager.persistEstiloAto();
	}

	public static void searchLocale() {
		LocaleManager localeManager = LocaleManager.getInstance();
		localeManager.findInGeonames();
		localeManager.persistLocale();
	}
}