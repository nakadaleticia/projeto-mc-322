public class Robo {
    String nome;
    String direcao;
    int posicaoX;
    int posicaoY;

    public Robo(String nome, String direcao, int posicaoX, int posicaoY) {
        this.nome = nome;
        this.direcao = direcao;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public void identificarObstaculo(Ambiente ambiente) {
        boolean encontrou = false;
        System.out.println(nome + " está verificando obstáculos...");

        for (Robo outro : ambiente.encontrarRobosAtivos()) {
            encontrou = false;
            if (outro != this) {
                int dx = Math.abs(outro.posicaoX - this.posicaoX); // distância ao obstáculo em x
                int dy = Math.abs(outro.posicaoY - this.posicaoY); // distâcnia ao obstáculo em y

                if ((dx == 1 && dy <= 1) || (dy == 1 && dx <= 1)) { // se estiver imediatamente adjacente ao robô
                    System.out.println("obstáculo detectado: " + outro.nome + " em (" + outro.posicaoX + ", " + outro.posicaoY + ")");
                    encontrou = true;
                }
            }
        }

    }

    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX;
        posicaoY += deltaY;
        System.out.println(nome + "moveu para (" + posicaoX + ", " + posicaoY + ")");
    }

    public void exibirPosicao() {
        System.out.println(this.nome +"(" + posicaoX + ";" + posicaoY + ")");
    }


}
