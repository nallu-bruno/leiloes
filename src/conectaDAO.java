
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
  public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/leilaotdsat", "root", "123456");
            return conn;
        }catch (SQLException e){
            System.out.println("Erro ao conectar " + e.getMessage());
         return null;        
        }
    
    }
    
}
