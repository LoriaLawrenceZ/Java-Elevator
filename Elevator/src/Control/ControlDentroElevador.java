package Control;

import javax.swing.JTextField;

import java.util.Timer;
import java.util.TimerTask;

import Model.Elevador;
import View.BotoesElevador;

public class ControlDentroElevador {
    //-----===| ATRIBUTOS |===-----//
    //---=| ELEVADORES |=---//
    private Elevador elevador;
    private JTextField mostrarAndarElevador;
    private JTextField nomeElevador;
    private static JTextField infoElevador;

    //---=| CONSTRUTOR |=---//
    public ControlDentroElevador(Elevador elevador, JTextField mostrarAndarElevador, JTextField nomeElevador, JTextField infoElevador){
        this.elevador = elevador;
        this.mostrarAndarElevador = mostrarAndarElevador;
        this.nomeElevador = nomeElevador;
        this.infoElevador = infoElevador;
        mostrarAndarElevador.setText(""+elevador.getAndarAtual());
    }

    //-----===| MÉTODOS |===-----//
    // Verificar se é o botão de andar ou de SAIR
    public boolean check(String andarEscolhido){
        if(andarEscolhido.matches("-?\\d+")){
            return true;
        } else {
            infoElevador.setText("PARADO");
            return false;
        }
    }

    // Lógica paraescolha de andar válida
    public void escolha(Integer andarEscolhido){
        Integer subirOuDescer = elevador.getAndarAtual() - andarEscolhido; // verificando se vai mostrar q vai subir ou descer
        
        if(subirOuDescer < 0){
            nomeElevador.setText("ELEVADOR "+elevador.getIdElevador()+" | "+andarEscolhido);
            infoElevador.setText("SUBINDO");
        } else if(subirOuDescer == 0){
            nomeElevador.setText("ELEVADOR "+elevador.getIdElevador()+" | "+andarEscolhido);
            infoElevador.setText("ABRINDO");
        } else{
            nomeElevador.setText("ELEVADOR "+elevador.getIdElevador()+" | "+andarEscolhido);
            infoElevador.setText("DESCENDO");
        }

        // Depois de tdo o funcionamento do elevador, "redefinir" como PARADO (Depois de 1 segundo "movimentando")
        Timer timer = new Timer(); // Criando um Timer
        timer.schedule(new Parando(), 1000);

        // Setando andar atual do elevador com o andar escolhido
        elevador.setAndarAtual(andarEscolhido);
    }

    // Timer para "zerar" o elevador (PARADO)
    static class Parando extends TimerTask {
        @Override
        public void run() {
            infoElevador.setText("PARADO");
        }
    }
}
