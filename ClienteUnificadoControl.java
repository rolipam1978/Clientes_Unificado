
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteUnificadoControl {
    
    Date data = new Date();
    java.sql.Date dataParaGravar = new java.sql.Date(data.getTime());
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    PreparedStatement pstm;
    ResultSet rs;
    
    String consultaRegistro = "select * from clientes_unificada where nome like ? order by nome";
    String gravaRegistro = "insert into clientes_unificada (data_cadastro, tipo, razao_social, nome, sexo, cnpj, inscest, "
            + "telefone, celular, fax, email, contato, aniversario, endereco, bairro)"
            + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // 15 campos

    String alteraRegistro = "update clientes_unificada set razao_social=?, nome=?, aniversario =?, cnpj=?, "
            + "inscest=?, telefone=?, fax=?, contato=?, celular=?, email=?, endereco=?, "
            + "bairro=?, sexo=? where codigo=?"; // 14 Campos
    
    
    public List<ClinteUnificadoBean> listarClientes (String nome) { 
        List<ClinteUnificadoBean> clientes = new ArrayList();
        try {
            ConexaoBD con = new ConexaoBD();
            pstm = con.conecta().prepareStatement(consultaRegistro);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            ClinteUnificadoBean cli;
            while (rs.next()){
                cli = new ClinteUnificadoBean();
                cli.setCodigo(rs.getInt("codigo"));
                cli.setDataCadastro(sdf.format(rs.getDate("data_cadastro")));
                cli.setTipo(rs.getString("tipo"));
                cli.setRaz_social(rs.getString("razao_social"));
                cli.setNome(rs.getString("nome"));
                cli.setSexo(rs.getString("sexo"));
                cli.setCnpj(rs.getString("cnpj"));
                cli.setInscest(rs.getString("inscest"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setFax(rs.getString("fax"));
                cli.setEmail(rs.getString("email"));
                cli.setContato(rs.getString("contato"));
                cli.setAniversario(rs.getString("aniversario"));
                cli.setEndereco(rs.getString("endereco"));
                cli.setBairro(rs.getString("bairro"));
                clientes.add(cli);
                con.desonecta();
         } 
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Existe Registro!");
        }
        return clientes;

    }
    

    public void gravarRegistro(ClinteUnificadoBean cli) {
        ConexaoBD con = new ConexaoBD();
        try {
            pstm = con.conecta().prepareStatement(gravaRegistro);
            pstm.setDate(1, dataParaGravar);
            pstm.setString(2, cli.getTipo());
            pstm.setString(3, cli.getRaz_social());
            pstm.setString(4, cli.getNome());
            pstm.setString(5, cli.getSexo());
            pstm.setString(6, cli.getCnpj());
            pstm.setString(7, cli.getInscest());
            pstm.setString(8, cli.getTelefone());
            pstm.setString(9, cli.getCelular());
            pstm.setString(10, cli.getFax());
            pstm.setString(11, cli.getEmail());
            pstm.setString(12, cli.getContato());
            pstm.setString(13, cli.getAniversario());
            pstm.setString(14, cli.getEndereco());
            pstm.setString(15, cli.getBairro());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Gravado com Sucesso!");
            con.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Gravar o Registro!");
            e.printStackTrace();
        }


    }
    
    public void alterarRegistro(ClinteUnificadoBean cli){
                ConexaoBD con = new ConexaoBD();
        try {
            pstm = con.conecta().prepareStatement(alteraRegistro);
            pstm.setString(1, cli.getRaz_social());
            pstm.setString(2, cli.getNome());
            pstm.setString(3, cli.getAniversario());
            pstm.setString(4, cli.getCnpj());
            pstm.setString(5, cli.getInscest());
            pstm.setString(6, cli.getTelefone());
            pstm.setString(7, cli.getFax());
            pstm.setString(8, cli.getContato());
            pstm.setString(9, cli.getCelular());
            pstm.setString(10, cli.getEmail());
            pstm.setString(11, cli.getEndereco());
            pstm.setString(12, cli.getBairro());
            pstm.setString(13, cli.getSexo());
            pstm.setInt(14, cli.getCodigo());
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
            con.desonecta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Possível Alterar o Registro!");
            e.printStackTrace();
        }

    }



 }
