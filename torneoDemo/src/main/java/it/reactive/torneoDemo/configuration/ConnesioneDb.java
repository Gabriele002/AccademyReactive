package it.reactive.torneoDemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnesioneDb {

    @Value("${profilo}")
    private String user;

    @Value("${psw}")
    private String psw;

    @Value("${nomeDb}")
    private String nome;

    Connection con;

    @Bean
    @Scope("prototype")
    public Connection init(){
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+ nome, user, psw);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}
