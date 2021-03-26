
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ramiro
 */
public class ConexaoBD {

       final private String driver = "org.postgresql.Driver";
       final private String url = "jdbc:postgresql://localhost/teste";
       final private String usuario = "postgres";
       final private String senha = "123";
       Statement stm;
       ResultSet rs;

       private Connection con;


       public Connection conecta()
       {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url, usuario, senha);
                //JOptionPane.showMessageDialog(null, "Conectado ao Banco de Dados!");

            }
            catch (ClassNotFoundException Driver)
            {
                JOptionPane.showMessageDialog(null, "Falha na Conexão com o Banco de Dados!");
                            }
            catch (SQLException Fonte)
            {
                JOptionPane.showMessageDialog(null, "Erro de Conexão!");
                            }
            return con;
       }
       public void desonecta()
       {
            boolean result = true;

            try
            {
                con.close();
                //JOptionPane.showMessageDialog(null, "Conexão Fechada!");
            }
            catch (SQLException erroSQL)
            {
                    JOptionPane.showMessageDialog(null, "Não Foi Possíve Fechar o Banco de Dados!");
                    result = false;
             }
            
       }
       public void executeSQL (String sql){
           try{
               stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
               rs = stm.executeQuery(sql);
               
           }catch (Exception ex){
               JOptionPane.showMessageDialog(null, "Erro ao Executar Comando!");
           }
       }
       
}
