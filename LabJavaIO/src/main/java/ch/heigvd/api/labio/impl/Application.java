package ch.heigvd.api.labio.impl;

import ch.heigvd.api.labio.quotes.Quote;
import ch.heigvd.api.labio.quotes.QuoteClient;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Application {

  /**
   * This constant defines where the quotes will be stored. The path is relative
   * to where the Java application is invoked.
   */
  public static String WORKSPACE_DIRECTORY = "./workspace/quotes";
  
  private static final Logger LOG = Logger.getLogger(Application.class.getName());
  
  public static void main(String[] args) {
    
    /*
     * I prefer to have LOG output on a single line, it's easier to read. Being able
     * to change the formatting of console outputs is one of the reasons why it is
     * better to use a Logger rather than using System.out.println
     */
    System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");
    
       
    int numberOfQuotes = 0;
    try {
      numberOfQuotes = Integer.parseInt(args[0]);
    } catch (Exception e) {
      System.err.println("The command accepts a single numeric argument (number of quotes to fetch)");
      System.exit(-1);
    }
        
    Application app = new Application();
    try {
      /*
       * Step 1 : clear the output directory
       */
      app.clearOutputDirectory();
      
      /*
       * Step 2 : use the QuotesClient to fetch quotes; store each quote in a file
       */
      app.fetchAndStoreQuotes(numberOfQuotes);
      
      /*
       * Step 3 : process the quote files, by applying 2 transformations to their content
       *          (convert to uppercase and add line numbers)
       */
      app.processQuoteFiles();
      
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, "Could not fetch quotes. {0}", ex.getMessage());
      ex.printStackTrace();
    }
  }

  public void fetchAndStoreQuotes(int numberOfQuotes) throws IOException {
    QuoteClient client = new QuoteClient();
    for (int i = 0; i < numberOfQuotes; i++) {
      Quote quote = client.fetchQuote();
      /* TODO: There is a missing piece here!
       *  As you can see, this method handles the first part of the lab. It uses the web service
       *  client to fetch quotes. We have removed a single line from this method. It is a call to
       *  one method provided by this class, which is responsible for storing the content of the
       *  quote in a text file (and for generating the directories based on the tags).
       *  Add the missing line which stores the content of the quote in a file with
       *  the name "quote-i.utf8" where 'i' is the number of the file.
       */

      LOG.info("Received a new joke with " + quote.getTags().size() + " tags.");
      for (String tag : quote.getTags()) {
        LOG.info("> " + tag);

      }
    }
  }
  
  /**
   * This method deletes the WORKSPACE_DIRECTORY and its content. It uses the
   * apache commons-io library. You should call this method in the main method.
   * 
   * @throws IOException 
   */
  void clearOutputDirectory() throws IOException {
    FileUtils.deleteDirectory(new File(WORKSPACE_DIRECTORY));    
  }

  /**
   * This method stores the content of a quote in the local file system. It has
   * 2 responsibilities: 
   * 
   * - with quote.getTags(), it gets a list of tags and uses
   *   it to create sub-folders (for instance, if a quote has three tags "A", "B" and
   *   "C", it will be stored in /quotes/A/B/C/quotes-n.utf8.
   * 
   * - with quote.getQuote(), it has access to the text of the quote. It stores
   *   this text in UTF-8 file.
   * 
   * @param quote the quote object, with tags and text
   * @param filename the name of the file to create and where to store the quote text
   * @throws IOException 
   */
  void storeQuote(Quote quote, String filename) throws IOException {
    // Create the directory path by concatenating the tags from quote, with a slash between the tags
    List<String> tags = quote.getTags();
    String path = WORKSPACE_DIRECTORY;
    for(String tag : tags) {
      path += "/" + tag;
    }
    path += "/";

    // Create directory. Does nothing if the directory already exists.
    File directory = new File(path);
    directory.mkdirs();

    // Create the output file under the new directory. Use the filename received as parameter.
    File file = new File(directory, filename);

    /* Now write the quote into the file using Output streams.
     * The content of the file is in quote.getQuote().
     * TODO: There is something missing here: you have to implement writing the file
     *   using an output stream.
     *   Write the file with encoding UTF-8.
     */

  }
  
  public void processQuoteFiles() throws IOException {
    FileExplorer explorer = new FileExplorer();
    explorer.explore(new File(WORKSPACE_DIRECTORY));
  }

}