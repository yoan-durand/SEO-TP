/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import urlloading.LoadUrl;

/**
 *
 * @author yoan
 */
public class ComparePage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException 
    {
        URL url = new URL(args[0]);
        String result;
        result = LoadUrl.LoadUrl(url);
       
        result = HTMLParser.Parse(result);
        
        
        
    }
}
