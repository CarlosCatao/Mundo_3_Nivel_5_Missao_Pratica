package Model;

import Model.Operacao;
import Model.Pessoa;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-12T13:14:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile CollectionAttribute<Produto, Pessoa> pessoaCollection;
    public static volatile CollectionAttribute<Produto, Operacao> operacaoCollection;
    public static volatile SingularAttribute<Produto, BigDecimal> precoUnitarioAtual;
    public static volatile SingularAttribute<Produto, Integer> quantidadeEstoque;
    public static volatile SingularAttribute<Produto, Integer> codProduto;
    public static volatile SingularAttribute<Produto, String> descricao;

}