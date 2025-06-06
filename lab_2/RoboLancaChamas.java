/*
esta classe de robô é responsável por lançar chamas sobre os inimigos

funções
- lançar chamas
 */

public class RoboLancaChamas extends RoboTerrestre {
    int combustivel;

    public RoboLancaChamas(String nome, String direcao, int posicaoX, int posicaoY, int velocidadeMaxima, int combustivel) {
        super(nome, direcao, posicaoX, posicaoY, velocidadeMaxima);
        this.combustivel = combustivel;
    }

    public void lancarChamas() {
        if (combustivel < 0) {
            System.out.println(nome + " não possui combustível disponível");
        } else {
            combustivel--;
            System.out.println(nome + " lançou chamas");
        }
    }

    public void recarregarCombustivel(int numCombustivel) {
        combustivel += numCombustivel;
        System.out.println(nome + " recarregou combustível. combustível: " + combustivel);
    }
}
