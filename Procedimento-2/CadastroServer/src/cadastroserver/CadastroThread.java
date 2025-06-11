/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */
package cadastroserver;

import Controller.ProdutoJpaController;
import Controller.UsuarioJpaController;

import Model.Produto;
import Model.Usuario;

import java.io.*;
import java.net.Socket;
import java.util.Collection;

public class CadastroThread extends Thread {

    private final ProdutoJpaController ctrl;
    private final UsuarioJpaController ctrlUsu;
    private final Socket s1;

    // Construtor
    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
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
                
                // Verifica o login 
                 Usuario usuario = ctrlUsu.findByLoginSenha(login, senha);
                
                if (usuario == null) {
                    out.writeObject("Acesso negado. Encerrando conexao.");
                    out.flush();
                    System.out.println("Usuario invalido.");
                    return;
                }

                out.writeObject("Acesso permitido");
                out.flush();
                System.out.println("Usuario autenticado com sucesso.");

                // Loop de resposta
                 boolean conectado = true;
                 
                while (conectado) {
                    String comando = (String) in.readObject();
                    System.out.println("Comando recebido: " + comando);

                    switch (comando.toUpperCase()) {
                        case "L":    // Comando "L" - Listar produtos
                            System.out.println("Listando produtos...");
                            Collection<Produto> produtos = ctrl.findProdutoEntities();
                            out.writeObject(produtos);
                            out.flush();
                            break;
                        case "S":    // Comando "S" - Sair
                            out.writeObject("Sessao encerrada");
                            out.flush();
                            conectado = false;
                            break;
                        default:
                            out.writeObject("Comando invalido.");
                            out.flush();
                            break;
                    }
                }
                
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro na thread: " + e.getMessage());
        } finally {
            try {
                s1.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar o socket: " + e.getMessage());
            }
        }
    }
}
