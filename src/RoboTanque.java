public class RoboTanque extends RoboTerrestre {
    int misseis;
    int blindagem; // capacidade de absorção de dano
    boolean modoDefesa;

    public RoboTanque(String nome, int posicaoX, int posicaoY, int velocidade, int velocidadeMaxima, int misseis, int blindagem, boolean modoDefesa) {
        super(nome, posicaoX, posicaoY, velocidade, velocidadeMaxima);
        this.misseis = misseis;
        this.blindagem = blindagem;
        this.modoDefesa = false; // robô inicia com modo defesa desligado
    }

    public void dispararMissil() {
        if (misseis < 0) {
            System.out.println(nome + " está sem misseis disponíveis");
        } else {
            misseis--;
            System.out.println(nome + " disparou");
        }
    }

    public void ativarModoDefensivo() {
        if (!modoDefesa) {
            modoDefesa = true;
            blindagem += 10;
            System.out.println(nome + " ativou modo denfensivo");
        } else {
            System.out.println(nome + " já está em modo defensivo");
        }
    }

    public void receberDano(int dano) {
        int danoEfetivo = modoDefesa ? dano / 2 : dano;

        if (danoEfetivo >= blindagem) {
            blindagem = 0;
            System.out.println(nome + " foi destruído");
        } else {
            blindagem -= danoEfetivo;
            System.out.println(nome + " recebeu dano");
        }
    }
}
