package classes_de_conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    
    public static Connection faz_conexao() throws SQLException {
        try {
            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Estabelecer a conexão com o banco de dados
            return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas", "root", "1234");
            
        } catch (ClassNotFoundException e) {
            // Exceção para caso o driver JDBC não seja encontrado
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new RuntimeException("Driver JDBC não encontrado", e);
        }
    }
}
