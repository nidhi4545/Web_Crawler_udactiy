package com.udacity.webcrawler.json;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // What  we had to do in the load section is to read the file from the "Path" variable using the Reader interface and then parse the argument to the "Read" method of this class
    CrawlerConfiguration crawlerConfiguration = null;
    try(Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      crawlerConfiguration = ConfigurationLoader.read(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return crawlerConfiguration;
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  public static CrawlerConfiguration read(Reader reader) {
    Objects.requireNonNull(reader);
    CrawlerConfiguration deserialized = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      deserialized =  mapper.readValue(reader, CrawlerConfiguration.class);
    }catch(IOException e){
      e.printStackTrace();
    }
    return deserialized;
  }
}
