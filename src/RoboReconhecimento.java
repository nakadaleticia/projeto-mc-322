/*
esta classe de robô é responsável por realizar um reconhecimento da área de ataque

funções:
- reconhecimento (altitude máxima)
 */

import java.util.ArrayList;

public class RoboReconhecimento extends RoboAereo {
    boolean modoReconhecimento; // move robô para posição mais alta

    public RoboReconhecimento(String nome, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, boolean modoReconhecimento) {
        super(nome, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.modoReconhecimento = false;
    }

    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            subir(altitudeMaxima - altitude); // sobe para altitude máxima
            modoReconhecimento = true;
            System.out.println("modo reconhecimento ativado");
        } else {
            System.out.println("modo reconhecimento já está ativado");
        }
    }

    public void fazerReconhecimento(Ambiente ambiente) {

        if (!modoReconhecimento || altitude != altitudeMaxima) {
            System.out.println(nome + " não pode realizar reconhecimento");
        } else {
            System.out.println("realizando reconhecimento...");

            boolean encontrou = false;
            for (Robo r : ambiente.encontrarRobosAtivos()) {
                encontrou = false;

                if (r != this) {
                    System.out.println("- " + r.nome + " localizado em (" + r.posicaoX + "; " + r.posicaoY + ")");
                }
            }

            if (!encontrou) {
                System.out.println("nenhum robô detectado");
            }
        }
    }
}
