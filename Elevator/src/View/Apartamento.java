package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Control.ControlChamadaDeElevador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.Elevador;

public class Apartamento extends JFrame {
    //-----===| ATRIBUTOS |===-----//
    //---=| GERAL |=---//
    JPanel painelGeral;
    // Andares
    JButton btnAndarSubDois = new JButton("-2");
    JButton btnAndarSubUm = new JButton("-1");
    JButton btnAndarTerreo = new JButton("0");
    JButton btnAndarUm = new JButton("1");
    JButton btnAndarDois = new JButton("2");
    JButton btnAndarTres = new JButton("3");
    JButton btnAndarQuatro = new JButton("4");
    JButton btnAndarCinco = new JButton("5");
    JButton btnAndarSeis = new JButton("6");
    JButton[] controleDeBotoes = new JButton[9];
    // Info Elevadores
    JTextField nomeEUm = new JTextField("ELEVADOR 1 | 0");
    JTextField nomeEDois = new JTextField("ELEVADOR 2 | 0");
    JTextField infoEUm = new JTextField("PARADO");
    JTextField infoEDois = new JTextField("PARADO");
    // COmponentes
    ArrayList<JComponent> componentesGeral = new ArrayList<JComponent>(){
        {
            //-=| ANDARES |=-//
            add(btnAndarSubDois); //1
            add(btnAndarSubUm); //1
            add(btnAndarTerreo); //2
            add(btnAndarUm); //1
            add(btnAndarDois); //1
            add(btnAndarTres); //1
            add(btnAndarQuatro); //1
            add(btnAndarCinco); //1
            add(btnAndarSeis); //1
            //-=| Elevadores |=-//
            add(nomeEUm); //5
            add(nomeEDois); //5
            add(infoEUm); //5
            add(infoEDois); //5
        }
    };
    //---=| CONTROL |=---//
    ControlChamadaDeElevador control;

    //-----===| CONSTRUTOR |===-----//
    public Apartamento() {
        super();

        // Criando/organizando elementos dentro do painel geral
        painelGeral = criarPainel();
        control = new ControlChamadaDeElevador(painelGeral); //Instanciando control corretamente
        this.add(painelGeral); // Adicionando Painel geral ao frame

        //---=| Setando o Frame |=---//
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 250);
        this.setLocationRelativeTo(null);
        this.setTitle("APARTAMENTO");
    }

    //-----===| MÉTODOS |===-----//
    public JPanel criarPainel(){
        JPanel painel = new JPanel();

        // Setando layout
        painel.setLayout(new GridBagLayout());
        // Setando Background
        painel.setBackground(Color.DARK_GRAY);
        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento
        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArray = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, // S2, S1, T, 1, 2, 3, 4, 5, 6
            9, 10, //Nome Elevador 1 e 2
            11, 12 // Info Elevadores
        };
        // Declarando os valores de cada item
        int[][] posicaoComponentes = {
            {0, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // S2
            {1, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // S1
            {2, 0, 2, 1, 2, 1, 5, 5, 5, 5}, // T
            {4, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 1
            {5, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 2
            {6, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 3
            {7, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 4
            {8, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 5
            {9, 0, 1, 1, 1, 1, 5, 5, 5, 5}, // 6
            
            {0, 1, 5, 1, 5, 1, 5, 5, 5, 5}, // Nome Elevador 1
            {5, 1, 5, 1, 5, 1, 5, 5, 5, 5}, // Nome Elevador 1
            
            {0, 2, 5, 3, 5, 3, 5, 5, 5, 5}, // Info Elevador 1
            {5, 2, 5, 3, 5, 3, 5, 5, 5, 5}, // Info Elevador 2
        };
        // Configurand modelo de exibição
        for (int i = 0; i < 13; i++) { // Menor que 13 pois 13 é a quantidade de item da janela
            elemento.fill = GridBagConstraints.BOTH;
            elemento.gridx = posicaoComponentes[i][0];
            elemento.gridy = posicaoComponentes[i][1];
            elemento.gridwidth = posicaoComponentes[i][2];
            elemento.gridheight = posicaoComponentes[i][3];
            elemento.weightx = posicaoComponentes[i][4];
            elemento.weighty = posicaoComponentes[i][5];
            elemento.insets = new Insets(posicaoComponentes[i][6], posicaoComponentes[i][7], posicaoComponentes[i][8], posicaoComponentes[i][9]);

            painel.add(componentesGeral.get(posicaoNoArray[i]), elemento);
        }

        // Arrumando exibição dos Elevadores
        for (int i = 0; i < 4; i++) {
            JTextField texto = (JTextField) componentesGeral.get(i + 9);
            texto.setEditable(false);
            texto.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            texto.setBackground(Color.BLACK);
            texto.setForeground(Color.RED);
        }

        //---=| Adicionando Evento |=---//
        // Adicionando botões ao controle de botões para poder fazer o loop for
        for (int i = 0; i < controleDeBotoes.length; i++) {
            controleDeBotoes[i] = (JButton) componentesGeral.get(i);
        }
        //---=| Botões
        for (JButton botao : controleDeBotoes) {
            botao.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent evt){
                    Integer andarChamado = Integer.valueOf(botao.getText());

                    control.chamarElevador(andarChamado, infoEUm, infoEDois, nomeEUm, nomeEDois);
                }
            });
        }

        return painel;
    }
}
