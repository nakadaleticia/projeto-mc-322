public class Robo {
    String nome;
    int posicaoX;
    int posicaoY;

    public Robo(String nome, int posicaoX, int posicaoY) {
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX;
        posicaoY += deltaY;
    }

    public void exibirPosicao() {
        System.out.println(this.nome +"(" + posicaoX + ";" + posicaoY + ")");
    }
}
