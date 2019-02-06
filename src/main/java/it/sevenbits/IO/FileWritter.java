package it.sevenbits.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * symbol to file
 */
public class FileWritter implements IWritter {

    private File file;
    private FileWriter out;

    /**
     * @param name name or path to file
     * @throws IOException from java.IO
     */
    public FileWritter(final String name) throws IOException {
        file = new File(name);
        out = new FileWriter(file);
    }

    @Override
    public void write(final char c) throws IOException {
        out.write(String.valueOf(c));
        out.flush();
    }
}
