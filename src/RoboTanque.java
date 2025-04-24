/*
roboTanque: responsável por atirar misseis em robos alvos.
cada robo pode carregar ate 2 misseis (misseis sao recarregaveis).
cada robo tem um modo defesa, que reduz o dano recebido em 20%.
o modo defesa pode ser utilizado ate 4 vezes (blindagem).
o modo defesa precisa ser reativado apos o robo receber dano.
o dano do missil é fatal.
 */

import java.util.ArrayList;

public class RoboTanque extends RoboTerrestre {
    private int numMissil, blindagem;
    boolean modoDefesa;
    ArrayList<Sensor> sensores;

    public RoboTanque(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima) {
        super(nome, direcao, vida, posicaoX, posicaoY, velocidadeMaxima);
        this.numMissil = 2;
        this.blindagem = 4;
        this.modoDefesa = false;
        this.sensores = new ArrayList<>();

    }

    public void ativarModoDefesa() {
        if (blindagem > 0) {
            modoDefesa = true;
            blindagem--;

            System.out.println(nome + " ativou o modo defesa. blindagem restante: " + blindagem);
        } else {
            System.out.println(nome + " nao pode ativar o modo defesa. nenhuma blindagem restante");
        }
    }

    @Override
    protected void receberDano(int dano) {
        if (modoDefesa) {
            int danoEfetivo = (int) (0.8 * dano);
            vida -= danoEfetivo;
            modoDefesa = false;

            System.out.println(nome + " recebeu " + danoEfetivo + " de dano (modo defesa). Vida restante: " + vida);
        } else {
            vida -= dano;
            System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);
        }

        if (vida <= 0) {
            System.out.println(nome + " foi destruído!");

            if (getAmbiente() != null) {
                getAmbiente().removerRobo(this);
            }
        }
    }



    public void recarregarMissil() {
        if (numMissil < 2) {
            numMissil = 2;

            System.out.println(nome + " recarregou seus mísseis");
        } else {
            System.out.println(nome + " só pode carregar 2 mísseis simultaneamente");
        }
    }

    public void dispararMissil(Robo alvo) {
        if (numMissil > 0) {
            numMissil--;

            System.out.println(nome + " disparou míssil em " + alvo.nome);

            alvo.receberDano(alvo.vida); // dano fatal
            System.out.println(alvo.nome + " morreu");
            // remover alvo do mapa
        } else {
            System.out.println(nome + " nao possui mísseis restantes. é necessário recarregar");
        }
    }
}
