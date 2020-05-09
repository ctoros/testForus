package cl.forus.ejerciciodos.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import static java.util.Arrays.stream;


@Service
public class Config {
    static Logger logger = LogManager.getLogger(Config.class);

    public static final String SPRING_CONFIG_NAME = "--spring.config.name=";
    private static String bundle = "ejerciciodos.properties";

    @Value("${properties.location:}")
    private String propertiesLocation;

    Properties userProperties;

    public static void args(String[] args) {

        for (String arg : args) {
            if (arg.startsWith(SPRING_CONFIG_NAME)) {
                bundle = arg.substring(SPRING_CONFIG_NAME.length()) + ".properties";
            }
        }
    }

    public Config() {

        final Resource[] possiblePropertiesResources = {
                new ClassPathResource(bundle),
                new PathResource("config/" + bundle),
                new PathResource(bundle)
//                ,new PathResource(getCustomPath())
        };
        InputStream inputStream = null;
        try {
            final Resource propertiesResource = stream(possiblePropertiesResources).filter(Resource::exists).reduce((previous, current) -> current).get();
            userProperties = new Properties();
            inputStream = propertiesResource.getInputStream();
            userProperties.load(inputStream);
        } catch (NoSuchElementException e) {
            String message = "No se encuentra archivo de configuracion: " + bundle;
            logger.fatal(message, e);
            throw new RuntimeException(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.debug("Problema al cerrar inputStream");
            }
        }
        logger.debug("configurado");
    }

    private String getCustomPath() {
        return propertiesLocation.endsWith(".properties") ? propertiesLocation : propertiesLocation + bundle;
    }

    public int getInt(String key) {
        Object o = userProperties.get(key);
        if (o == null) {
            return -1;
        }
        return Integer.parseInt(o.toString());
    }

    public String getString(String key) {
        return (String) userProperties.get(key);
    }
}
