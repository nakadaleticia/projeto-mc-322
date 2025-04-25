/*
esta classe de robô é responsável pela defesa

funções:
- disparar misseis
- absorver dano quando estiver com o modo defesa ativado
 */

public class RoboTanque extends RoboTerrestre {
    int misseis;
    int blindagem; // capacidade de absorção de dano
    boolean modoDefesa;

    public RoboTanque(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, int misseis, int blindagem, boolean modoDefesa) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima);
        this.misseis = misseis;
        this.blindagem = blindagem;
        this.modoDefesa = false; // robô inicia com modo defesa desligado
    }

    public void dispararMissil() {
        if (misseis <= 0) {
            System.out.println(nome + " está sem misseis disponíveis");
        } else {
            misseis--;
            System.out.println(nome + " disparou");
        }
    }

    // modo defensivo: blindagem aumenta em 10 unidades de defesa e dano efetivo = dano / 2
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
            System.out.println(nome + " recebeu " + dano + " de dano. blindagem: " + blindagem);
        }
    }
}
