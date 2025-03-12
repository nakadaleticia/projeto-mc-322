public class Ambiente {
    int largura;
    int altura;

    public Ambiente(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public boolean dentroDosLimites(int x, int y) {
        return (0 <= x && x <= largura && 0 <= y && y <= altura);
    }
}
