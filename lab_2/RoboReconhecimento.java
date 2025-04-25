/*
esta classe de robô é responsável por realizar um reconhecimento da área de ataque

funções:
- reconhecimento (altitude máxima)
 */

public class RoboReconhecimento extends RoboAereo {
    boolean modoReconhecimento; // move robô para posição mais alta

    public RoboReconhecimento(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, boolean modoReconhecimento) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.modoReconhecimento = false;
    }

    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            if (ambiente.posicaoOcupada(posicaoX, posicaoY, altitudeMaxima, this)) {
                System.out.println(nome + " não pode ativar modo reconhecimento: posição no topo já ocupada");
                return;
            }
            System.out.println("modo reconhecimento ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - altitude); // sobe para altitude máxima
            modoReconhecimento = true;
        } else {
            System.out.println("modo reconhecimento já está ativado");
        }
    }

    public void fazerReconhecimento(Ambiente ambiente) {

        if (!modoReconhecimento || altitude < altitudeMaxima) {
            System.out.println(nome + " não pode realizar reconhecimento. ative o modo reconhecimento");
        } else {
            System.out.println("realizando reconhecimento...");

            boolean encontrou = false;
            for (Robo r : ambiente.encontrarRobosAtivos()) {
                if (r != this) {
                    if (r instanceof RoboAereo) {
                        System.out.println("- " + r.nome + " localizado em (" + r.posicaoX + ", " + r.posicaoY + ", " + altitude + ")");
                    } else {
                        System.out.println("- " + r.nome + " localizado em (" + r.posicaoX + ", " + r.posicaoY + ")");
                    }
                }
            }

            if (!encontrou) {
                System.out.println("nenhum robô detectado");
            }
        }
    }
}
