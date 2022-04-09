package pl.lublin.wsei.java.cwiczenia.test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFileRead {

    public static void main(String[] args) throws java.io.IOException{

        try{
            String contents = new String(Files.readAllBytes(Paths.get("gusInfoGraphic.xml")));
            System.out.println("Zawartość pliku gusInfoGraphic.xml");
            System.out.print(contents);
        }

        catch (IOException e){

            System.out.println("Błąd wczytywania pliku gusInfoGraphic.xml =>"+ e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
