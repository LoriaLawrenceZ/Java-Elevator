package Control;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import Model.Elevador;
import View.Apartamento;
import View.BotoesElevador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControlChamadaDeElevador {
    //-----===| ATRIBUTOS |===-----//
    //---=| ELEVADORES |=---//
    private Elevador elevadorUm;
    private Elevador elevadorDois;
    //---=| PAINEL |=---//
    JPanel painel; // Painel necessário para passar info para BotoesElevador.java

    String retorno;

    //-----===| CONSTRUTOR |===-----//
    public ControlChamadaDeElevador(JPanel painel) {
        elevadorUm = new Elevador(1);
        elevadorDois = new Elevador(2);
        this.painel = painel;
    }

    //-----===| MÉTODOS |===-----//
    public void chamarElevador(Integer andarChamado, JTextField infoEUm, JTextField infoEDois, JTextField nomeEUm, JTextField nomeEDois){
        Integer distanciaUm = Math.abs(elevadorUm.getAndarAtual() - andarChamado);
        Integer distanciaDois = Math.abs(elevadorDois.getAndarAtual() - andarChamado);
        Integer subirOuDescer; // Variável para calcular e mostrar se vai subir ou descer;

        if(distanciaUm > distanciaDois){ // Dois mais próximo, dois vai
            subirOuDescer = elevadorDois.getAndarAtual() - andarChamado; // verificando se vai mostrar q vai subir ou descer

            if(subirOuDescer < 0){
                elevadorDois.setAndarAtual(andarChamado);
                infoEDois.setText("SUBINDO");
                timer(2, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else if(subirOuDescer == 0){
                infoEDois.setText("ABRINDO");
                timer(2, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else{
                elevadorDois.setAndarAtual(andarChamado);
                infoEDois.setText("DESCENDO");
                timer(2, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            }
        }
        else if(distanciaUm == distanciaDois){ // Os dois iguais, UM vai
            subirOuDescer = elevadorUm.getAndarAtual() - andarChamado; // verificando se vai mostrar q vai subir ou descer
            
            if(subirOuDescer < 0){
                elevadorUm.setAndarAtual(andarChamado);
                infoEUm.setText("SUBINDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else if(subirOuDescer == 0){
                infoEUm.setText("ABRINDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else{
                elevadorUm.setAndarAtual(andarChamado);
                infoEUm.setText("DESCENDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            }
        }
        else{ // Um mais próximo, um vai
            subirOuDescer = elevadorUm.getAndarAtual() - andarChamado; // verificando se vai mostrar q vai subir ou descer

            if(subirOuDescer < 0){
                elevadorUm.setAndarAtual(andarChamado);
                infoEUm.setText("SUBINDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else if(subirOuDescer == 0){
                infoEUm.setText("ABRINDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            } else{
                elevadorUm.setAndarAtual(andarChamado);
                infoEUm.setText("DESCENDO");
                timer(1, infoEUm, infoEDois, andarChamado, nomeEUm, nomeEDois);
            }
        }
    }

    //---=| TIMER |=---//
    private void timer(Integer elevador, JTextField infoEUm, JTextField infoEDois, Integer andarAtual, JTextField nomeEUm, JTextField nomeEDois){
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BotoesElevador btnsElevador;
                // Mensagem de aberto dependendo do elevador
                if(elevador == 1){
                    nomeEUm.setText("ELEVADOR 1 | "+andarAtual);
                    infoEUm.setText("ABERTO");
                    btnsElevador = new BotoesElevador(painel, andarAtual, elevadorUm, nomeEUm, infoEUm); // Chamando frame do elevador
                } else {
                    nomeEDois.setText("ELEVADOR 2 | "+andarAtual);
                    infoEDois.setText("ABERTO");
                    btnsElevador = new BotoesElevador(painel, andarAtual, elevadorDois, nomeEDois, infoEDois); // Chamando frame do elevador
                }

                btnsElevador.setVisible(true);
            }
        });
        timer.setRepeats(false); // Apenas uma execução
        timer.start();
    }
}
