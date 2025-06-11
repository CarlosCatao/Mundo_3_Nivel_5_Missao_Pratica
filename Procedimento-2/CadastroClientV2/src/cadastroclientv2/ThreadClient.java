/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroclientv2;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;

import Model.Produto;

public class ThreadClient extends Thread {
    private final ObjectInputStream entrada;    
    private final JTextArea textArea;
    private volatile boolean running = true;

    public ThreadClient(ObjectInputStream entrada, JTextArea textArea) {
        this.entrada = entrada;
        this.textArea = textArea;
    }    

    public void stopThread() {
        running = false;
    }
    
    @Override
    public void run() {
        try {
            while (running) {
            Object obj = entrada.readObject();

            if (obj instanceof String mensagem) {
                if (mensagem.equalsIgnoreCase("Conexao finalizada pelo cliente.")) {
                    running = false;
                }
                SwingUtilities.invokeLater(() -> 
                    textArea.append("Mensagem: " + mensagem + "\n"));
            } else if (obj instanceof List<?>) {
                    List<?> lista = (List<?>) obj;                     
                    SwingUtilities.invokeLater(() -> {

                        for (Object item : lista) {
                            if (item instanceof Produto produto) {
                                textArea.append(
                                      "Codigo: " + produto.getCodProduto() +
                                      " | Produto: " + produto.getDescricao() + 
                                      " | Quantidade: " + produto.getQuantidadeEstoque() +
                                      " | Preco Unitario: " + produto.getPrecoUnitarioAtual() + "\n");
                            } else {
                                textArea.append("--> Objeto nao reconhecido na Lista.\n");
                            }
                        }
                   }); 
                } else {
                    SwingUtilities.invokeLater(() -> 
                    textArea.append("--> Recebido Tipo de objeto desconhecido.\n")
                    );
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> 
            textArea.append("Conexao encerrada pelo Cliente ou erro de leitura. Finalizando thread..\n")
            );
        }                        
    }    
}
