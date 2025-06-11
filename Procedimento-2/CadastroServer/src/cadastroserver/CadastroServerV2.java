/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroserver;

import Controller.*;

import java.net.ServerSocket;
import java.net.Socket;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroServerV2 {

    public static void main(String[] args) {

        // Cria EntityManagerFactory a partir da unidade de persistência
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");

        // Instancia os controladores necessários
        ProdutoJpaController ctrlProduto = new ProdutoJpaController(emf);
        UsuarioJpaController ctrlUsuario = new UsuarioJpaController(emf);
        OperacaoJpaController ctrlMovimento = new OperacaoJpaController(emf);
        PessoaJpaController ctrlPessoa = new PessoaJpaController(emf);

        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor aguardando conexoes na porta 4321...");

            // Loop infinito para aceitar várias conexões de clientes
            while (true) {
                Socket socket = serverSocket.accept(); // Aguarda conexao
                System.out.println("Novo cliente conectado: " + socket.getInetAddress());

                // Cria uma nova thread para cada cliente usando CadastroThreadV2
                CadastroThreadV2 thread = new CadastroThreadV2(
                        ctrlProduto, ctrlUsuario, ctrlMovimento, ctrlPessoa, socket
                );
                thread.start(); // Inicia a thread

                // Servidor continua livre para receber outras conexoes
            }

        } catch (Exception ex) {
            System.err.println("[DEBUG:CadastroServer:catch@run] Erro no servidor: " + ex.getMessage());
        } finally {
            emf.close(); // Fecha o EntityManagerFactory ao encerrar o servidor
        }
    }
}
