/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroclientv2;

import java.awt.Frame;
import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Date;

import javax.swing.*;

public class CadastroClientV2 {
    public static void main(String[] args) {
        Socket socket = null;
        ObjectOutputStream saida = null;
        ObjectInputStream entrada = null;
        ThreadClient thread = null;
        //JFrame frame = null;

        try {
            // Conectar ao servidor
            socket = new Socket("localhost", 4321);
            
            // streams
            saida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());

            // Enviar login e senha
            saida.writeObject("op1");
            saida.writeObject("op1");
            saida.flush();

            // Criar janela para mensagens e iniciar thread de leitura
            JFrame frame = new JFrame("Mensagens do Servidor");
            JTextArea textArea = new JTextArea(50, 100);
            textArea.setEditable(false);
            frame.add(new JScrollPane(textArea));
            frame.pack();
            frame.setVisible(true);

            // Inicia thread de leitura
            thread = new ThreadClient(entrada, textArea);
            thread.start();

            // Inicializar leitor de teclado
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
                                    
            // Mensagem de início da comunicação
            SwingUtilities.invokeLater(() -> 
                    textArea.append("Mensagem: => Nova comunicação em " + new Date() + "\n"));
            
            // Loop de menu
            String comando = "";
            while (!comando.equalsIgnoreCase("X")) {
                System.out.println("\nMenu:");
                System.out.println("L – Listar");
                System.out.println("E – Entrada");
                System.out.println("S – Saída");
                System.out.println("X – Finalizar");
                System.out.print("Escolha a opcao desejada: ");
                comando = teclado.readLine().toUpperCase();

                try {
                    switch (comando) {
                        case "L":
                            saida.writeObject("L");
                            break;
                        case "E":
                            saida.writeObject("E");
                            
                            System.out.print("Id do Comprador: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));
                            
                            System.out.print("Id do Vendedor: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));
                            

                            System.out.print("Id do produto: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));

                            System.out.print("Quantidade: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));

                            System.out.print("Valor unitário: ");
                            saida.writeObject(new BigDecimal(teclado.readLine().replace(",", ".")));

                            saida.flush();
                            break;
                        case "S":
                            saida.writeObject("S");
                            
                            System.out.print("Id do Comprador: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));
                            
                            System.out.print("Id do Vendedor: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));
                            

                            System.out.print("Id do produto: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));

                            System.out.print("Quantidade: ");
                            saida.writeInt(Integer.parseInt(teclado.readLine()));

                            System.out.print("Valor unitário: ");
                            saida.writeObject(new BigDecimal(teclado.readLine().replace(",", ".")));

                            saida.flush();
                            break;
                        case "X":
                            saida.writeObject("X");
                            saida.flush();
                            thread.stopThread();    // Finaliza a thread de leitura
                            break;
                        default:
                            System.out.println("Comando invalido.");
                            break;
                    }
                } catch (IOException | NumberFormatException e) {
                    System.err.println("Erro ao enviar dados: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro na conexao: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (saida != null) saida.close();
                if (socket != null && !socket.isClosed()) socket.close();
                
                // Fecha a janela Swing
                try {
                    SwingUtilities.invokeAndWait(() -> {
                        Frame[] frames = JFrame.getFrames();
                        for (Frame f : frames) {
                            f.dispose();
                        }   
                    });
                } catch (Exception e) {
                    System.err.println("Erro ao fechar a janela: " + e.getMessage());
                }

            System.out.println("Conexao encerrada com sucesso.");
            System.exit(0);
                
            } catch (IOException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}
