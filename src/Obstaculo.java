public class Obstaculo {
    final int posicaoX1, posicaoX2, posicaoY1, posicaoY2;
    final int altura;
    TipoObstaculo tipo;

    public Obstaculo(int posicaoX1, int posicaoX2, int posicaoY1, int posicaoY2, int altura, TipoObstaculo tipo) {
        this.posicaoX1 = posicaoX1;
        this.posicaoX2 = posicaoX2;
        this.posicaoY1 = posicaoY1;
        this.posicaoY2 = posicaoY2;
        this.altura = altura;
        this.tipo = tipo;
    }

    public int getPosicaoX1() {
        return posicaoX1;
    }

    public int getPosicaoX2() {
        return posicaoX2;
    }

    public int getPosicaoY1() {
        return posicaoY1;
    }

    public int getPosicaoY2() {
        return posicaoY2;
    }

    public int getAltura() {
        return altura;
    }

    public TipoObstaculo getTipo() {
        return tipo;
    }
}

