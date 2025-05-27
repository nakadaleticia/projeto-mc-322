import java.util.ArrayList;

public class Robo implements Entidade {
    protected String nome; // modificado para protected
    protected String direcao; // (N, S, L, O)
    protected int vida;
    protected int posicaoX, posicaoY, posicaoZ;
    protected boolean ligado = true;

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
        System.out.println("Sensor adicionado com sucesso em " + this.nome);
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
                ambiente.removerEntidade(this); // ajuste importante
            }
        }
    }

    // mover o robô (usando o método do Ambiente)
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;
        int novoZ = posicaoZ + deltaZ;

        try {
            ambiente.moverEntidade(this, novoX, novoY, novoZ);
            setPosicao(novoX, novoY, novoZ);
        } catch (Exception e) {
            System.out.println("Erro ao mover: " + e.getMessage());
        }
    }

    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }

    /* Métodos obrigatórios da interface Entidade */

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
        return 'R';
    }

    // getters auxiliares
    public String getNome() {
        return nome;
    }

    public String getDirecao() {
        return direcao;
    }

    public int getVida() {
        return vida;
    }

    public boolean estaLigado() {
        return ligado;
    }

    public void ligar() {
        ligado = true;
        System.out.println(nome + " foi ligado.");
    }

    public void desligar() {
        ligado = false;
        System.out.println(nome + " foi desligado.");
    }

    // atualizar posição (usado pelo Ambiente)
    public void setPosicao(int x, int y, int z) {
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;
    }
}
