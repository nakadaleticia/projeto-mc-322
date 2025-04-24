import java.util.ArrayList;

public class Robo {
    String nome;
    String direcao; // (N, S, L, O)
    int vida;
    int posicaoX, posicaoY, posicaoZ;

    private Ambiente ambiente;

    protected ArrayList<Sensor> sensores; // sensores do robo

    public Robo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int posicaoZ) {
        this.nome = nome;
        this.direcao = direcao;
        this.vida = vida;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;

        this.sensores = new ArrayList<>(); // inicializa sensores
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    public void usarSensores() {
        for (Sensor s : sensores) {
            s.monitorar(this);
        }
    }

    // receberDano: aplica dano ao robo
    protected void receberDano(int dano) {
        vida -= dano;

        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);

        if (vida <= 0) {
            System.out.println(nome + " foi destruído!");

            if (ambiente != null) {
                ambiente.removerRobo(this);
            }
        }
    }

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

    // identificarObstaculo: verifica se há obstáculo adjacente ao robo (eixo x, eixo y)
    public void identificarObstaculo() {
        for (Obstaculo o : ambiente.getObstaculos()) {
            for (int x = o.getPosicaoX1(); x <= o.getPosicaoX2(); x++) {
                for (int y = o.getPosicaoY1(); y <= o.getPosicaoY2(); y++) {
                    int distX = Math.abs(x - posicaoX);
                    int distY = Math.abs(y - posicaoY);

                    boolean adjacente = (distX <= 1 && distY <= 1) && !(distX == 0 && distY == 0);

                    if (adjacente) {
                        System.out.println(nome + " identificou obstáculo do tipo " + o.getTipo() +
                                " em posição próxima (" + x + ", " + y + ").");

                        if (o.getTipo().isBloqueiaPassagem()) {
                            System.out.println("-> Esse obstáculo bloqueia a passagem.");
                        } else {
                            System.out.println("-> Esse obstáculo não bloqueia a passagem.");
                        }

                        return; // parar após o primeiro obstáculo encontrado
                    }
                }
            }
        }

        System.out.println(nome + " não encontrou nenhum obstáculo adjacente.");
    }


    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }
}
