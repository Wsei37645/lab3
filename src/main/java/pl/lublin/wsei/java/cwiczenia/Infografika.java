package pl.lublin.wsei.java.cwiczenia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infografika {
    String tytul;
    String adresGrafiki;
    String adresStrony;
    int szerokosc;
    int wysokosc;


    public Infografika(String tekst){


        Pattern pat = Pattern.compile("<title><!\\[CDATA\\[(.*)\\]\\]");
        Matcher m = pat.matcher(tekst);

        if (m.find()) {
            tytul = m.group(1);
        } else {
            tytul = "";
        }

        pat = Pattern.compile("<link>(.*)</link>");
        m = pat.matcher(tekst);

        if (m.find()) {
            adresStrony = m.group(1);
        } else {
            adresStrony = " ";
        }

        pat = Pattern.compile("<media:content url=\"(.*)\" type");
        m = pat.matcher(tekst);

        if (m.find()) {
            adresGrafiki = m.group(1);
        } else {
            adresGrafiki = " ";
        }

        pat = Pattern.compile("width=\"(.*)\" height");
        m = pat.matcher(tekst);

        if (m.find()) {
            szerokosc = Integer.parseInt(m.group(1));
        } else {
            szerokosc = 0;
        }

        pat = Pattern.compile("height=\"(.*)\"");
        m = pat.matcher(tekst);

        if (m.find()) {
            wysokosc = Integer.parseInt(m.group(1));
        } else {
            wysokosc = 0;
        }



    }


    public void print() {
        System.out.print("\n\nInfografika:");
        System.out.print("\n\t tytul:"+ tytul);
        System.out.print("\n\t adres strony:"+adresStrony);
        System.out.print("\n\t adres grafiki:"+adresGrafiki);
        System.out.print("\n\t rozmiary:"+ szerokosc + "x" +wysokosc );
    }
}
