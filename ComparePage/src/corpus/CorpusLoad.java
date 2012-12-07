/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corpus;

import comparepage.HTMLParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import urlloading.LoadUrl;

/**
 *
 * @author yoan
 */
public class CorpusLoad 
{
    private HashMap<String, Double> finalMap;
    private HashMap<String, Double> otherMap;

    public HashMap<String, Double> getOtherMap() 
    {
        return otherMap;
    }

    public void setOtherMap(HashMap<String, Double> otherMap) 
    {
        this.otherMap = otherMap;
    }

    public HashMap<String, Double> getFinalMap() 
    {
        return finalMap;
    }

    public void setFinalMap(HashMap<String, Double> finalMap)
    {
        this.finalMap = finalMap;
    }
    
    
    
    public static void Load(String filename) throws IOException
    {
        File  corpus = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        String line;
        HTMLParser parser = new HTMLParser();
        while ((line = reader.readLine()) != null)
        {
            int i = 0;
            URL url = new URL(line);
            System.out.println("lecture de " + line);
            try (FileWriter urlFile = new FileWriter("url_"+i+".txt", true)) 
            {
                parser.LemProcess(LoadUrl.LoadUrl(url));
                try (BufferedWriter bw = new BufferedWriter(urlFile)) 
                {
                    for (String str : parser.getDocStr())
                    {
                        bw.write(str + " ");
                        bw.flush();
                    }
                }
            }
            i++;
            System.out.println("fichier creer");  
        }
    }
    
    
}
