/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;

import corpus.CorpusLoad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author yoan
 */
public class Tools 
{
     public static int Tf (String word, String doc)
    {
        int count = 0;
        int idx = 0;

        while ((idx = doc.indexOf(word, idx)) != -1)
        {
           idx++;
           count++;
        }

        return count;
    }
   
   
    //idf
   public boolean IsinFile (String word, String doc)
   {
       if (word != null)
       {
            if (doc.indexOf(word) != -1)
            {
                return true;
            }
            else
            {
                return false;
            }
       }
       return false;
   }
  
   public double idf (int corpussize, int nbfileswithword)
   {
     return ( Math.log10(corpussize/nbfileswithword));
   }
   
   public void FillMap(CorpusLoad cl) throws FileNotFoundException, IOException
    {
                
        for (int i = 0; i < 8; i++)
        {
            File f = new File("corpus/url_"+i+".txt");
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] lineStr = line.split(" ");
                for (String l: lineStr)
                {
                   cl.getFirstMap().put(l, Double.valueOf(0));
                   cl.getSecondMap().put(l, Double.valueOf(0));
                }
            }
        }
        

    }
   
    public double cosStalton (HashMap v1, HashMap v2)
   {
        double d1 = 0;
       double d2 = 0;
       double d1d2 = 0;
       Set cles = v1.keySet();
       Iterator it = cles.iterator();
       while (it.hasNext())
       {
           String key = (String) it.next();
           double val1 = (double) v1.get(key);
           double val2 = (double) v2.get(key);
           d1 += val1 * val1;
           d2 += val2 * val2;
           d1d2 += (val1 * val2)*(val1 * val2);
       }
       d1 = Math.sqrt(d1);
       d2 = Math.sqrt(d2);
       d1d2 = Math.sqrt(d1d2);
       return ((d1d2)/(d1 * d2));
   }
   
   
}
