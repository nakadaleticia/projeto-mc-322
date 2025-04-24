/*
roboLancaChamas: responsável por lancar chama em robos alvos (TERRESTRES).
cada robo tem um tanque de combustivel de 100L.
o tanque de combustivel é recarregavel.
o disparo da chama gasta 5L de combustível.
o dano da chama é de 20% da vida do robo alvo.
 */
public class RoboLancaChamas extends RoboTerrestre {
    private int numCombustivel;

    public RoboLancaChamas(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima) {
        super(nome, direcao, vida, posicaoX, posicaoY, velocidadeMaxima);
        this.numCombustivel = 100;
    }

    public void recarregarCombustivel() {
        if (numCombustivel < 100) {
            numCombustivel = 100;

            System.out.println(nome + " recarregou o tanque de combustivel");
        } else {
            System.out.println("tanque de combustível já está cheio");
        }
    }

    public void lancarChamas(Robo alvo) {
        if (numCombustivel > 0) {
            numCombustivel -= 5;

            System.out.println(nome + " ateou fogo em " + alvo.nome);

            int dano = (int) (0.2 * alvo.vida);
            alvo.receberDano(dano);

            // retirar do mapa caso vida == 0
        } else {
            System.out.println(nome + " nao pode ater fogo. é necessário reabastecer o tanque de combustivel");
        }
    }
}
