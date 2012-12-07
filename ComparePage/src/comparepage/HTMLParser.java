/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author yoan
 */
public class HTMLParser 
{
  public HTMLParser()
  {
      
  }
  
  public static String Parse(String docHTML)
  {
      String res;
      Document doc = Jsoup.parse(docHTML);
      
      res = doc.getAllElements().text();
      return res;
  }
  
  public static String LemProcess(String )
  {
      
  }
  
  public static String lemmeword(String word) throws FileNotFoundException, IOException
    {
        String res = "";
       
        File  dic = new File("src/Dico/dico_" + word.charAt(0) + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(dic));
        String line;

        while ((line = reader.readLine()) != null)
        {
            if (line.contains(word))
            {
                String[] split = line.split("[ \t]");
                res = split[1];
            }
        }
        return res;
    }
    
}
