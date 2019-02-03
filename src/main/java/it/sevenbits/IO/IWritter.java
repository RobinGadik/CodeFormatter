package it.sevenbits.IO;

import java.io.IOException;

/**
 * int for symbol writting
 */
public interface IWritter {

    /**
     * @param c char
     * @throws IOException if fail write
     */
    void write(char c) throws IOException;

}
