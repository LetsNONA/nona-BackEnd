package letsnona.nonabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NonaBackEndApplication {
private static final String mainYml = "local";

    public static void main(String[] args) {
        new SpringApplicationBuilder(NonaBackEndApplication.class)
                .properties(
                        "spring.config.location=" + "classpath:/application-"+mainYml+".yml"

                ).run(args);
    }

}
