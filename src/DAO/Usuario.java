/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author guilh
 */
public class Usuario {
    private String Nome;
    private String Email;
    private String Telefone;
    private String Senha;
    private boolean active;
 
    public Usuario(){ 
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
    
    public String alterarDadosSQLValues(){
        String dadosUsuarios = "username ='"
        +this.getNome() + "', =email'"
        +this.getEmail() +"', =phone'"
        +this.getTelefone() +"', =password'"
        +this.getSenha();
        return dadosUsuarios;
    }
    public boolean validateDate(String senha) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
