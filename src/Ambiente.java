import java.util.ArrayList;

public class Ambiente {
    private int largura, altura, altitude; // dimensões do ambiente
    private ArrayList<Entidade> entidades; // lista de entidades (robôs e obstáculos)
    private TipoEntidade[][][] mapa; // mapa 3D que registra tipo da entidade em cada posição

    public Ambiente(int largura, int altura, int altitude, ArrayList<Entidade> entidades) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.entidades = (entidades != null) ? entidades : new ArrayList<>();
        this.mapa = new TipoEntidade[largura + 1][altura + 1][altitude + 1];
        inicializarMapa();
        System.out.println("Ambiente " + largura + "x" + altura + "x" + altitude + " foi criado");
    }

    private void inicializarMapa() {
        for (int x = 0; x <= largura; x++) {
            for (int y = 0; y <= altura; y++) {
                for (int z = 0; z <= altitude; z++) {
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    public void adicionarEntidade(Entidade e) {
        entidades.add(e);
        mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        System.out.println(e.getDescricao() + " adicionado em (" + e.getX() + ", " + e.getY() + ", " + e.getZ() + ")");
    }

    public void removerEntidade(Entidade e) {
        if (entidades.remove(e)) {
            mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
            System.out.println(e.getDescricao() + " removido do ambiente.");
        } else {
            System.out.println(e.getDescricao() + " não está no ambiente.");
        }
    }

    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws ForaDosLimitesException, ColisaoException {
        // Verifica limites do ambiente
        if (novoX < 0 || novoX > largura || novoY < 0 || novoY > altura || novoZ < 0 || novoZ > altitude) {
            throw new ForaDosLimitesException("Posição (" + novoX + ", " + novoY + ", " + novoZ + ") está fora dos limites do ambiente.");
        }

        // Verifica se a posição está ocupada por outra entidade
        if (estaOcupado(novoX, novoY, novoZ)) {
            throw new ColisaoException("Posição (" + novoX + ", " + novoY + ", " + novoZ + ") já está ocupada por outra entidade.");
        }

        // Atualiza mapa removendo entidade da posição antiga
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;

        // Atualiza posição da entidade
        if (e instanceof Robo robo) {
            robo.setPosicao(novoX, novoY, novoZ);
        }

        // Atualiza mapa para nova posição
        mapa[novoX][novoY][novoZ] = e.getTipo();

        System.out.println(e.getDescricao() + " moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
    }

    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }

    public void visualizarAmbiente() {
        System.out.println("Mapa do ambiente (XY, camada Z=0):");
        for (int y = altura; y >= 0; y--) {
            for (int x = 0; x <= largura; x++) {
                char simbolo = '.';
                for (int z = 0; z <= altitude; z++) {
                    if (mapa[x][y][z] != TipoEntidade.VAZIO) {
                        simbolo = mapa[x][y][z].name().charAt(0);
                        break;
                    }
                }
                System.out.print(simbolo + " ");
            }
            System.out.println();
        }
    }
}
