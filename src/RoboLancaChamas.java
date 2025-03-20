public class RoboLancaChamas extends RoboTerrestre {
    int combustivel;

    public RoboLancaChamas(String nome, int posicaoX, int posicaoY, int velocidade, int velocidadeMaxima, int combustivel) {
        super(nome, posicaoX, posicaoY, velocidade, velocidadeMaxima);
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
        System.out.println(nome + " recarregou combustível");
    }
}
