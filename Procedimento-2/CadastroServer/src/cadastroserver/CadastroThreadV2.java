/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */
package cadastroserver;

import Controller.*;
import Model.*;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Collection;
import java.util.Date;

public class CadastroThreadV2 extends Thread {

    private final ProdutoJpaController ctrlProd;
    private final UsuarioJpaController ctrlUsu;
    private final OperacaoJpaController ctrlMov;
    private final PessoaJpaController ctrlPessoa;
    private final Socket s1;

    // Construtor
    public CadastroThreadV2(ProdutoJpaController ctrlProd, UsuarioJpaController ctrlUsu, OperacaoJpaController ctrlMov, PessoaJpaController ctrlPessoa, Socket s1) {
        this.ctrlProd = ctrlProd;
        this.ctrlUsu = ctrlUsu;
        this.ctrlMov = ctrlMov;
        this.ctrlPessoa = ctrlPessoa;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try (
                // Encapsula os canais de entrada e saída
                ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(s1.getInputStream());
        ) {
        
            // Obter login e senha
            String login = (String) in.readObject();
            String senha = (String) in.readObject();
            
            // Exibe o login e senha do usuario
            System.out.println("Login: " + login + ", Senha: " + senha);
            
            // Exibe na janela
            out.writeObject("Login recebido: " + login);
            out.flush();

            // Verifica o login 
            Usuario usuario = ctrlUsu.findByLoginSenha(login, senha);

            if (usuario == null) {
                out.writeObject("Acesso negado. Encerrando conexao.");
                out.flush();
                System.out.println("Usuario invalido.");
                return;
            }

            out.writeObject("Acesso permitido. Usuario conectado com sucesso.");
            out.flush();
            System.out.println("Usuario autenticado com sucesso.");

            // Loop de resposta
            boolean conectado = true;

            while (conectado) {
                
            Object objComando;
            try {
                objComando = in.readObject();
            } catch (EOFException e) {
                break;
            }

                if (!(objComando instanceof String comandoRaw)) break;
                String comando = comandoRaw.trim().toUpperCase();
                
                System.out.println("Comando recebido: " + comando);

                switch (comando) {
                    case "L":    // Comando "L" - Listar produtos
                        System.out.println("Listando produtos...");
                        out.writeObject("Listando produtos...");
                        Collection<Produto> produtos = ctrlProd.findProdutoEntities();
                        out.writeObject(produtos);
                        out.flush();
                        break;

                    case "X":    // Comando "X" - Sair
                        out.writeObject("Conexao finalizada pelo cliente.");
                        out.flush();
                        conectado = false;
                        break;

                    case "E": // Comando "E" - Entrada de produto - COMPRA
                        try {                       
                        
                            // Recebe os dados da movimentacao
                            Integer idComprador = in.readInt();
                            Integer idVendedor = in.readInt();
                            Integer idProduto = in.readInt();
                            Integer quantidade = in.readInt();
                            BigDecimal valorUnitario = (BigDecimal) in.readObject();

                            System.out.println("Recebendo dados da movimentação...");
                            System.out.println("Comprador: " + idComprador + ", Vendedor: " + idVendedor +", Produto: " + idProduto + ", Quantidade: " + quantidade );
                            
                            // Recupera a pessoa e o produto
                            Pessoa comprador = ctrlPessoa.findPessoa(idComprador);
                            Pessoa vendedor = ctrlPessoa.findPessoa(idVendedor);
                            Produto produto = ctrlProd.findProduto(idProduto);
                            
                            if (comprador == null || vendedor == null || produto == null) {
                                out.writeObject("Comprador, Vendedor ou Produto nao encontrado.");
                                out.flush();
                                break;
                            }

                            // Cria o objeto Movimento
                            Operacao movimento = new Operacao();
                            movimento.setDataOperacao(new Date());
                            movimento.setQuantidadeOperacao(quantidade);
                            movimento.setPrecoUnitarioOperacao(valorUnitario);
                            movimento.setCodProduto(Produto);
                            
                            movimento.setCodComprador(idComprador);
                            movimento.setCodVendedor(idVendedor);
                            movimento.setTipoOperacao('C');
                            
                            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);

                            // Persiste o movimento
                            ctrlMov.create(movimento);
                            ctrlProd.edit(produto);

                            out.writeObject("Movimento de Compra registrado com sucesso.");
                            out.flush();
                        } catch (Exception e) {
                            out.writeObject("Erro durante a movimentacao de Compra." + e.getMessage());
                            out.flush();
                            System.err.println("Erro ao processar movimentacao de Compra: " + e.getMessage());
                            conectado = false;
                        }
                        break;
                        
                    case "S": // Comando "S" - Saída de produto   - VENDA
                        try {                       
                        
                            // Recebe os dados da movimentacao
                            Integer idPessoa = in.readInt();
                            Integer idProduto = in.readInt();
                            Integer quantidade = in.readInt();
                            BigDecimal valorUnitario = (BigDecimal) in.readObject();

                            // Recupera a pessoa e o produto
                            Pessoa pessoa = ctrlPessoa.findPessoa(idPessoa);
                            Produto produto = ctrlProd.findProduto(idProduto);

                            if (pessoa == null || produto == null) {
                                out.writeObject("Pessoa ou Produto nao encontrado.");
                                out.flush();
                                break;
                            }

                            // Cria o objeto Movimento
                            Operacao movimento = new Operacao();
                            movimento.setDataOperacao(new Date());
                            movimento.setQuantidadeOperacao(quantidade);
                            movimento.setPrecoUnitarioOperacao(valorUnitario);
                            movimento.setCodProduto(produto);
                            
                            movimento.setCodVendedor(pessoa);
                            movimento.setCodComprador(null);
                            movimento.setTipoOperacao('V');
                                                    
                            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);

                            // Persiste o movimento
                            ctrlMov.create(movimento);
                            ctrlProd.edit(produto);

                            out.writeObject("Movimento registrado com sucesso.");
                            out.flush();
                        } catch (Exception e) {
                            out.writeObject("Erro durante a movimentacao de Venda." + e.getMessage());
                            out.flush();
                            System.err.println("Erro ao processar movimentação: " + e.getMessage());
                            conectado = false;
                        }
                        break;
                        
                    default:
                        out.writeObject("Comando invalido.");
                        out.flush();
                        break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Erro na thread [" + Thread.currentThread().getName() + "]: " + e.getClass().getSimpleName());
            System.err.println("[DEBUG:CadastroThreadV2:catch@run] Erro na thread [" + Thread.currentThread().getName() + "]: "
    + e.getClass().getSimpleName() + (e.getMessage() != null ? " - " + e.getMessage() : " - sem mensagem"));
        } finally {
            try {
                if (!s1.isClosed()) s1.close();
            } catch (IOException e) {
                System.err.println("[DEBUG:CadastroThreadV2:catch@run] Erro ao fechar o socket: " + e.getMessage());
            }
        }   
    }
}