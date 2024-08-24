package DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private Properties properties;

    public PropertyReader(String FilePath) throws IOException {
        FileReader file = new FileReader(FilePath);
        properties= new Properties();
        properties.load(file);
    }

    public String getPropertyValueFromKey(String key){
    return properties.getProperty(key);
    }

}
