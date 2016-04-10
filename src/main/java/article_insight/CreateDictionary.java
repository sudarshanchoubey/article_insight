/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article_insight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 *
 * @author schoubey
 */
public class CreateDictionary {
    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);        
    }
    
    public static void main(String[] arg) throws Exception{
        File dir = new File("C:\\Users\\schoubey\\Documents\\NetBeansProjects"+
                            "\\article_insight\\Nattypages\\");
        File[] filesInDir = dir.listFiles();
        ArrayList<Word> words = new ArrayList();
        int count = 1;
        if(filesInDir != null) {
            for(File article : filesInDir) {
                System.out.println("File being read " + article.toString());
                String html = readFile(article.getPath());
                Document doc = Jsoup.parse(html);
                String text = doc.body().text();
                if(count-- == 0) {
                    break;
                }
                //ArrayList<String> wordsInFile = getWordsFromText(text);
                String[] allWords = text.split("\\W+");
                for(String oneWord: allWords) {
                    Word word = new Word(oneWord);
                    words.add(word);
                }
            }
            for(Word oneWord: words) {
                System.out.println(oneWord.toString());
            }
        }
    }
}
