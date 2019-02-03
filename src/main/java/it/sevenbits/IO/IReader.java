package it.sevenbits.IO;

import java.io.IOException;

public interface IReader {

    char read() throws IOException;

    boolean hasNext();

}
