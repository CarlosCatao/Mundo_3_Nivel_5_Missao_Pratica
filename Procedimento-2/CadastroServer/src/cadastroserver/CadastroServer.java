/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroserver;

import Controller.UsuarioJpaController;
import Controller.ProdutoJpaController;

import java.net.ServerSocket;
import java.net.Socket;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroServer {

    public static void main(String[] args) {
        
        // Instancia o EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CadastroServerPU");

        // Instancia o controlador de produtos
        ProdutoJpaController ctrl = new ProdutoJpaController(emf);

        // Instancia o controlador de usuários
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);
        
        try (ServerSocket serverSocket = new ServerSocket(4321)) {
            System.out.println("Servidor aguardando conexões na porta 4321...");

            // Loop infinito aguardando conexoes
            while (true) {
                
                Socket socket = serverSocket.accept(); // aceita conexao
                System.out.println("Novo cliente conectado: " + socket.getInetAddress());
                
                // Cria e inicia uma nova thread para o cliente
                CadastroThread thread = new CadastroThread(ctrl, ctrlUsu, socket);
                thread.start();
                
                // Servidor continua livre para receber novas conexoes
            }
                
        } catch (Exception ex) {
            System.err.println("Erro no servidor: " + ex.getMessage());
        } finally {
            emf.close();   // fecha o EntityManagerFactory
        }
    }
}
