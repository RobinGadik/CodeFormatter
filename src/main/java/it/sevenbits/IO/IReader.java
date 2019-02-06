package it.sevenbits.IO;

import java.io.IOException;

/**
 * for symbol reading
 */
public interface IReader {

    /**
     * @return next input char
     * @throws IOException if char's end's or some specific
     */
    char read() throws IOException;

    /**
     * @return can be next char read or not
     */
    boolean hasNext();

}
