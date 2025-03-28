/*
esta classe de robô é responsável por realizar resgates de vítimas

funções:
- resgatar vítimas
- evacuar da área de perigo quando estiver com o modo defesa ativado (altitude máxima)
 */

public class RoboResgateAereo extends RoboAereo {
    int capacidadeVitimas;
    boolean modoEmergencia;

    public RoboResgateAereo(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, int capacidadeVitimas, boolean modoEmergencia) {
        super(nome, direcao, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.capacidadeVitimas = capacidadeVitimas;
        this.modoEmergencia = false; // robô inicia com modo emergência desativado
    }

    public void carregarVitima() {
        if (capacidadeVitimas <= 0) {
            System.out.println(nome + " não pode resgatar mais vítimas");
        } else {
            capacidadeVitimas--;
            System.out.println(nome + " resgatou uma vítima. capacidade atual: " + capacidadeVitimas);
        }
    }

    public void ativarModoEmergencia() {
        if (!modoEmergencia) {
            modoEmergencia = true;
            System.out.println("modo emergência ativado");
        } else {
            System.out.println("modo emergência já está ativado");
        }
    }

    /* evacuação de emergência deve ser ativado para robô sobrevoar pelo mapa até encontrar um local seguro (altitude máxima = voo liberado) */
    public void evacuacaoDeEmergencia() {
        if (modoEmergencia) {
            altitude = altitudeMaxima;
            System.out.println(nome + " está realizando evacuação de emergência...");
        } else {
            System.out.println(nome + " não pode sobrevoar o terreno seguramente. ative o modo emergência");
        }
    }
}
