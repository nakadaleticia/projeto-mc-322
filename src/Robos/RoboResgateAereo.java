package Robos;/*
roboResgateAereo: responsável por realizar resgates de vítimas.
cada robô pode carregar até 5 vítimas.
cada robô tem um modo emergência, que eleva o robô até sua altitude máxima (evacuação e resgate).
*/
import AmbienteP.Ambiente;
import Interfaces.*;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;

public class RoboResgateAereo extends AgenteInteligente implements Comunicavel, Sensoreavel, Resgatador, Autonomo {
    int capacidadeVitimas;
    boolean modoEmergencia;
    public int altitudeMaxima;
    private ModuloComunicacao modulo;
    private GerenciadorSensor gerenciadorSensor;
    private ControleMovimentoAereo controleMovimentoAereo;

    public RoboResgateAereo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente, ModuloComunicacao modulo, ControleMovimentoAereo controleMovimentoAereo, GerenciadorSensor gerenciadorSensor) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, ambiente);
        this.capacidadeVitimas = 5;
        this.modoEmergencia = false;
        this.modulo = modulo;
        this.gerenciadorSensor = gerenciadorSensor;
        this.controleMovimentoAereo = controleMovimentoAereo;
        this.altitudeMaxima = altitudeMaxima;
    }

    public void ativarModoEmergencia() {
        if (!modoEmergencia) {
            modoEmergencia = true;
            int alturaParaSubir = altitudeMaxima - posicaoZ;
            if (alturaParaSubir > 0) {
                System.out.println("modo emergência ativado. " + nome + " está subindo...");
                subir(alturaParaSubir, this, ambiente);
            } else {
                System.out.println(nome + " já está na altitude máxima.");
            }
        } else {
            System.out.println(nome + " já está em modo resgate");
        }
    }

    public void resgatarVitima(int posicaoVitimaX, int posicaoVitimaY) {
        int deltaX = posicaoVitimaX - posicaoX;
        int deltaY = posicaoVitimaY - posicaoY;

        if (modoEmergencia) {
            if (capacidadeVitimas > 0) {
                mover(deltaX, deltaY, 0, -1, ambiente, this);
                System.out.println(nome + " resgatou uma vítima");
                capacidadeVitimas--;
            } else {
                System.out.println(nome + " não pode resgatar novas vítimas. é necessário evacuar");
            }
        } else {
            System.out.println(nome + " não pode realizar resgates. ative o modo emergência");
        }
    }

    public void evacuacaoDeVitimas(int posicaoEvacuacaoX, int posicaoEvacuacaoY) {
        int deltaX = posicaoEvacuacaoX - posicaoX;
        int deltaY = posicaoEvacuacaoY - posicaoY;

        mover(deltaX, deltaY, 0, -1, ambiente, this);
        System.out.println(nome + " levou suas vítimas para (" + posicaoEvacuacaoX + ", " + posicaoEvacuacaoY + ", 0)");
        capacidadeVitimas = 5;
    }

    @Override
    public void resgatar() {
        System.out.println(nome + " está realizando uma operação de resgate.");
        // implementar lógica automática se desejar
    }
    @Override
    public void acionarSensores() throws RoboDesligadoException {
        gerenciadorSensor.usarSensores(this);
    }

    // Implementação da interface Interfaces.Autonomo
    @Override
    public void executarTarefa() {
        System.out.println(nome + " executando tarefa automática: iniciando resgate.");
        resgatar();
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
    @Override
    public void executarMissao(Ambiente ambiente) {
        if (missao != null) {
            System.out.println(nome + " está executando a missão:");
            missao.executar(this, ambiente);
        } else {
            System.out.println(nome + " não tem missão definida.");
        }
    }

    @Override
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo, Ambiente ambiente, Robo r){
        controleMovimentoAereo.mover(deltaX, deltaY, deltaZ, tempo, ambiente, r);
    }
    public void subir(int metros, Robo r, Ambiente ambiente){
        controleMovimentoAereo.subir(metros, r, ambiente);
    }
    public void descer(int metros, Robo r, Ambiente ambiente){
        controleMovimentoAereo.descer(metros, r, ambiente);
    }
}
