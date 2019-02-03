package it.sevenbits;

import it.sevenbits.IO.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Prints "Hello, World" to the terminal window.
        //System.out.println(" CHE MI CHAN GI ");
        FileReader in = new FileReader("pom.xml");
        FileWritter out = new FileWritter("pom1.xml");
        while(in.hasNext()) {
            out.write(in.read());
        }

        IReader e = new StringReader("12345667890абсвгдеЁabcdefg");
        StringWritter r = new StringWritter();

        while(e.hasNext()) {
            r.write(e.read());
        }

    }
}
