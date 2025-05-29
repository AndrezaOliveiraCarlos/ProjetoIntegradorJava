package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author guilh
 */
public class DelBicos {

    /**
     * @param args the command line arguments
     */
     Connection con;
    public Connection delbicosDB(){
        JOptionPane.showMessageDialog(null, "Inicia a classe para conexão com SQL SERVER!");
        String caminho = "jdbc:sqlserver://DESKTOP-09C4FSJ;databaseName=DelBicos;"
                + "encrypt=true;trustServerCertificate=true;"; 
        String usuario = "sa";
        String senha = ".";
        
        try {
            con = DriverManager.getConnection(caminho);
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Mensagemm => "+erro.getSQLState());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Mensagemmm => "+erro.getErrorCode());
        }
        return con;
    
}
    public void insereCadastroJFBD(String tabela, String strDados) {
    con = delbicosDB();
    Statement stmt;
    try{
        stmt = con.createStatement();
        String sql = "INSERT INTO dbo." + tabela + " Values(" + strDados + ")";
        JOptionPane.showMessageDialog(null, "String de insert " + sql);
        try{
            stmt.executeUpdate (sql);
            JOptionPane.showMessageDialog(null, "Inclusão executada com sucesso!");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Mensagemm => "+erro.getSQLState());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Mensagemmm => "+erro.getErrorCode());
        }
    }
catch (Exception ex) {
     Logger.getLogger(DelBicos.class.getName()).log(Level.SEVERE, null,ex);
}
    
} 
}
