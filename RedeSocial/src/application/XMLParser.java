package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import managers.MusicalActManager;
import managers.MovieManager;
import managers.LocaleManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import valueObjects.Know;
import valueObjects.CurtiFilme;
import valueObjects.CurtirAto;
import valueObjects.Film;
import valueObjects.IRecordable;
import valueObjects.Pessoa;



public class XMLParser {
	private static NodeList parseFile(File file, String tagName) throws SAXException, IOException, ParserConfigurationException{		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();		
		NodeList nodes = doc.getElementsByTagName(tagName);
		return nodes;
	}

	private static String getLastDataFromUri(String uri){		
		if(uri.endsWith("/")){
			uri = uri.substring(0, uri.length()-2);
		}
		int lastPos = uri.lastIndexOf("/");			
		String str = uri.substring(lastPos+1,uri.length());		
		return str;		
	}
	
	public static List<IRecordable> parsePersons() throws SAXException, IOException, ParserConfigurationException{
		
		List<IRecordable> pessoas = new ArrayList<IRecordable>();
		File file = new File(Constants.XML_PERSONS_PATH);
		NodeList nodes = parseFile(file,"Person");
		
		for(int index = 0; index < nodes.getLength(); index++){
			Node node = nodes.item(index);
						
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				
				String uri = element.getAttribute("uri");				
				String name = element.getAttribute("name");
				String hometown = element.getAttribute("hometown");				
				String login = getLastDataFromUri(uri);
				
				LocaleManager.getInstance().createLocale(hometown);
				//System.out.printf("%s %s %s %s \n", uri, name, hometown, login);
				
				Pessoa pessoa = new Pessoa(0,name,login,hometown);
				pessoas.add(pessoa);				
			}
		}				
		return pessoas;
	}
	
	public static List<IRecordable> parseKnows() throws SAXException, IOException, ParserConfigurationException{
		List<IRecordable> knows  = new ArrayList<IRecordable>();
		File file = new File(Constants.XML_KNOWS_PATH);
		NodeList nodes = parseFile(file,"Knows");
		
		for(int index = 0; index < nodes.getLength(); index++){
			Node node = nodes.item(index);
						
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				
				String uriPerson = element.getAttribute("person");				
				String uriColleague = element.getAttribute("colleague");
				
				String person = getLastDataFromUri(uriPerson);
				String colleague = getLastDataFromUri(uriColleague);	
				
				Know conhece = new Know(person,colleague);
				knows.add(conhece);
				System.out.printf("%s %s  \n", person, colleague);
								
			}
		}	
		return knows;		
	}
	
	public static List<IRecordable> parseLikesMovie(boolean save) throws SAXException, IOException, ParserConfigurationException{
		MovieManager filmeManager = MovieManager.getInstance();
		
		List<IRecordable> likesMovies  = new ArrayList<IRecordable>();
		File file = new File(Constants.XML_LIKES_MOVIES_PATH);
		NodeList nodes = parseFile(file,"LikesMovie");
		
		for(int index = 0; index < nodes.getLength(); index++){
			Node node = nodes.item(index);
						
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				
				String uriPerson = element.getAttribute("person");
				String uriMovie  = element.getAttribute("movieUri");
				String rating    = element.getAttribute("rating");
				int rat = Integer.parseInt(rating);
				
				String person = getLastDataFromUri(uriPerson);
				String movie  = getLastDataFromUri(uriMovie);
								
				Film filme = new Film(movie);				
				if(save){
					filmeManager.addFilme(movie, filme);
				}				
								
				CurtiFilme curtiFilme = new CurtiFilme(person, movie, rat);
				likesMovies.add(curtiFilme);
			}
		}				
		return likesMovies;		
	}
	
	public static List<IRecordable> parseLikesMusic(List<IRecordable> bandsName) throws SAXException, IOException, ParserConfigurationException{
		MusicalActManager atoManager = MusicalActManager.getInstance();
		List<IRecordable> likesMusic = new ArrayList<IRecordable>();
		File file = new File(Constants.XML_LIKES_MUSIC_PATH);
		NodeList nodes = parseFile(file,"LikesMusic");
		
		for(int index = 0; index < nodes.getLength(); index++){
			Node node = nodes.item(index);
						
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) node;
				
				String uriPerson = element.getAttribute("person");
				String uriBand   = element.getAttribute("bandUri");
				String rating    = element.getAttribute("rating");
				int rat = Integer.parseInt(rating);
				
				String person = getLastDataFromUri(uriPerson);
				String band   = getLastDataFromUri(uriBand);
				band = band.replace("'", "");				
				
				atoManager.createAto(band);
				
				CurtirAto curtirAto = new CurtirAto(person,band,rat);			
				likesMusic.add(curtirAto);			
			}
		}				
		return likesMusic;		
	}
	
	

}
