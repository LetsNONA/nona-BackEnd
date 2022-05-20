package letsnona.nonabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
//@EnableJpaRepositories("letsnona.nonabackend")
@EnableJpaRepositories
public class Application {
    private static final String mainYml = "";

    public static void main(String[] args) {
        /*new SpringApplicationBuilder(NonaBackEndApplication.class)
                .properties(
                        "spring.config.location=" + "classpath:/application" + mainYml + ".yml"

                ).run(args);*/
        SpringApplication.run(Application.class, args);

    }

}
