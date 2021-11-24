package ch.heigvd.api.labio.impl.transformers;

import java.util.logging.Logger;

/**
 * This class applies no transformation to the input character (a string with a single character).
 * It is just a simple case, before implementing other character transformers.
 *
 * @author Olivier Liechti, Juergen Ehrensberger
 */
public class NoOpCharTransformer {
    private static final Logger LOG = Logger.getLogger(NoOpCharTransformer.class.getName());
    
    public String transform(String c) {
        return c;
    }
}
