/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tharlys
 */
// Essa classe será responsavel por uma conexão com o Banco de
// dados E devolver pra alguma parte do código ao qual precise fazer uma
// consulta no banco de dados, uma inserção no banco, apagar e etc.
public class ConnectionFactory {

    // Atributos para conectar com o banco Estipular qual vai ser o driver de
    // conexão: sempre que a aplicação for se conectar com um banco de dados,
    // ela precisa de um Driver Alguém que faça a ponte entre a aplicação e o
    // banco jdbc é uma dependencia que será utilizada, externa a nossa
    // aplicação Precisa informar que será usado essa biblioteca (jdbc)
    // jdbc
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    // Caminho de conexão até o banco de dados - onde está o banco
    public static final String URL = "jdbc:mysql://localhost:3306/todoApp";
    // Informar ao user será utilizado, normalmente o MySql vem com o root
    public static final String USER = "root";
    // e com o password fazio, sem segurança. Podendo ser alterado
    public static final String PASS = "";

    // Esse metodo devolve/retorna uma conexão static: Diz que eu posso chamar
    // esse metodo sem criar uma instancia dessa classe Não é necessário criar
    // um objeto da Classe ConnectionFactory, pra poder executa-lo É como se
    // fosse um metodo global, não é muito usal, vai depender de cada projeto
    public static Connection getConnection() {
        // try - catch: É uma forma de pegar erros que podem acontecer e fazer
        // um tratamento Faz tratamento de exceção/erro No try se coloca todo
        // código que é passivel de erro
        try {
            // Carrega o driver da nossa aplicação
            Class.forName(DRIVER);
            // Faz uma conexão com esses parametros
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            // Se a conexão existe, feche-a
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
}
