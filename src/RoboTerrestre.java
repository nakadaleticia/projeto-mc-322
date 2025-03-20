public class RoboTerrestre extends Robo {
    int velocidade;
    int velocidadeMaxima;

    public RoboTerrestre(String nome, int posicaoX, int posicaoY, int velocidade, int velocidadeMaxima) {
        super(nome, posicaoX, posicaoY);
        this.velocidade = velocidade;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY) {
        if (velocidade > velocidadeMaxima) {
            System.out.println("velocidade do robô ultrapassa limite permitido");
        } else {
            super.mover(deltaX, deltaY);
        }
    }
}
