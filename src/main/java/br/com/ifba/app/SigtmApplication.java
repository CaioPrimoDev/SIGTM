package br.com.ifba.app;

import br.com.ifba.login.controller.LoginController;
import br.com.ifba.login.view.TelaLoginUI;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// teste
//TESTE PEDRO
//Teste da mais linda
@SpringBootApplication(scanBasePackages = "br.com.ifba") // Garante que o Spring escaneie todos os componentes dentro de "br.com.ifba"
@EnableJpaRepositories(basePackages = "br.com.ifba")     // Habilita o uso dos repositórios JPA dentro do pacote especificado
@EntityScan(basePackages = "br.com.ifba")                // Garante que o Spring encontre as entidades JPA dentro do pacote especificado
public class SigtmApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SigtmApplication.class, args);

        
        //MUDAR A CLASSE MAIN DE VCS PQ EU FIZ ASSIM PARA TESTAR AS MINHAS INTERFACES GRAFICAS
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SigtmApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        // Obtém a tela de login do contexto e a exibe
        LoginController loginController = context.getBean(LoginController.class);
        TelaLoginUI tela = new TelaLoginUI(loginController);
        tela.setVisible(true);

    }

}
