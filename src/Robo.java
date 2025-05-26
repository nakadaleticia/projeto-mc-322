import java.util.ArrayList;

public class Robo implements Entidade {
    String nome; // nome do robô
    String direcao; // (N, S, L, O)
    int vida;
    int posicaoX, posicaoY, posicaoZ;
    protected boolean ligado = true; // estado do robô: ligado ou desligado

    protected ArrayList<Sensor> sensores; // sensores do robô
    private Ambiente ambiente; // ambiente em que o robô está

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

    // adiciona um sensor ao robô
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
        System.out.println("Sensor adicionado com sucesso em " + this.nome);
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    // usa todos os sensores do robô
    public void usarSensores() {
        for (Sensor s : sensores) {
            s.monitorar(this);
        }
    }

    // receberDano: aplica dano ao robô e verifica se foi destruído
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

    // move o robô no ambiente (se a posição for válida e não ocupada)
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

    // exibe a posição atual do robô
    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }

    /* métodos obrigatórios da interface Entidade */

    @Override
    public int getX() {
        return posicaoX;
    }

    @Override
    public int getY() {
        return posicaoY;
    }

    @Override
    public int getZ() {
        return posicaoZ;
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.ROBO;
    }

    @Override
    public String getDescricao() {
        return "Robô do tipo " + this.getClass().getSimpleName() + " chamado " + nome;
    }

    @Override
    public char representacao() {
        return 'R'; // pode alterar para diferenciar tipos de robôs
    }

    // verifica se o robô está ligado
    public boolean estaLigado() {
        return ligado;
    }

    // liga o robô
    public void ligar() {
        ligado = true;
        System.out.println(nome + " foi ligado.");
    }

    // desliga o robô
    public void desligar() {
        ligado = false;
        System.out.println(nome + " foi desligado.");
    }
}
