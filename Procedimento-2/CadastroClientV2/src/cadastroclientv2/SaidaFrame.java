/*
 * Desenvolvedor Full Stack
 * Carlos Altomare Catao
 * matricula: 20240346.0912
 * EAD - Polo Santa Luiza - Vitoria - ES
 */

package cadastroclientv2;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class SaidaFrame extends JDialog {
    public JTextArea texto;

    public SaidaFrame() {
        // Define titulo e dimensoes
        setTitle("Mensagens do Servidor");
        setBounds(700, 250, 500, 300);  // x, y, largura, altura
        
        // Define como nao modal (pode interagir com outras janelas)
        setModal(false);

        // Cria o JTextArea
        texto = new JTextArea();
        texto.setEditable(false);   // apenas leitura
        texto.setLineWrap(true);        // Quebra de linha automática
        texto.setWrapStyleWord(true);   // Quebra entre palavras

        // Adiciona o JTextArea dentro de um JScrollPane para permitir scroll
        JScrollPane scrollPane = new JScrollPane(texto);
        
        // Adiciona o JScrollPane na janela
        add(scrollPane);

        // Fecha apenas esta janela, sem encerrar o app
        // setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        // Torna visivel
        // setVisible(true);
    }
}
