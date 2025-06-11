package Model;

import Model.Pessoa;
import Model.Produto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-10T19:47:44", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Operacao.class)
public class Operacao_ { 

    public static volatile SingularAttribute<Operacao, Integer> quantidadeOperacao;
    public static volatile SingularAttribute<Operacao, BigDecimal> precoUnitarioOperacao;
    public static volatile SingularAttribute<Operacao, Pessoa> codComprador;
    public static volatile SingularAttribute<Operacao, Integer> idOperacao;
    public static volatile SingularAttribute<Operacao, Pessoa> codVendedor;
    public static volatile SingularAttribute<Operacao, Character> tipoOperacao;
    public static volatile SingularAttribute<Operacao, Date> dataOperacao;
    public static volatile SingularAttribute<Operacao, Produto> codProduto;

}