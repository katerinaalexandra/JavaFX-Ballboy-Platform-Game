package ballboy;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

public class ConfigParser {

    public JSONObject config;

    public ConfigParser(String configFile) {
        JSONParser parser = new JSONParser();
        System.out.println(this.getClass().getResource("/"+configFile));

        try {
            URI uri = new URI(this.getClass().getResource("/"+configFile).toString());
            this.config =(JSONObject) parser.parse(new FileReader(new File(uri.getPath())));
        } catch (IOException | URISyntaxException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getLevelConfig(){return (JSONObject) config.get("LevelInfo");}

    public String getLevelName(){return (String) config.get("LevelName");}
}
