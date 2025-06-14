package Robos;/*
roboTanque: responsável por atirar mísseis em robôs alvos.
cada robô pode carregar até 2 mísseis (mísseis são recarregáveis).
cada robô tem um modo defesa, que reduz o dano recebido em 20%.
o modo defesa pode ser utilizado até 4 vezes (blindagem).
o modo defesa precisa ser reativado após o robô receber dano.
o dano do míssil é fatal.
*/

import AmbienteP.Ambiente;
import Interfaces.*;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;

public class RoboTanque extends AgenteInteligente implements Sensoreavel, Comunicavel, Atacante, Autonomo {
    private int numMissil, blindagem;
    boolean modoDefesa;
    int velocidadeMaxima;
    private ModuloComunicacao modulo;
    private GerenciadorSensor gerenciadorSensor;
    private ControleMovimentoTerrestre controleMovimentoTerrestre;

    public RoboTanque(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente, ModuloComunicacao modulo, ControleMovimentoTerrestre controleMovimentoTerrestre, GerenciadorSensor gerenciadorSensor) {
        super(nome, direcao, vida, posicaoX, posicaoY, 0, ambiente);
        this.numMissil = 2;
        this.blindagem = 4;
        this.modoDefesa = false;
        this.modulo = modulo;
        this.gerenciadorSensor = gerenciadorSensor;
        this.controleMovimentoTerrestre = controleMovimentoTerrestre;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public void ativarModoDefesa() {
        if (blindagem > 0) {
            modoDefesa = true;
            blindagem--;
            System.out.println(nome + " ativou o modo defesa. blindagem restante: " + blindagem);
        } else {
            System.out.println(nome + " não pode ativar o modo defesa. nenhuma blindagem restante");
        }
    }

    @Override
    public void receberDano(int dano) {
        if (modoDefesa) {
            int danoEfetivo = (int) (0.8 * dano);
            vida -= danoEfetivo;
            modoDefesa = false;
            System.out.println(nome + " recebeu " + danoEfetivo + " de dano (modo defesa). Vida restante: " + vida);
        } else {
            vida -= dano;
            System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);
        }

        if (vida <= 0 && getAmbiente() != null) {
            getAmbiente().removerEntidade(this);
            System.out.println(nome + " foi destruído!");
        }
    }

    public void recarregarMissil() {
        if (numMissil < 2) {
            numMissil = 2;
            System.out.println(nome + " recarregou seus mísseis");
        } else {
            System.out.println(nome + " só pode carregar 2 mísseis simultaneamente");
        }
    }

    public void dispararMissil(Robo alvo) {
        if (numMissil > 0) {
            numMissil--;
            System.out.println(nome + " disparou míssil em " + alvo.getNome());
            alvo.receberDano(alvo.getVida());
        } else {
            System.out.println(nome + " não possui mísseis restantes. é necessário recarregar");
        }
    }

    @Override
    public void atacar(Entidade alvo) {
        if (alvo instanceof Robo roboAlvo) {
            dispararMissil(roboAlvo);
        } else {
            System.out.println("Alvo inválido para ataque.");
        }
    }
    @Override
    public void acionarSensores() throws RoboDesligadoException {
        gerenciadorSensor.usarSensores(this);
    }

    @Override
    public void enviarMensagemPara(Comunicavel destinatario, String mensagem) throws RoboDesligadoException, ErroComunicacaoException {
        modulo.enviarMensagem(destinatario, mensagem);

    }

    @Override
    public void receberMensagem(String mensagem) throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não pode receber mensagens.");
        }
        System.out.println("[" + nome + "] recebeu mensagem: " + mensagem);
    }

    // Implementação da interface Interfaces.Autonomo
    @Override
    public void executarTarefa() {
        System.out.println(nome + " executando tarefa automática: atacando alvo padrão.");
        atacar(this);
    }
    @Override
    public void executarMissao(Ambiente ambiente){}
    @Override
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo, Ambiente ambiente, Robo r){
        controleMovimentoTerrestre.mover(deltaX, deltaY, deltaZ, tempo, ambiente, r);
    }
}
