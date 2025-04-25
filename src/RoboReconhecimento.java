/*
roboReconhecimento: responsável por realizar um reconhecimento do mapa.
cada robo tem um modo de reconhecimento, que eleva o robo ate sua altitude maxima.
*/

import java.util.ArrayList;

public class RoboReconhecimento extends RoboAereo {
    boolean modoReconhecimento;
    ArrayList<Robo> mapaReconhecimento;

    public RoboReconhecimento(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, altitudeMaxima, ambiente);
        this.modoReconhecimento = false;
        this.mapaReconhecimento = new ArrayList<>();
    }

    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            modoReconhecimento = true;
            System.out.println("modo reconhecimento ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - posicaoZ);
        } else {
            System.out.println(nome + " já está em modo reconhecimento");
        }
    }
}