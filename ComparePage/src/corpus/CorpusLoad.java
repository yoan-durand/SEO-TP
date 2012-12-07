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
    private HashMap<String, Double> firstMap;
    private HashMap<String, Double> secondMap;

    public HashMap<String, Double> getFirstMap() {
        return firstMap;
    }

    public void setFirstMap(HashMap<String, Double> firstMap) {
        this.firstMap = firstMap;
    }

    public HashMap<String, Double> getSecondMap() {
        return secondMap;
    }

    public void setSecondMap(HashMap<String, Double> secondMap) {
        this.secondMap = secondMap;
    }

    public CorpusLoad ()
            {
                this.firstMap = new HashMap<>();
                this.secondMap = new HashMap<>();
            }
    public static void Load(String filename) throws IOException
    {
        File  corpus = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        String line;
        HTMLParser parser = new HTMLParser();
         int i = 0;
        while ((line = reader.readLine()) != null)
        {  
            URL url = new URL(line);
            System.out.println("lecture de " + line);
            try (FileWriter urlFile = new FileWriter("corpus/url_"+i+".txt", true)) 
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
            System.out.println("corpus/url_"+i+".txt");  
        }
    }
    
    
}
