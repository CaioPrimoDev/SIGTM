package br.com.ifba.app;

import br.com.ifba.login.view.TelaLoginUI;
 import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.ifba") // Garante que o Spring escaneie todos os componentes dentro de "br.com.ifba"
@EnableJpaRepositories(basePackages = "br.com.ifba")     // Habilita o uso dos repositórios JPA dentro do pacote especificado
@EntityScan(basePackages = "br.com.ifba")                // Garante que o Spring encontre as entidades JPA dentro do pacote especificado
@ComponentScan("br.com.ifba")
public class SigtmApplication {

     public static void main(String[] args) {
        //SpringApplication.run(SigtmApplication.class, args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder(SigtmApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        // Obtém TelaLoginUI como bean Spring
        TelaLoginUI telaLogin = context.getBean(TelaLoginUI.class);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);

    } 
}
