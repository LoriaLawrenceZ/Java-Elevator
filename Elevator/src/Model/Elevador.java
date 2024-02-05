package Model;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class Elevador {
    //-----===| ATRIBUTOS |===-----//
    private Integer idElevador;
    private Integer andarAtual = 0;

    //-----===| CONSTRUTOR |===-----//
    public Elevador(Integer idElevador) {
        this.idElevador = idElevador;
    }
    
    //-----===| GETTERS & SETTERS |===-----//
    public Integer getAndarAtual() {
        return andarAtual;
    }
    public Elevador setAndarAtual(Integer andarAtual) {
        this.andarAtual = andarAtual;
        return this;
    }

    public Integer getIdElevador() {
        return idElevador;
    }
    public Elevador setIdElevador(Integer idElevador) {
        this.idElevador = idElevador;
        return this;
    }    
}
