package com.example.travellab.mainJava;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class javaDB {
    public String [] destName = new String[20];

    //Manually inputted from Distance Matrix Google Cloud API
    int mainArr[][] = new int[][] {
            { 0, 8, 8, 17, 16, 22, 4, 8, 28, 31, 10, 7, 9, 11, 21, 15, 2, 7 }, //ArtScience Museum
            { 8, 0, 3, 19, 13, 21, 9, 3, 27, 30, 8, 6, 5, 16, 26, 20, 8, 7 }, //National Gallery Singapore
            { 8, 3, 0, 18, 11, 20, 11, 1, 25, 28, 5, 7, 6, 15, 24, 18, 9, 8 }, //National Museum of Singapore
            { 17, 19, 18, 0, 19, 14, 11, 15, 24, 27, 19, 16, 18, 12, 22, 16, 13, 15 }, //Haw Par Villa
            { 16, 13, 11, 19, 0, 17, 18, 11, 19, 22, 11, 15, 16, 20, 30, 24, 18, 15 }, //Singapore Botanic Garden
            { 22, 21, 20, 14, 17, 0, 18, 16, 19, 22, 22, 19, 24, 19, 29, 23, 20, 19 }, //Chinese and Japanese Gardens
            { 4, 9, 11, 11, 18, 18, 0, 14, 29, 32, 14, 11, 12, 12, 22, 16, 8, 10 }, //Gardens by the Bay
            { 8, 3, 1, 15, 11, 16, 14, 0, 24, 27, 6, 4, 8, 10, 20, 14, 7, 5 }, //Fort Canning
            { 28, 27, 25, 24, 19, 19, 29, 24, 0, 3, 23, 29, 26, 20, 30, 34, 29, 30 }, //Singapore Zoo
            { 31, 30, 28, 27, 22, 22, 32, 27, 3, 0, 26, 32, 29, 33, 43, 36, 32, 33 }, //River Safari
            { 10, 8, 5, 19, 11, 22, 14, 6, 23, 26, 0, 14, 7, 18, 26, 20, 11, 13 }, //Little India
            { 7, 6, 7, 16, 15, 19, 11, 4, 29, 32, 14, 0, 11, 14, 23, 16, 10, 5 }, //Chinatown
            { 9, 5, 6, 18, 16, 24, 12, 4, 26, 29, 7, 11, 0, 14, 24, 18, 8, 9 }, //Kampong Glam
            { 11, 16, 15, 12, 20, 19, 12, 8, 20, 33, 18, 14, 14, 0, 10, 4, 12, 14 }, //Universal Studios Singapore
            { 21, 26, 24, 22, 30, 29, 22, 10, 30, 43, 26, 23, 24, 10, 0, 10, 20, 24 }, //Siloso Beach
            { 15, 20, 18, 16, 24, 23, 16, 20, 34, 36, 20, 16, 18, 4, 10, 0, 14, 16 }, //Adventure Cove Waterpark
            { 2, 8, 9, 13, 18, 20, 8, 14, 29, 32, 11, 10, 8, 12, 20, 14, 0, 9 }, //Marina Bay Sands Rooftop
            { 7, 7, 8, 15, 15, 19, 10, 5, 30, 33, 13, 5, 9, 14, 24, 16, 9, 0 } //1-Altitude
    };

    public int getDistance(int a, int b) {
        return mainArr[a][b];
    }

    public String[] dataScraper() {
        int count = 0;
        try {
            //Fetch the document over HTTP
            Document doc = Jsoup.connect("https://theculturetrip.com/asia/singapore/articles/20-must-visit-attractions-in-singapore/").get();
            Elements names = doc.select("h2.item-card-title__ItemTitleWrapper-a1kgd2-0.gakNGi"); //CSS Selector of Heading 2
            for(Element e:names) {
                destName[count] = e.text();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Remove Pulau Ubin and St John’s & Lazarus Island since it is too far and has no estimated time in google maps
        String[] anotherArray = new String[destName.length - 2];
        for (int i = 0, k = 0; i < destName.length; i++) {
            if (destName[i].equals("Pulau Ubin") || destName[i].equals("St John’s & Lazarus Island")) {
                continue;
            } anotherArray[k++] = destName[i];
        } return anotherArray;
    }
}

