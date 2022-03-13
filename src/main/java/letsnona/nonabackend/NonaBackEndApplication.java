package letsnona.nonabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonaBackEndApplication {
    private static final String mainYml = "";

    public static void main(String[] args) {
        /*new SpringApplicationBuilder(NonaBackEndApplication.class)
                .properties(
                        "spring.config.location=" + "classpath:/application" + mainYml + ".yml"

                ).run(args);*/
        SpringApplication.run(NonaBackEndApplication.class, args);

    }

}
