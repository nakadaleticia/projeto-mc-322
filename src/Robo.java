import java.util.ArrayList;

public abstract class Robo implements Entidade {
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
    public String getNome(){return nome;}

    public int getVida(){return vida;}

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

    // receberDano: aplica dano ao robo
    protected void receberDano(int dano) {
        vida -= dano;

        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);

        if (vida <= 0) {
            System.out.println(nome + " foi destruído!");

            if (ambiente != null) {
                ambiente.removerEntidade(this);
            }
        }
    }

    // mover o robô (usando o metodo do Ambiente)
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;
        int novoZ = posicaoZ + deltaZ;

        try {
            ambiente.moverEntidade(this, novoX, novoY, novoZ);
            setPosicao(novoX, novoY, novoZ);
            System.out.println(nome + " se moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
        } catch (ForaDosLimitesException e) {
            System.out.println(nome + " não pode se mover: " + e.getMessage());
        } catch (ColisaoException e) {
            System.out.println(nome + " não pode se mover: " + e.getMessage());
        }

        posicaoX = novoX;
        posicaoY = novoY;
        posicaoZ = novoZ;

        System.out.println(nome + " se moveu");
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
        String tipoEspecificoRobo = this.getClass().getSimpleName();
        // Monta a 'string' de descrição
        return "Tipo de Robô: " + tipoEspecificoRobo +
                ", Nome: '" + this.nome +
                "', Símbolo no Mapa: R'" + //
                "', Posição: (" + this.posicaoX + "," + this.posicaoY + "," + this.posicaoZ + ")" +
                ", Vida: " + this.vida +
                ", Direção: " + this.direcao +
                ", Estado: " + (ligado ? "sim":"não") ;
    }

    ;

    @Override
    public char getRepresentacao() {
        return 'R';
    }

    ;

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

    // atualizar posição (usado pelo Ambiente)
    public void setPosicao(int x, int y, int z) {
        this.posicaoX = x;
        this.posicaoY = y;
        this.posicaoZ = z;
    }

    public abstract void executarTarefa();

}
