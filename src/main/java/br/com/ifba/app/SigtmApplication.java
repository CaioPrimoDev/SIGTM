package br.com.ifba.app;

import br.com.ifba.parceiro.view.ParceirosListar;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

// teste
//TESTE PEDRO
//Teste da mais linda
@SpringBootApplication(scanBasePackages = "br.com.ifba")//começar a escanear a partir dos pacotes que tenham br.com.ifba
public class SigtmApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SigtmApplication.class, args);

        
        //MUDAR A CLASSE MAIN DE VCS PQ EU FIZ ASSIM PARA TESTAR AS MINHAS INTERFACES GRAFICAS
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SigtmApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        ParceirosListar telaCursoListar = context.getBean(ParceirosListar.class);
        telaCursoListar.setVisible(true);

    }

}
