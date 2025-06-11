package ObstaculoP;

import Interfaces.Entidade;
import enums.TipoEntidade;
import enums.TipoObstaculo;

public class Obstaculo implements Entidade {
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

    @Override
    public int getX(){ return (posicaoX1+posicaoX2)/2;}

    @Override
    public int getY(){return (posicaoY1+posicaoY2)/2;}

    @Override
    public int getZ(){return altura;}

    @Override
    public TipoEntidade getTipo() {return TipoEntidade.OBSTACULO;}

    @Override
    public char getRepresentacao(){return 'O';}

    @Override
    public String getDescricao(){
        return "Tipo de obstáculo:" + tipo +
                "Caracter representante: O" +
                "Posição: " + "(" + ((this.posicaoX1+this.posicaoX2)/2) +  ((this.posicaoY1+this.posicaoY2)/2) + ")," ;
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

    public TipoObstaculo getTipoObstaculo() {
        return tipo;
    }
}

