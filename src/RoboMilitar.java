public class RoboMilitar extends RoboTerrestre {
    int municao;

    public RoboMilitar(String nome, int posicaoX, int posicaoY, int velocidade, int velocidadeMaxima, int municao) {
        super(nome, posicaoX, posicaoY, velocidade, velocidadeMaxima);
        this.municao = municao;
    }

    public void atirar() {
        if (municao > 0) {
            municao--;
        } else {
            System.out.println("robô está sem munições");
        }
    }

    public void recarregar(int numMunicoes) {
        municao += numMunicoes;
    }
}
