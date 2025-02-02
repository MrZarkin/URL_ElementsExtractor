package fr.zarkin.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HTTPLoader {
    
    static HashMap<String, Data> map = new HashMap<>();
    
    public static void getList(String _url) throws IOException
    {
    	try {
			URI uri = new URI(_url);
			URL url = uri.toURL();
			
	    	
	    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET"); // Définir la méthode de requête
	    	
	    	int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) // 200 OK
	        {
	        	System.out.println("Reading ...");
	        	
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = in.readLine()) != null) 
                {
                    analyseData(line);
                }
                
                in.close();
                System.out.println("Done");
                
                setMoy();
                sortMap();
                
            } else {
                System.out.println("Erreur lors de la connexion : " + responseCode);
            }
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
	public static void analyseData(String line)
    {
        String[] args = line.split(";");
        Float number = Float.parseFloat(args[1]);
        Data dt = new Data();
        
        if(!map.containsKey(args[0]))
        {
            dt.setMin(number);
            dt.setMax(number);
            dt.addTotal(number);
            dt.setCount(1);
            
            map.put(args[0], dt);
        }
        else
        {
            dt.addTotal(map.get(args[0]).getTotal() + number);
            dt.setCount(map.get(args[0]).getCount() + 1);
            
            if(number > map.get(args[0]).getMax())
            {
            	dt.setMax(number);
            }
            else
            {
            	dt.setMax(map.get(args[0]).getMax());
            }
            
            if(number < map.get(args[0]).getMin())
            {
            	dt.setMin(number);
            }
            else
            {
            	dt.setMin(map.get(args[0]).getMin());
            }
            
            map.put(args[0], dt);
        }
    }

	public static void setMoy()
    {
		System.out.println("Setting Moyenne...");
		for(Map.Entry<String, Data> entry : map.entrySet())
		{
			Data dt = new Data();
			
			Data value = entry.getValue();
			String key = entry.getKey();
			
			dt.setMin(value.getMin());
			dt.setMax(value.getMax());
			dt.setMoy( Math.round((value.getTotal() / value.getCount()) * 10.0) / 10.0);
			dt.setCount(value.getCount());
			dt.setTotal(value.getTotal());
			
			map.put(key, dt);
		}
		System.out.println("Done");
    }

    public static void sortMap()
    {
    	System.out.println("Sorting Elements...");
        TreeMap<String, Data> sortedMap = new TreeMap<>(map);

        for (Map.Entry<String, Data> entry : sortedMap.entrySet())
        {
        	String key = entry.getKey();
            Data value = entry.getValue();
        	
        	System.out.println("{" + key + " : " + value.getMin() + "/" + value.getMoy() + "/" + value.getMax() + "}");
        }
        
        System.out.println("Done");
    }
}