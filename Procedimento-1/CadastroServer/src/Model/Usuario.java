/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package Model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCodUsuario", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
    @NamedQuery(name = "Usuario.findByLoginAndSenha", query = "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha")
})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cod_usuario")
    private Integer codUsuario;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "senha")
    private String senha;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "cod_usuario", referencedColumnName = "cod_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    public Usuario() {}
    
    // getters e setters

    public Integer getCodUsuario() {
        return codUsuario;
    }
    
    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public String getNome() {
        return pessoa != null ? pessoa.getNome() : null;
    }

    @Override
    public String toString() {
        return "Usuario[codUsuario=" + codUsuario + ", login=" + login + "]";
    }
    
}
