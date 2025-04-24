public class Robo {
    String nome;
    String direcao; // (N, S, L, O)
    int vida;
    int posicaoX, posicaoY, posicaoZ;

    private Ambiente ambiente;

    public Robo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int posicaoZ) {
        this.nome = nome;
        this.direcao = direcao;
        this.vida = vida;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    // receberDano (criar tipoDeDano)

    public void mover(int deltaX, int deltaY, int deltaZ, int tempo) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;
        int novoZ = posicaoZ + deltaZ;

        if (ambiente.posicaoOcupada(novoX, novoY, novoZ, this)) {
            System.out.println(nome + " nao pode se mover para uma posicao ocupada");
        } else if (!ambiente.dentroDosLimites(novoX, novoY, novoZ)) {
            System.out.println(nome + " nao pode se mover para fora dos limites");
            return;
        }

        posicaoX = novoX;
        posicaoY = novoY;
        posicaoZ = novoZ;

        System.out.println(nome + " se moveu");
    }

    // public void identificarObstaculo()

    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }
}
