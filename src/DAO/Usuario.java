/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author guilh
 */
public class Usuario implements BaseDAO {
    private String tableName = "user";
    private int userId;
    private String Nome;
    private String Email;
    private String Telefone;
    private String Senha;
    private boolean active;
 
    public Usuario(){ 
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String Telefone) {
        this.Telefone = Telefone;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    public boolean getactive() {
        return active;
    }

     public boolean isActive() {
        return active;
    }
     public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
    
    @Override
    public String dadosSQLValues(){
        String dadosUsuarios;
        dadosUsuarios = " ' "
        + this.getNome() + "','"
        + this.getEmail() + "','"
        + this.getTelefone() + "','"
        + this.getSenha() + "',"
       +  (this.isActive() ? "1" : "0");
        return dadosUsuarios;
    }
    
    @Override
    public String alteraDadosSQLValues(){
        String dadosUsuarios = "username ='"
        +this.getNome() + "', =email'"
        +this.getEmail() +"', =phone'"
        +this.getTelefone() +"', =password'"
        +this.getSenha();
        return dadosUsuarios;
    }
    
    @Override
    public String termoSQLWhereById() {
        return "id = "+this.getUserId();
    }
    
    @Override
    public String consultaSQLValues() {
        return "username, email, phone, password";
    }
    
    @Override
    public void importaSQLValues(List<String> dados) {
        if (dados.size() != 4) {
            throw new IllegalArgumentException("Número de dados inválido. Esperado 4 dados.");
        }
        this.setNome(dados.get(0));
        this.setEmail(dados.get(1));
        this.setTelefone(dados.get(2));
        this.setSenha(dados.get(3));
    }
    
    public boolean validateDate(String senha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
