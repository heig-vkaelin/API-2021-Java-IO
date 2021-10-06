package ch.heigvd.api.labio.impl;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms files. The transform method receives an inputFile.
 * It writes a copy of the input file to an output file, but applies a
 * character transformer before writing each character.
 *
 * @author Juergen Ehrensberger
 */
public class FileTransformer {
    private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());
    
    public void transform(File inputFile) {
        /*
         * This method opens the given inputFile and copies the
         * content to an output file.
         * The output file has a file name <inputFile-Name>.out, for example:
         *   quote-2.utf --> quote-2.utf.out
         * Both files must be opened (read or write) with encoding "UTF-8".
         * Before writing each character to the output file, the transformer calls
         * a character transformer to transform the character before writing it to the output.
         */
        
        /* TODO: first start with the NoOpCharTransformer which does nothing.
         *  Later, replace it by a combination of the UpperCaseCharTransformer
         *  and the LineNumberCharTransformer.
         */
        // ... transformer = ...
        
        /* TODO: implement the following logic here:
         *  - open the inputFile and an outputFile
         *    Use UTF-8 encoding for both.
         *    Filename of the output file: <inputFile-Name>.out (that is add ".out" at the end)
         *  - Copy all characters from the input file to the output file.
         *  - For each character, apply a transformation: start with NoOpCharTransformer,
         *    then later replace it with a combination of UpperCaseFCharTransformer and LineNumberCharTransformer.
         */
        try {
        
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error while reading, writing or transforming file.", ex);
        }
    }
}