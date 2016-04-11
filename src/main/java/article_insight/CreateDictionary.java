/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article_insight;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author schoubey
 */
public class CreateDictionary {
    private static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);        
    }
    public static String getTextFromHTML(String p) {
        String html;
        try {
            html = readFile(p);
        } catch(Exception e) {
            System.out.println("Could not read File:" + p + "\n");
            return null;
        }
        Document doc = Jsoup.parse(html);
        String text = doc.body().text();
        return text;
    }
    private static void printDetailsForWord(String s,HashMap<String, Word> wordsHashMap) {
        Word word = wordsHashMap.get(s);
        if (word != null) {
            System.out.println("The word is " + word.toString());
            System.out.println("Files containing the word are:");
            for (String f : word.getFilesContainingWord()) {
                System.out.println("File Name:" + f);
            }
        }
    }
    
    public static void main(String[] arg) throws Exception{
        File dir = new File("C:\\Users\\schoubey\\Documents\\NetBeansProjects"+
                            "\\article_insight\\Nattypages\\");
        File[] filesInDir = dir.listFiles();
        HashMap<String, Word> wordsHashMap = new HashMap();
        int count = 10;
        if(filesInDir != null) {
            for(File article : filesInDir) {
                System.out.println("File being read " + article.toString());
                String text = getTextFromHTML(article.toString());
                if(count-- == 0) {
                    break;
                }
                //ArrayList<String> wordsInFile = getWordsFromText(text);
                String[] allWords = text.split("\\W+");
                for(String oneWord: allWords) {
                    oneWord = oneWord.toLowerCase();
                    Word word;
                    if(wordsHashMap.containsKey(oneWord)) {
                        word = wordsHashMap.get(oneWord);
                        word.addFile(article.toString());                        
                    } else {
                        word = new Word(oneWord);
                        word.addFile(article.toString());
                        wordsHashMap.put(oneWord, word);
                    }
                }
            }
            printDetailsForWord("arms", wordsHashMap);
            try {
                FileOutputStream fs = new FileOutputStream("C:\\Users\\" +
                        "schoubey\\Documents\\NetBeansProjects\\" +
                        "article_insight\\words.ser");
                ObjectOutputStream out = new ObjectOutputStream(fs);
                out.writeObject(wordsHashMap);
                out.close();
                fs.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }
    }
}
