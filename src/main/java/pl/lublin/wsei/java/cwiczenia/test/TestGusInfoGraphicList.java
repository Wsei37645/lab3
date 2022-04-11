package pl.lublin.wsei.java.cwiczenia.test;


import pl.lublin.wsei.java.cwiczenia.GusInfoGraphicList;
import pl.lublin.wsei.java.cwiczenia.Infografika;

public class TestGusInfoGraphicList {
    public static void main(String[] args) {
        GusInfoGraphicList list = new GusInfoGraphicList("gusInfoGraphic.xml");
        for (Infografika ig: list.infografiki) ig.print();

    }
}
