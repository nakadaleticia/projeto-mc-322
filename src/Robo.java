
import java.util.ArrayList;

public class Robo {
    String nome;
    String direcao; // (N, S, L, O)
    int vida;
    int posicaoX, posicaoY, posicaoZ;

    protected ArrayList<Sensor> sensores; // sensores do robo
    private Ambiente ambiente; // usado apenas para mover e remoção (apenas por agora)

    public Robo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int posicaoZ, Ambiente ambiente) {
        this.nome = nome;
        this.direcao = direcao;
        this.vida = vida;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
        this.ambiente = ambiente;

        this.sensores = new ArrayList<>(); // inicializa sensores

        // adiciona sensor de proximidade padrão
        SensorProximidade sensorPadrao = new SensorProximidade(3, ambiente);
        this.adicionarSensor(sensorPadrao);
    }

    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
    }

    public Ambiente getAmbiente() {
        return ambiente;
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

    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }
}