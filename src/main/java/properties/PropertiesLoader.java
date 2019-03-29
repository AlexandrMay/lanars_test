package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.baseUrl;

public interface PropertiesLoader {

    Properties temp = new Properties();

    default String getTempProperty(String propertyName) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/properties/project.properties");
        temp.load(fis);
        return temp.getProperty(propertyName);
    }

    default void loadProperties() throws IOException {
        System.setProperty(getTempProperty("driver"), getTempProperty("pathToDriver"));
        System.setProperty(getTempProperty("browserType"), getTempProperty("browserName"));
        baseUrl = getTempProperty("baseURL");
    }
}
