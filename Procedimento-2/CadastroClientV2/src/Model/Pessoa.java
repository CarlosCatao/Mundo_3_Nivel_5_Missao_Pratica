/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package Model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "Pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
    @NamedQuery(name = "Pessoa.findByCodPessoa", query = "SELECT p FROM Pessoa p WHERE p.codPessoa = :codPessoa"),
    @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pessoa.findByLogradouro", query = "SELECT p FROM Pessoa p WHERE p.logradouro = :logradouro"),
    @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone"),
    @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email"),
    @NamedQuery(name = "Pessoa.findByTipoPessoa", query = "SELECT p FROM Pessoa p WHERE p.tipoPessoa = :tipoPessoa"),
    @NamedQuery(name = "Pessoa.findByCidade", query = "SELECT p FROM Pessoa p WHERE p.cidade = :cidade"),
    @NamedQuery(name = "Pessoa.findByEstado", query = "SELECT p FROM Pessoa p WHERE p.estado = :estado")})

public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "cod_pessoa")
    private Integer codPessoa;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "logradouro")
    private String logradouro;
    
    @Column(name = "telefone")
    private String telefone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "tipo_pessoa")
    private Character tipoPessoa;
    
    @Column(name = "cidade")
    private String cidade;
    
    @Column(name = "estado")
    private String estado;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private Usuario usuario;
    
    @JoinTable(name = "Pessoa_Produto", joinColumns = {
        @JoinColumn(name = "cod_pessoa", referencedColumnName = "cod_pessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto")})
    @ManyToMany
    private Collection<Produto> produtoCollection;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private PessoaJuridica pessoaJuridica;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codComprador")
    private Collection<Operacao> operacaoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codVendedor")
    private Collection<Operacao> operacaoCollection1;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private PessoaFisica pessoaFisica;

    public Pessoa() {
    }

    public Pessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }
    
    // getters e setters
    
    public Integer getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(Integer codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Character tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Produto> getProdutoCollection() {
        return produtoCollection;
    }

    public void setProdutoCollection(Collection<Produto> produtoCollection) {
        this.produtoCollection = produtoCollection;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Operacao> getOperacaoCollection() {
        return operacaoCollection;
    }

    public void setOperacaoCollection(Collection<Operacao> operacaoCollection) {
        this.operacaoCollection = operacaoCollection;
    }

    public Collection<Operacao> getOperacaoCollection1() {
        return operacaoCollection1;
    }

    public void setOperacaoCollection1(Collection<Operacao> operacaoCollection1) {
        this.operacaoCollection1 = operacaoCollection1;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPessoa != null ? codPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        return (this.codPessoa != null || other.codPessoa == null)
                && (this.codPessoa == null || this.codPessoa.equals(other.codPessoa));
    }

    @Override
    public String toString() {
        return "Pessoa[codPessoa=" + codPessoa + ", nome=" + nome + "]";
    }
    
}
