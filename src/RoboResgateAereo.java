public class RoboResgateAereo extends RoboAereo {
    int capacidadeVitimas;
    boolean modoEmergencia;

    public RoboResgateAereo(String nome, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, int capacidadeVitimas, int modoEmergencia) {
        super(nome, posicaoX, posicaoY, altitude, altitudeMaxima);
        this.capacidadeVitimas = capacidadeVitimas;
        this.modoEmergencia = false; // robô inicia com modo emergência desativado
    }

    public void carregarVitima() {
        if (capacidadeVitimas < 0) {
            System.out.println(nome + " não pode resgatar mais vítimas");
        } else {
            capacidadeVitimas--;
            System.out.println(nome + " resgatou uma vítima");
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
            System.out.println(nome + " pode sobrevoar o terreno seguramente");
        } else {
            System.out.println(nome + " não pode sobrevoar o terreno seguramente");
        }
    }
}
