/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package DAO;
 
 
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
// import javax.swing.table.AbstractTableModel;
 
/**
*
 * @author Alunos
*/
public class ConnectDAO {
    
    Connection conn;
    
    public Connection connectDB(){
        /* JOptionPane.showMessageDialog(null, "Inicia a classe para conexão com SQL SERVER!");
        String caminho = "jdbc:sqlserver://DESKTOP-09C4FSJ;databaseName=MOV_CONTA_CORRENTE;"
                + "encrypt=true;trustServerCertificate=true;"; 
        String usuario="sa";
        String senha=".";*/
        
        JOptionPane.showMessageDialog(null, "Inicia a classe para conexão com SQL SERVER!");
        String caminho = "jdbc:sqlserver://DESKTOP-09C4FSJ;databaseName=Delbicos;"
                + "encrypt=true;trustServerCertificate=true;";         

        
        String usuario="sa";
        String senha="S@outubro10";//"S@outubro10";  
        
        try {
            conn = DriverManager.getConnection(caminho, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
            JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
        }
        return conn;
        // conn.close();
    }
    
    /*
    * Rotina para inserir um registro novo no banco de dados
    */
    public void insereRegistroJFBD(BaseDAO obj) {
        String tabela = obj.getTableName();
        String strDados = obj.dadosSQLValues();
        conn = connectDB();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            
            String sql = "INSERT INTO dbo."+tabela+" VALUES ("+strDados+")";
            JOptionPane.showMessageDialog(null, "String de insert: "+sql);
            
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Inclusão executada com sucesso!");
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
            }
            conn.close();
        } catch (SQLException erro) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
    }

    public void alteraRegistroJFBD(BaseDAO obj) {
        String tabela = obj.getTableName();
        String strDados = obj.alteraDadosSQLValues();
        String condicao = obj.termoSQLWhereById();
        conn = connectDB();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            
            String sql = "UPDATE dbo."+tabela+" SET "+strDados+" WHERE "+condicao;
            JOptionPane.showMessageDialog(null, "String de update: "+sql);
            
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Alteração executada com sucesso!");
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
            }
            conn.close();
        } catch (SQLException erro) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
    }

    public void excluiRegistroJFBD(BaseDAO obj) {
        String tabela = obj.getTableName();
        String condicao = obj.termoSQLWhereById();
        conn = connectDB();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            
            String sql = "DELETE FROM dbo."+tabela+" WHERE "+condicao;
            JOptionPane.showMessageDialog(null, "String de delete: "+sql);
            
            try {
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Exclusão executada com sucesso!");
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
            }
            conn.close();
        } catch (SQLException erro) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
    }

    public List<String> consultaRegistroJFBD(BaseDAO obj) {
        String tabela = obj.getTableName();
        String campos = obj.consultaSQLValues();
        String condicao = obj.termoSQLWhereById();
        conn = connectDB();
        Statement stmt;
        ResultSet dados;
        List<String> lista = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT "+campos+" FROM dbo."+tabela+" WHERE "+condicao;
            JOptionPane.showMessageDialog(null, "String de select: "+sql);
            
            try {
                dados = stmt.executeQuery(sql);
                
                if (!dados.next()) {
                    JOptionPane.showMessageDialog(null, "Registro não encontrado!");
                }

                int columnCount = dados.getMetaData().getColumnCount();
                for (int index = 1; index <= columnCount; index++) {
                    lista.add(dados.getString(index));
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
            }
            conn.close();
        } catch (SQLException erro) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
        return lista;
    }
    
    public List<List<String>> consultaTodoRegistroJFBD(BaseDAO obj) {
        String tabela = obj.getTableName();
        String campos = obj.consultaSQLValues();
        conn = connectDB();
        Statement stmt;
        ResultSet dados;
        List<List<String>> lista = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            
            String sql = "SELECT "+campos+" FROM dbo."+tabela;
            JOptionPane.showMessageDialog(null, "String de select: "+sql);
            
            try {
                dados = stmt.executeQuery(sql);
                
                while (dados.next()) {
                    List<String> linha = new ArrayList<>();
                    int columnCount = dados.getMetaData().getColumnCount();
                    for (int index = 1; index <= columnCount; index++) {
                        linha.add(dados.getString(index));
                    }
                    lista.add(linha);
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão, connectDAO - Mensagem => "+erro.getMessage());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Estado => "+erro.getSQLState());
                JOptionPane.showMessageDialog(null, "\n Erro de conexão, connectDAO - Código => "+erro.getErrorCode());
            }
            conn.close();
        } catch (SQLException erro) {
            Logger.getLogger(ConnectDAO.class.getName()).log(Level.SEVERE, null, erro);
        }
        return lista;
    }
}

