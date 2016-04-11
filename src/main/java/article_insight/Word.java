/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article_insight;

import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author schoubey
 */
public class Word implements Serializable {
    private final String wordString;
    private final HashSet<String> filesHaveWord;
    public Word(String s) {
        wordString = s.toLowerCase();
        filesHaveWord = new HashSet();
    }
    @Override
    public String toString() {
        return wordString;
    }
    public String[] getFilesContainingWord() {
        String[] s = new String[filesHaveWord.size()];
        return filesHaveWord.toArray(s);        
    }
    public void addFile(String s) {
        filesHaveWord.add(s);
    }
}
