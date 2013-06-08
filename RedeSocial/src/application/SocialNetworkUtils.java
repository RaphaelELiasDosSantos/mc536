package application;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class with generic functions, that can be used in may parts of the application.
 * @author André Pinheiro Borba
 */
public class SocialNetworkUtils {
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDate (Date date){
		if (date == null){
			return null;
		}
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = sdf.format(date);
		return currentDate;
	}
	
	public static String cleanName(String name){
		if(name.contains("-")){
			name = name.replace('-', ' ');
		}
		
		if(name.contains("(")){
			name = name.substring(0, name.indexOf("(")-1);
		}
		
		if(name.contains(",")){
			name = name.substring(0, name.indexOf(",")-1);
		}
		
		return name;		
	}
	
	/**
	 * 
	 * @param source
	 * @param contain
	 * @param delimiter
	 * @return
	 */
	public static String takeString (String source, String contain, char delimiter){
		StringBuilder sb = new StringBuilder();
	    int position = source.indexOf(contain);
	    if(position!=-1){
	    	for(int i=position; i<source.length() && source.charAt(i)!= ',';i++){
		    	sb.append(source.charAt(i));
		    }
	    }
	    return sb.toString();
	}
	
	/**
	 * 
	 * @param source
	 * @param delimiterBegin
	 * @param delimiterEnd
	 * @param ignoreCharQty
	 * @return
	 */
	public static String extractContent(String source, char delimiterBegin, char delimiterEnd, int ignoreCharQty){
		StringBuilder sb = new StringBuilder();
		int ignored = 0, index=0;
		boolean cont = true;
		
		while(index < source.length() && cont){
			if(source.charAt(index)==delimiterBegin){
				if(ignored < ignoreCharQty){ignored++;}
				else{
					index++;
					//begin extract
					while(source.charAt(index)!=delimiterEnd){
						sb.append(source.charAt(index));
						index++;
					}
					cont = false;
				}
			}			
			
			index++;
		}		
		return sb.toString();
	}

}
