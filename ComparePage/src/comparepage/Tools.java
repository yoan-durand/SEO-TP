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
       if (doc.indexOf(word) != -1)
       {
           return true;
       }
       else
       {
           return false;
       }
   }
  
   public double idf (int corpussize, int nbfileswithword)
   {
     return ( Math.log10(corpussize/nbfileswithword));
   }
   
   public void FillMap(String filename) throws FileNotFoundException, IOException
    {
        File  corpus = new File(filename);
        CorpusLoad cl = new CorpusLoad();
        BufferedReader reader = new BufferedReader(new FileReader(corpus));
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] lineStr = line.split(" ");
            for (String str: lineStr)
            {
               cl.getFinalMap().put(str, Double.valueOf(0));
               cl.getOtherMap().put(str, Double.valueOf(0));
            }
        }
    }
}
