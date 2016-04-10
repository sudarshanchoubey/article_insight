/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article_insight;

import java.util.ArrayList;

/**
 *
 * @author schoubey
 */
public class Word {
    private String wordString;
    private ArrayList<String> filesHaveWord;
    public Word(String s) {
        wordString = s;
    }
    public String toString() {
        return wordString;
    }
}
