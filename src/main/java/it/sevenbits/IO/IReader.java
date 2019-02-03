package it.sevenbits.IO;

import java.io.IOException;

interface IReader {

    char read() throws IOException;

    boolean hasNext();

}
