/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webpageclassifier;
import java.io.File;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author schoubey
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

/**
 * Example program to list links from a URL.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = "http://nattyornot.com/archives";
        print("Fetching %s...", url);
        int i;
        Document doc = Jsoup.connect(url).get();
        Elements allPosts = doc.select("div[id=allposts]");
        Elements links = allPosts.select("a[href*=nattyornot.com]");
                
        ArrayList<String> linksToTraverse = new ArrayList();
        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            Document page = null;
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
            //if(link.attr("href").matches(".*nattyornot.com.*")) {
            System.out.println(link.attr("href"));
            String[] temp = link.attr("href").split("/");
            String fileName = temp[temp.length - 1];
            File f = new File("C:\\Users\\schoubey\\Documents\\NetBeansProjects\\WebpageClassifier\\Nattypages\\" + fileName + ".html");
            if(f.exists()) {
                System.out.println("File " + f.getPath() + " exists skipping");
                f = null;
                continue;
            }
            i=0;
            while(i < 3) {
                try {
                    page = Jsoup.connect(link.attr("href")).get();
                    break;
                } catch (SocketTimeoutException ex) {
                    System.out.println("Failed an attempt");
                } catch (IOException e) {
                    
                }
                Thread.sleep(1000);
                i++;
            }
            if(page == null) {
                continue;
            }
            Elements content = page.select("div[class=entry-content]");
            System.out.println("Writing " + fileName);
            try (PrintWriter out = new PrintWriter(
                    "C:\\Users\\schoubey\\Documents\\NetBeansProjects\\WebpageClassifier\\Nattypages\\" + fileName + ".html")) {
                out.print(content.toString());
                //}
            }
        }
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}

