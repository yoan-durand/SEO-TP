/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author yoan
 */
public class HTMLParser 
{
    private String docHTML;
    private List<String> docStr;

    public List<String> getDocStr() {
        return docStr;
    }

    public void setDocStr(List<String> docStr) {
        this.docStr = docStr;
    }
 
    public String getDocHTML() 
    {
        return docHTML;
    }

    public void setDocHTML(String docHTML) 
    {
        this.docHTML = docHTML;
    }
    
    
    
  public HTMLParser()
  {
      this.docHTML = new String();
      this.docStr = new ArrayList<>();
  }
  
  public void Parse(String html)
  {
      Document doc = Jsoup.parse(html);
      
      this.docHTML = doc.getAllElements().text();
      
  }
  
  
  public void LemProcess(String HTML) throws FileNotFoundException, IOException
  {
      String[] tabStr;
      List<String> list = new ArrayList<>();
      
      this.Parse(HTML);
      tabStr = this.docHTML.split(" ");
            
      for(String str : tabStr)
      {  
          if (str.length() > 2)
          {           
            if (str.contains("\'"))
            {
               
                    str = str.substring(str.indexOf("\'")+1, str.length()-1);
            }
             if (str.length() > 2)
            {
                str = this.lemmeword(str);
               if ((str != null) && (!str.equals("")) )
               {
                    list.add(str);
               }
            }
          }
      }
      this.docStr = list;
  }
  
  public String lemmeword(String word) throws FileNotFoundException, IOException
    {
        String res = "";
        word = word.toLowerCase();
        
        try
        {
            File f = new File("src/Dico/dico/dico_" + word.toLowerCase().charAt(0) + ".txt");
            InputStream in = new FileInputStream(f);                        
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "ISO-8859-15"));
            String line;
            while ((line = reader.readLine()) != null)
            {

                if (line.contains(word.toLowerCase()))
                {
                    String[] split = line.split("[ \t]");
                    res = split[1];
                }
            }
            return res;
        }
        catch (Exception e)
        {
         return null;
        }
    }
    
}
