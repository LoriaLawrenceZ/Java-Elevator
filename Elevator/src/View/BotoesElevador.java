package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Control.ControlDentroElevador;
import Model.Elevador;

public class BotoesElevador extends JDialog {
    //-----===| ATRIBUTOS |===-----//
    //---=| GERAL |=---//
    JPanel painelGeral;
    // Criando componentes
    JTextField mostrarAndarElevador = new JTextField("T"); // Info em cima dos botões
    // Botões
    JButton btnAndarSubDois = new JButton("-2");
    JButton btnAndarSubUm = new JButton("-1");
    JButton btnAndarTerreo = new JButton("0");
    JButton btnAndarUm = new JButton("1");
    JButton btnAndarDois = new JButton("2");
    JButton btnAndarTres = new JButton("3");
    JButton btnAndarQuatro = new JButton("4");
    JButton btnAndarCinco = new JButton("5");
    JButton btnAndarSeis = new JButton("6");
    JButton btnSair = new JButton("SAIR");
    JButton[] controleDeBotoes = new JButton[10];
    // Componentes
    ArrayList<JComponent> componentesGeral = new ArrayList<JComponent>(){
        {
            //-=| INFO |=-//
            add(mostrarAndarElevador); // 2
            //-=| ANDARES |=-//
            add(btnAndarCinco); // 1
            add(btnAndarSeis); // 1
            add(btnAndarTres); // 1
            add(btnAndarQuatro); // 1
            add(btnAndarUm); // 1
            add(btnAndarDois); // 1
            add(btnAndarTerreo); // 2
            add(btnAndarSubDois); // 1
            add(btnAndarSubUm); // 1
            //-=| SAIR |=-//
            add(btnSair);
        }
    };
    //---=| CONTROL |=---//
    ControlDentroElevador control;

    //-----===| CONSTRUTOR |===-----///
    public BotoesElevador(JPanel parent, Integer andarAtual, Elevador elevador, JTextField nomeElevador, JTextField infoElevador){
        super((JFrame) SwingUtilities.getWindowAncestor(parent), "ELEVADOR", true);
        // Criando e adicionando painelGeral
        painelGeral = criarPanel(andarAtual);
        control = new ControlDentroElevador(elevador, mostrarAndarElevador, nomeElevador, infoElevador); // Instanciadno control corretamente
        this.add(painelGeral); // Adicionando painel ao JDialog

        //---=| Setando o Frame |=---//
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(150, 300);
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }

    private JPanel criarPanel(Integer andarAtual){
        JPanel painel = new JPanel();

        // Setando layout
        painel.setLayout(new GridBagLayout());
        // Setando Background
        painel.setBackground(Color.DARK_GRAY);
        // Configurando Painel
        GridBagConstraints elemento = new GridBagConstraints(); // Variável de controle de exibição de cada elemento
        // Declarando os itens a serem utilizados (sua posição no ArrayList)
        int[] posicaoNoArray = {
            0, // Info Elevador
            1, 2, // 5, 6
            3, 4, // 3, 4
            5, 6, // 1, 2
            7, // T
            8, 9, // S2, S1
            10 // SAIR
        };
        // Declarando os valores de cada item
        int[][] posicaoComponentes = {
            {0, 0, 2, 1, 1, 1, 3, 3, 3, 3}, // INFO

            {0, 1, 1, 1, 1, 1, 3, 3, 3, 3}, // 5
            {1, 1, 2, 1, 1, 1, 3, 3, 3, 3}, // 6
            {0, 2, 1, 1, 1, 1, 3, 3, 3, 3}, // 3
            {1, 2, 1, 1, 1, 1, 3, 3, 3, 3}, // 4
            {0, 3, 1, 1, 1, 1, 3, 3, 3, 3}, // 1
            {1, 3, 1, 1, 1, 1, 3, 3, 3, 3}, // 2
            {0, 4, 2, 1, 1, 1, 3, 3, 3, 3}, // T
            {0, 5, 1, 1, 1, 1, 3, 3, 3, 3}, // S2
            {1, 5, 1, 1, 1, 1, 3, 3, 3, 3}, // S1
            
            {0, 6, 2, 1, 1, 1, 3, 3, 3, 3}, // SAIR
        };
        // Configurand modelo de exibição
        for (int i = 0; i < 11; i++) { // Menor que 11 pois 11 é a quantidade de item da janela
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

        // Arrumando exibição do mostrarAndarElevador
        mostrarAndarElevador.setEditable(false);
        mostrarAndarElevador.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        mostrarAndarElevador.setBackground(Color.BLACK);
        mostrarAndarElevador.setForeground(Color.RED);

        //---=| Adicionando Evento |=---//
        // Adicionando botões ao controle de botões para poder fazer o loop for
        for (int i = 0; i < controleDeBotoes.length; i++) {
            controleDeBotoes[i] = (JButton) componentesGeral.get(i+1);
        }
        //---=| Botões
        for (JButton botao : controleDeBotoes) {
            botao.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt){
                    String andarEscolhido = botao.getText();
                    // Checar se é um botão "válido" ou sair
                    if(control.check(andarEscolhido)){
                        control.escolha(Integer.valueOf(andarEscolhido));
                        dispose();
                    } else{
                        dispose();
                    }
                }
            });
        }

        return painel;
    }
}
