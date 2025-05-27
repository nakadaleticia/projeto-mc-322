// Classe Obstaculo: representa um obstáculo fixo no ambiente
// Cada obstáculo ocupa um retângulo no plano XY e tem uma altura Z fixa
// Implementa Entidade para ser gerenciado pelo ambiente

public class Obstaculo implements Entidade {
    final int posicaoX1, posicaoX2, posicaoY1, posicaoY2; // limites do obstáculo no plano XY
    final int altura; // altura Z do obstáculo
    final TipoObstaculo tipo; // tipo específico do obstáculo (VITIMA, PAREDE, etc.)

    // Construtor: define os limites e o tipo do obstáculo
    public Obstaculo(int posicaoX1, int posicaoX2, int posicaoY1, int posicaoY2, int altura, TipoObstaculo tipo) {
        this.posicaoX1 = posicaoX1;
        this.posicaoX2 = posicaoX2;
        this.posicaoY1 = posicaoY1;
        this.posicaoY2 = posicaoY2;
        this.altura = altura;
        this.tipo = tipo;
    }

    /* Métodos obrigatórios da interface Entidade */

    @Override
    public int getX() {
        return posicaoX1; // ponto de referência para o mapa
    }

    @Override
    public int getY() {
        return posicaoY1;
    }

    @Override
    public int getZ() {
        return altura;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.OBSTACULO; // tipo genérico usado pelo mapa
    }

    @Override
    public String getDescricao() {
        return "Obstáculo do tipo " + tipo + " ocupando área de (" +
                posicaoX1 + "," + posicaoY1 + ") a (" + posicaoX2 + "," + posicaoY2 + ")";
    }

    @Override
    public char representacao() {
        return 'O'; // símbolo no mapa
    }

    /* Getters específicos do obstáculo */

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
