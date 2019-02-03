package it.sevenbits.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader implements IReader {

    private File file;
    private Scanner in;

    public FileReader(String filename) throws FileNotFoundException {
        file = new File(filename);
        in = new Scanner(file);
        in.useDelimiter("");
    }


    @Override
    public char read() {
        return in.next().charAt(0);
    }

    @Override
    public boolean hasNext() {
        return in.hasNext();
    }
}
