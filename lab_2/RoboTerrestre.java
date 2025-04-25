public class RoboTerrestre extends Robo {
    int velocidadeMaxima;

    public RoboTerrestre(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima) {
        super(nome, direcao, vida, posicaoX, posicaoY);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    private int calcularVelocidade(int deltaX, int deltaY, int tempo) {
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // calcula distância que robô deve percorrer
        double velocidade = distancia / tempo;

        return (int) velocidade; // truncamento para remover parte decimal
    }

    // variável "tempo" foi adicionada para calcular a velocidade do robo; não é possível fazer sobrescrita
    public void mover(int deltaX, int deltaY, int tempo) {
        int velocidade = calcularVelocidade(deltaX, deltaY, tempo);

        if (velocidade > velocidadeMaxima) {
            System.out.println("velocidade de " + nome + " ultrapassa limite permitido");
        } else {
            super.mover(deltaX, deltaY);
            System.out.println(nome + " se moveu com velocidade " + velocidade);
        }
    }
}
