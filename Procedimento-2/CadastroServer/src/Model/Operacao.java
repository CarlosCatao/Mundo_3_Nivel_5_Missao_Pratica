/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Operacao")
@NamedQueries({
    @NamedQuery(name = "Operacao.findAll", query = "SELECT o FROM Operacao o"),
    @NamedQuery(name = "Operacao.findByIdOperacao", query = "SELECT o FROM Operacao o WHERE o.idOperacao = :idOperacao"),
    @NamedQuery(name = "Operacao.findByQuantidadeOperacao", query = "SELECT o FROM Operacao o WHERE o.quantidadeOperacao = :quantidadeOperacao"),
    @NamedQuery(name = "Operacao.findByPrecoUnitarioOperacao", query = "SELECT o FROM Operacao o WHERE o.precoUnitarioOperacao = :precoUnitarioOperacao"),
    @NamedQuery(name = "Operacao.findByDataOperacao", query = "SELECT o FROM Operacao o WHERE o.dataOperacao = :dataOperacao"),
    @NamedQuery(name = "Operacao.findByTipoOperacao", query = "SELECT o FROM Operacao o WHERE o.tipoOperacao = :tipoOperacao")})

public class Operacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacao")
    private Integer idOperacao;
    
    @Column(name = "quantidade_operacao")
    private Integer quantidadeOperacao;
    
    @Column(name = "preco_unitario_operacao")
    private BigDecimal precoUnitarioOperacao;
    
    @Column(name = "data_operacao")
    @Temporal(TemporalType.DATE)
    private Date dataOperacao;
    
    @Column(name = "tipo_operacao")
    private Character tipoOperacao;
    
    @ManyToOne
    @JoinColumn(name = "cod_comprador", nullable = false)
    private Pessoa codComprador;

    
    @ManyToOne
    @JoinColumn(name = "cod_vendedor", nullable = false)
    private Pessoa codVendedor;
    
    @ManyToOne
    @JoinColumn(name = "cod_produto", nullable = false)
    private Produto codProduto;
    
    public Operacao() {
    }

    public Integer getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(Integer idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Integer getQuantidadeOperacao() {
        return quantidadeOperacao;
    }

    public void setQuantidadeOperacao(Integer quantidadeOperacao) {
        this.quantidadeOperacao = quantidadeOperacao;
    }

    public BigDecimal getPrecoUnitarioOperacao() {
        return precoUnitarioOperacao;
    }

    public void setPrecoUnitarioOperacao(BigDecimal precoUnitarioOperacao) {
        this.precoUnitarioOperacao = precoUnitarioOperacao;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public Character getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(Character tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public Pessoa getCodComprador() {
        return codComprador;
    }

    public void setCodComprador(Pessoa codComprador) {
        this.codComprador = codComprador;
    }

    public Pessoa getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(Pessoa codVendedor) {
        this.codVendedor = codVendedor;
    }

    public Produto getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Produto codProduto) {
        this.codProduto = codProduto;
    }

    @Override
    public int hashCode() {
        return (idOperacao != null ? idOperacao.hashCode() : 0);
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Operacao)) {
            return false;
        }
        Operacao other = (Operacao) object;
        return (this.idOperacao != null || other.idOperacao == null) &&
               (this.idOperacao == null || this.idOperacao.equals(other.idOperacao));
    }

    @Override
    public String toString() {
        return "Model.Operacao[ idOperacao=" + idOperacao + " ]";
    }
    
}
