package Model;

import Model.Operacao;
import Model.PessoaFisica;
import Model.PessoaJuridica;
import Model.Produto;
import Model.Usuario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-12T13:14:50", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, PessoaFisica> pessoaFisica;
    public static volatile SingularAttribute<Pessoa, String> telefone;
    public static volatile SingularAttribute<Pessoa, Character> tipoPessoa;
    public static volatile SingularAttribute<Pessoa, String> cidade;
    public static volatile SingularAttribute<Pessoa, String> estado;
    public static volatile SingularAttribute<Pessoa, String> nome;
    public static volatile CollectionAttribute<Pessoa, Produto> produtoCollection;
    public static volatile CollectionAttribute<Pessoa, Operacao> operacoesComoVendedor;
    public static volatile SingularAttribute<Pessoa, Integer> codPessoa;
    public static volatile SingularAttribute<Pessoa, PessoaJuridica> pessoaJuridica;
    public static volatile SingularAttribute<Pessoa, String> logradouro;
    public static volatile CollectionAttribute<Pessoa, Operacao> operacoesComoComprador;
    public static volatile SingularAttribute<Pessoa, Usuario> usuario;
    public static volatile SingularAttribute<Pessoa, String> email;

}