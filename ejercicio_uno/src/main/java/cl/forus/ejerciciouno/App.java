package cl.forus.ejerciciouno;


import cl.forus.ejerciciouno.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{
    public static void main(String... args){
        Config.args(args);
        SpringApplication.run(App.class, args);
    }
}
