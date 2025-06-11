package enums;

public enum TipoObstaculo {
    VITIMA(2, true),
    PAREDE(4, true),
    BURACO(0, true),
    OUTRO(-1, false); // altura -1 representa valor variável

    private final int alturaPadrao;
    private final boolean bloqueiaPassagem;

    TipoObstaculo(int alturaPadrao, boolean bloqueiaPassagem) {
        this.alturaPadrao = alturaPadrao;
        this.bloqueiaPassagem = bloqueiaPassagem;
    }

    public int getAlturaPadrao() {
        return alturaPadrao;
    }

    public boolean isBloqueiaPassagem() {
        return bloqueiaPassagem;
    }
}
