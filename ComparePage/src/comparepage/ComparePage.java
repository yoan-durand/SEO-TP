/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comparepage;

import corpus.CorpusLoad;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import urlloading.LoadUrl;

/**
 *
 * @author yoan
 */
public class ComparePage 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException 
    {   String doc1;
        String doc2;
        URL url1 = new URL(args[0]);
        URL url2 = new URL(args[1]);
        Tools t = new Tools();
        List<String> list = new ArrayList<>();
        CorpusLoad cl = new CorpusLoad();
        
        HTMLParser parser = new HTMLParser();
        File f = new File("corpus/url_1.txt");
        if (!f.exists())
        {
            CorpusLoad.Load("corpus/urlscorpus.txt");        
        }
        
        t.FillMap(cl);
        doc1 = LoadUrl.LoadUrl(url1);
        doc2 = LoadUrl.LoadUrl(url2);
        System.out.println("Calcul de lemmatisation de " + args[0]); 
        parser.LemProcess(doc1);
        System.out.println("Fin du calul de lemmatisation" +  args[0]);
       list = parser.getDocStr();
        for(String str : list)
        {
            System.out.println(str);
                if (str != null && !str.equals(""))
                {
                    int tf = Collections.frequency(list, str);
                    int nbmf = 0;
                    double idf = 0;
                    System.out.println("parcours corpus");
                    for(int i=0; i < 8; i++)
                    {
                        File file = new File("corpus/url_"+i+".txt");
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        line = reader.readLine();
                        if (t.IsinFile(str, line))
                        {
                            nbmf++;
                        }
                    }
                    
                    if (nbmf > 0)
                    {
                        System.out.println("calcul idf pour" + str);
                        idf = t.idf(20, nbmf); 
                        idf *= tf;
                        try
                        {
                            System.out.println("mise a jour hashmap pour" + str);
                            double nb = cl.getFirstMap().get(str);
                            nb++;
                            cl.getFirstMap().put(str, nb);
                        }
                        catch(Exception e)
                                {
                                    continue;
                                }
                          
                    } 
            }
        }
       
        System.out.println("parcours  de lemme" + args[1]);
        parser.LemProcess(doc2);
        System.out.println(" fin du lemme" +  args[1]);
        list = parser.getDocStr();
         for(String str : list)
        {
            int tf = Collections.frequency(list, str);
            int nbmf = 0;
            double idf = 0;
            
            for(int i=0; i < 8; i++)
            {
                File file = new File("corpus/url_"+i+".txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                line = reader.readLine();
                if (t.IsinFile(str, line))
                {
                    nbmf++;
                }
            }
            if (nbmf > 0)
            {
                System.out.println("calcul idf pour"+ str);
                idf = t.idf(20, nbmf); 
                idf *= tf;
                try
                {
                    System.out.println("mise a jour idf pour" + str);
                    double nb = cl.getSecondMap().get(str);
                    nb++;
                    cl.getSecondMap().put(str, nb);
                }
                catch (Exception e)
                        {
                            continue;
                        }
            } 
        }
         
        System.out.println("resultat de comparaison:"+  t.cosStalton(cl.getFirstMap(), cl.getSecondMap()));
        
    }
    
    
}
