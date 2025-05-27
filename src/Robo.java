
import java.util.ArrayList;

public abstract class Robo implements Entidade{
    String nome;
    String direcao; // (N, S, L, O)
    int vida;
    int posicaoX, posicaoY, posicaoZ;
    protected ArrayList<Sensor> sensores; // sensores do robo
    private final Ambiente ambiente; // usado apenas para mover e remoção (apenas por agora)
    Estado estado;

    public Robo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int posicaoZ, Ambiente ambiente) {
        this.nome = nome;
        this.direcao = direcao;
        this.vida = vida;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
        this.ambiente = ambiente;
        this.sensores = new ArrayList<>(); // inicializa sensores
        this.estado = Estado.Desligado; //valor padrão

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
                ambiente.removerRobo(this);
            }
        }
    }

    public void mover(int deltaX, int deltaY, int deltaZ, int tempo) throws RoboDesligadoException{
        if (this.estado == Estado.Desligado) {
            throw new RoboDesligadoException("O robô '" + this.nome + "' está desligado e não pode se mover.");
        }
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
    @Override
    public int getX(){ return posicaoX;}

    @Override
    public int getY(){return posicaoY;}

    @Override
    public int getZ(){return posicaoZ;}

    @Override
    public TipoEntidade getTipo() {return TipoEntidade.ROBO;}

    @Override
    public String getDescricao(){
        String tipoEspecificoRobo = this.getClass().getSimpleName();
        // Monta a string de descrição
        return "Tipo de Robô: " + tipoEspecificoRobo +
                ", Nome: '" + this.nome +
                "', Símbolo no Mapa: R'" + //
                "', Posição: (" + this.posicaoX + "," + this.posicaoY + "," + this.posicaoZ + ")" +
                ", Vida: " + this.vida +
                ", Direção: " + this.direcao +
                ", Estado: " + this.estado;
    };

    @Override
    public char getRepresentacao(){return 'R';};

    public Estado getEstado(){return estado;}

    public void ligar() {
        this.estado = Estado.Ligado;
        System.out.println("Robô " + this.nome + " ligado.");
    }

    public void desligar() {
        this.estado = Estado.Desligado;
        System.out.println("Robô " + this.nome + " desligado.");
    }

    public abstract void executarTarefa();


    public void exibirPosicao() {
        System.out.println(nome + " está em (" + posicaoX + ", " + posicaoY + ", " + posicaoZ + ")");
    }
}