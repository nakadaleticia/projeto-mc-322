public class RoboTerrestre extends Robo {
    int velocidadeMaxima;

    public RoboTerrestre(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, 0, ambiente);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public int calcularVelocidade(int deltaX, int deltaY, int tempo) {
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // calcula distância que robô deve percorrer
        double velocidade = distancia / tempo;

        return (int) velocidade; // truncamento para remover parte decimal
    }

    @Override
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo) {
        int velocidade = calcularVelocidade(deltaX, deltaY, tempo);
        if (velocidade > velocidadeMaxima) {
            System.out.println(nome + " não pode se mover. limite de velocidade excedido");
            return;
        }
        super.mover(deltaX, deltaY, deltaZ, tempo); // aqui ele trata limites e colisão!
    }

    @Override
    public void executarTarefa() {

    }
}
