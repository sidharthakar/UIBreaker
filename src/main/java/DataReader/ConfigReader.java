package DataReader;

import UtilityManager.BrowserFactory;
import UtilityManager.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigReader {
    public class ReadConfig {

        public static String env = System.getProperty("env");
        public static Map<String, Object> configData;
        private static final ObjectMapper objectMapper = new ObjectMapper();

        static {
            if(env == null){
                env ="qa";
            }
        }

        public static String getconfigvalue(String keyName) throws Exception {
            try {
                String configFilePath = System.getProperty("user.dir")+ "/src/main/resources/"+env+"/appConfig.json";
                Logger.info("appConfig.json file path: "+configFilePath);
                if(configData  == null){
                    configData = objectMapper.readValue(new File(configFilePath),new TypeReference<Map<String,Object>>(){});
                }
                return (String) configData.get(keyName);
            } catch (IOException e) {
                throw new Exception(e.getMessage()+"Not Able To Find the File Path or the Key Not mentioned ");
            }
        }
    }
}
