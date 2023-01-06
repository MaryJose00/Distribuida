package com.distribuida.configuracion;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.JdbiException;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

@ApplicationScoped
public class Dbconfig {
/*

    //OPCION 2: usar CDI
    @Inject
    @ConfigProperty(name = "db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name = "db.passw")
    String dbPasswr;  @Inject
    @ConfigProperty(name = "db.url")
    String dbUrl;

    @PostConstruct
    public void init(){

        System.out.println("************ post construct");
//OPCION PARA CONFIGURAR LOS DATOS DE LA BASE
        Config config = ConfigProvider.getConfig();
        String user = config.getValue("bd.user",String.class);
        String passwd = config.getValue("bd.password",String.class);
        String url = config.getValue("bd.url",String.class);
//imprime opcion 1
        System.out.println("op1 user "+user);
        System.out.println("op1 passw "+passwd);
        System.out.println("op1 url " +url);

        //imprime opcion 2
        System.out.println("op2 user "+ dbUser);
        System.out.println("op2 passw " +dbPasswr);
        System.out.println("op2 url " +dbUrl);

    }
*/

    public static Jdbi jdbi(){
        Config config = ConfigProvider.getConfig();
        String url = config.getValue("db.url", String.class);
        //String user = config.getValue("bd.user", String.class);
        String pasw = config.getValue("db.pasw", String.class);

        return Jdbi.create(url,"postgres",pasw);
    }
    public void test(){

    }

}
