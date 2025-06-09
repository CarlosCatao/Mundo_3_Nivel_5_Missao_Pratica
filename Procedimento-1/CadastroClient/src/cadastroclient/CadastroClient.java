/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collection;

import Model.Pessoa;
import Model.Produto;

public class CadastroClient {

    public static void main(String[] args) {
        String servidor = "localhost";
        int porta = 4321;

        try (
            // Instancia socket
            Socket socket = new Socket(servidor, porta);
                
            // Streams
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
        ) {
            
            System.out.println("Conectado ao servidor em " + servidor + ":" + porta);

            // Envia login e senha
            saida.writeObject("op1"); // login
            saida.writeObject("op1"); // senha
            saida.flush();
            
            // Recebe resposta do servidor (autenticado ou nao)
            Object resposta = entrada.readObject();
            System.out.println("Servidor: " + resposta);

            if (resposta instanceof String && ((String) resposta).contains("negado")) {
                System.out.println("Acesso negado pelo servidor. Encerrando...");
                return;
            }

            // Envia comando 'L' (listar entidades)
            saida.writeObject("L");
            saida.flush();

            // Recebe lista de produtos
            Object objetoRecebido = entrada.readObject();

            if (objetoRecebido instanceof Collection<?>) {
                Collection<?> produtos = (Collection<?>) objetoRecebido;

                // Apresenta o nome de cada entidade recebida
                System.out.println("\nProdutos:");
                for (Object obj : produtos) {
                    if (obj instanceof Produto) {
                        Produto p = (Produto) obj;

                        System.out.printf("- %s (Estoque: %d, Preço: %.2f)\n",
                                p.getDescricao(),
                                p.getQuantidadeEstoque(),
                                p.getPrecoUnitarioAtual());

                    } else {
                        System.out.println("- Objeto desconhecido: " + obj);
                    }
                }
            } else {
                System.out.println("Resposta inesperada do servidor.");
            }
            
            // Envia comando "S" para encerrar
            saida.writeObject("S");
            saida.flush();
            
            String encerramento = (String) entrada.readObject();
            System.out.println("Servidor: " + encerramento);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("\nConexao encerrada.");
    }
}
