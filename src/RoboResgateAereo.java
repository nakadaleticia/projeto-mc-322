/*
roboResgateAereo: responsável por realizar resgates de vitimas.
cada robo pode carregar até 5 vitimas.
cada robo tem um modo emergencia, que eleva o robo ate sua altitude maxima (evacuacao e resgate).
*/

public class RoboResgateAereo extends RoboAereo implements Sensoreavel {
    int capacidadeVitimas;
    boolean modoEmergencia;

    public RoboResgateAereo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, altitudeMaxima, ambiente);
        this.capacidadeVitimas = 5;
        this.modoEmergencia = false;
    }

    // ativa o modo emergência, eleva o robô para a altitude máxima
    public void ativarModoEmergencia() {
        if (!modoEmergencia) {
            modoEmergencia = true;
            System.out.println("modo emergência ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - posicaoZ);
        } else {
            System.out.println(nome + " já está em modo resgate");
        }
    }

    // resgata uma vítima em uma posição específica
    public void resgatarVitima(int posicaoVitimaX, int posicaoVitimaY) {
        int deltaX = posicaoVitimaX - posicaoX;
        int deltaY = posicaoVitimaY - posicaoY;

        if (modoEmergencia) {
            if (capacidadeVitimas > 0) {
                mover(deltaX, deltaY, 0, -1);
                System.out.println(nome + " resgatou uma vítima");
                capacidadeVitimas--;
            } else {
                System.out.println(nome + " não pode resgatar novas vítimas. é necessário evacuar");
            }
        } else {
            System.out.println(nome + " não pode realizar resgates. ative o modo emergência");
        }
    }

    // leva todas as vítimas para uma posição de evacuação
    public void evacuacaoDeVitimas(int posicaoEvacuacaoX, int posicaoEvacuacaoY) {
        int deltaX = posicaoEvacuacaoX - posicaoX;
        int deltaY = posicaoEvacuacaoY - posicaoY;

        mover(deltaX, deltaY, 0, -1);
        System.out.println(nome + " levou suas vítimas para (" + posicaoEvacuacaoX + ", " + posicaoEvacuacaoY + ")");
        capacidadeVitimas = 5;
    }

    // implementa a interface Sensoreavel
    public void acionarSensores() throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
    }
}
