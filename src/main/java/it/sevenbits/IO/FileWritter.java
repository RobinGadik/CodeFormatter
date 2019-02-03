package it.sevenbits.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWritter implements IWritter {

    File file;
    FileWriter out;

    public FileWritter(String name) throws IOException {
        file = new File(name);
        out = new FileWriter(file);
    }

    @Override
    public void write(char c) throws IOException {
        out.write(String.valueOf(c));
        out.flush();
    }
}
