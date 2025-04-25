import java.util.ArrayList;

public class Ambiente {
    int largura, altura, altitude; // (eixo x, eixo y, eixo z)
    private ArrayList<Robo> robosAtivos; // lista de robos ativos no mapa
    private ArrayList<Obstaculo> obstaculos; // lista de obstaculos no mapa

    public Ambiente(int largura, int altura, int altitude, ArrayList<Robo> robosAtivos) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.robosAtivos = new ArrayList<>(); // inicializa robosAtivos na memoria
        this.obstaculos = new ArrayList<>(); // inicializa obstaculos na memoria

        System.out.println("ambiente " + largura + "x" + altura + "x" + altitude + " foi criado");
    }

    public void adicionarRobo(Robo r) {
        robosAtivos.add(r);
        System.out.println(r.nome + " adiconado em (" + r.posicaoX + ", " + r.posicaoY + ", " + r.posicaoZ + ")");
    }

    public void removerRobo(Robo r) {
        if (robosAtivos.remove(r)) {
            System.out.println(r.nome + " removido do ambiente.");
        } else {
            System.out.println(r.nome + " não está no ambiente.");
        }
    }

    public void adicionarObstaculo(Obstaculo o) {
        obstaculos.add(o);
        System.out.println("obstáculo do tipo " + o.getTipo() + " adicionado ao ambiente.");
    }

    public ArrayList<Robo> getRobosAtivos() {
        return robosAtivos;
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public boolean dentroDosLimites(int x, int y, int z) {
        boolean limiteX = (0 <= x && x <= largura); // limite no eixo x
        boolean limiteY = (0 <= y && y <= altura); // limite no eixo y
        boolean limiteZ = (0 <= z && z <= altitude); // limite no eixo z

        return (limiteX && limiteY && limiteZ);
    }

    public boolean posicaoOcupada(int x, int y, int z, Robo solicitante) {
        for (Robo r : robosAtivos) {
            boolean mesmoX = (solicitante.posicaoX == r.posicaoX);
            boolean mesmoY = (solicitante.posicaoY == r.posicaoY);
            boolean mesmoZ = (solicitante.posicaoZ == r.posicaoZ);

            if (r != solicitante) {
                return (mesmoX && mesmoY && mesmoZ);
            }
        }

        return false;
    }
}
