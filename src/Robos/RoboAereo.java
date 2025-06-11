package Robos;/*
roboAereo: classe de robos voadores.
nao ha limite de velocidade para robos voadores (tempo = -1)
 */

import AmbienteP.Ambiente;

public class RoboAereo extends Robo {
    public int altitudeMaxima;

    public RoboAereo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, ambiente);
        this.altitudeMaxima = altitudeMaxima;
    }

    public void subir(int metros) {
        int novoZ = posicaoZ + metros;

        super.mover(0,0, novoZ, -1);
    }

    public void descer(int metros) {
        int novoZ = posicaoZ - metros;

        super.mover(0,0, novoZ, -1);
    }

    @Override
    public void executarTarefa() {

    }
}
