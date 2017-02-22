import java.util.Iterator;
import java.util.List;

public final class Utili{
    
	public static final int ERR = 1;
	
    public static String listToString(Object...data){
    	
    	String string = "";
    	
        int counter = 1;
        List list = (List)data[0];
        Iterator iterator = list.iterator();
        int size = list.size();
        String separator = "";
        
        if(data.length>1)
        	separator= String.format("%s", data[1]);
        if (size > 0){
	        string = "\n"+separator+"[";
	        for (; iterator.hasNext();counter++ ) {
	            string += iterator.next().toString()+((counter >= size)?"":"\n "+separator);
	          }
	          string += "]";
        }
        return string;
    }
    


}
