/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package urlloading;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 * @author yoan
 */
public class LoadUrl 
{
    public LoadUrl()
    {
        
    }
    
    public static String LoadUrl(URL url) throws IOException 
    {
        InputStream stream = null;
        try 
        {
            stream = url.openStream();
            return LoadStream(stream);
        } 
        finally 
        {
            if (stream != null) 
            {
                try 
                {
                    stream.close();
                } 
                catch (IOException e) 
                {
                }
            }
        }
    }
    
     public static String LoadStream(InputStream stream) throws IOException 
     {
        Reader reader = new InputStreamReader(stream, Charset.forName("UTF-8"));
        char[] buffer = new char[1024];
        int count;
        StringBuilder str = new StringBuilder();
        while ((count = reader.read(buffer)) != -1)
        {
            str.append(buffer, 0, count);
        }
        return str.toString();
     }
}
