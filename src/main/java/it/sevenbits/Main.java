package it.sevenbits;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.sevenbits.IO.FileReader;
import it.sevenbits.IO.FileWritter;

public class Main {
    public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        //System.out.println(" CHE MI CHAN GI ");
        FileReader in = new FileReader("pom.xml");
        FileWritter out = new FileWritter("pom1.xml");
        while(in.hasNext()) {
            out.write(in.read());
        }

    }
}
