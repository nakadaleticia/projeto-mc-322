import java.util.ArrayList;

public class Ambiente {
    private final int largura, altura, altitude; // (eixo x, eixo y, eixo z)
    private final ArrayList<Entidade> entidades; // lista de entidades no mapa (robôs e obstáculos)
    private final TipoEntidade[][][] mapa; // mapa 3D de ocupação

    public Ambiente(int largura, int altura, int altitude, ArrayList<Robo> robosAtivos) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;

        this.entidades = new ArrayList<>();
        this.mapa = new TipoEntidade[largura + 1][altura + 1][altitude + 1];

        inicializarMapa();
        System.out.println("Ambiente " + largura + "x" + altura + "x" + altitude + " foi criado");
    }

    // inicializa o mapa com VAZIO
    public void inicializarMapa() {
        for (int x = 0; x <= largura; x++) {
            for (int y = 0; y <= altura; y++) {
                for (int z = 0; z <= altitude; z++) {
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    // adiciona uma entidade ao ambiente e atualiza o mapa
    public void adicionarEntidade(Entidade e) {
        entidades.add(e);
        mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        System.out.println(e.getDescricao() + " adicionado em (" + e.getX() + ", " + e.getY() + ", " + e.getZ() + ")");
    }

    // remove uma entidade do ambiente e atualiza o mapa
    public void removerEntidade(Entidade e) {
        if (entidades.remove(e)) {
            mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
            System.out.println(e.getDescricao() + " removido do ambiente.");
        } else {
            System.out.println(e.getDescricao() + " não está no ambiente.");
        }
    }

    // verifica se a posição está dentro dos limites (lança exceção se não estiver)
    public boolean dentroDosLimites(int x, int y, int z) throws ForaDosLimitesException {
        boolean limiteX = (0 <= x && x <= largura);
        boolean limiteY = (0 <= y && y <= altura);
        boolean limiteZ = (0 <= z && z <= altitude);

        if (!(limiteX && limiteY && limiteZ)) {
            throw new ForaDosLimitesException("Posição (" + x + ", " + y + ", " + z + ") está fora dos limites do ambiente.");
        }
        return true;
    }

    // verifica se uma posição está ocupada por outra entidade
    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    // move uma entidade para uma nova posição (lança exceções se necessário)
    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws ColisaoException, ForaDosLimitesException {
        dentroDosLimites(novoX, novoY, novoZ);

        if (estaOcupado(novoX, novoY, novoZ)) {
            throw new ColisaoException("A posição (" + novoX + ", " + novoY + ", " + novoZ + ") já está ocupada.");
        }

        // limpa a posição antiga no mapa
        mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;

        // atualiza a posição na entidade
        if (e instanceof Robo robo) {
            robo.setPosicao(novoX, novoY, novoZ);
        } else if (e instanceof Obstaculo obstaculo) {
            // para o Lab 4, obstáculos não são móveis — deixamos preparado
        }

        // atualiza o mapa na nova posição
        mapa[novoX][novoY][novoZ] = e.getTipo();
        System.out.println(e.getDescricao() + " moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
    }

    // imprime o plano XY do mapa (camada Z = 0)
    public void visualizarAmbiente() {
        System.out.println("Mapa do ambiente (XY, camada Z=0):");
        for (int y = altura; y >= 0; y--) {
            for (int x = 0; x <= largura; x++) {
                char simbolo = '.';
                for (int z = 0; z <= altitude; z++) {
                    if (mapa[x][y][z] != TipoEntidade.VAZIO) {
                        simbolo = mapa[x][y][z].name().charAt(0); // primeira letra do tipo
                        break;
                    }
                }
                System.out.print(simbolo + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }
}
