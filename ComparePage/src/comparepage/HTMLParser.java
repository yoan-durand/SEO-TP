/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;


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
    
}
