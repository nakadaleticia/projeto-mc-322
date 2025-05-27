public class Vazio implements Entidade {


    private final int x;
    private final int y;
    private final int z;

    public Vazio(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
    }

    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }
    @Override
    public int getZ() { return z; }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.VAZIO; // A classe Vazio USA a enumeração para se identificar
    }

    @Override
    public String getDescricao() {
        return "Um espaço vazio na posição (" + x + "," + y + "," + z + ").";
    }

    @Override
    public char getRepresentacao() {
        return '.'; // Ou o caractere que preferir para vazio
    }

}
