package testUtil;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    /**
     * This method is used to load the properties from config.properties file
     * @return it returns Properties prop object
     */
    public Properties init_Properties() {

            prop = new Properties();
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/config/config.properties");
                prop.load(fileInputStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prop;

        }

}

