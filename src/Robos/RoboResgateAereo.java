package Robos;/*
roboResgateAereo: responsável por realizar resgates de vítimas.
cada robô pode carregar até 5 vítimas.
cada robô tem um modo emergência, que eleva o robô até sua altitude máxima (evacuação e resgate).
*/
import AmbienteP.Ambiente;
import Interfaces.*;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;

public class RoboResgateAereo extends RoboAereo implements Comunicavel, Sensoreavel, Resgatador, Autonomo {
    int capacidadeVitimas;
    boolean modoEmergencia;

    public RoboResgateAereo(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, altitudeMaxima, ambiente);
        this.capacidadeVitimas = 5;
        this.modoEmergencia = false;
    }

    public void ativarModoEmergencia() {
        if (!modoEmergencia) {
            modoEmergencia = true;
            int alturaParaSubir = altitudeMaxima - posicaoZ;
            if (alturaParaSubir > 0) {
                System.out.println("modo emergência ativado. " + nome + " está subindo...");
                subir(alturaParaSubir);
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
                mover(deltaX, deltaY, 0, -1);
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

        mover(deltaX, deltaY, 0, -1);
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
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
    }

    // Implementação da interface Interfaces.Autonomo
    @Override
    public void executarTarefa() {
        System.out.println(nome + " executando tarefa automática: iniciando resgate.");
        resgatar();
    }
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException, ErroComunicacaoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não pode enviar mensagem.");
        }
        if (destinatario == null) {
            throw new ErroComunicacaoException("Destinatário inválido.");
        }
        destinatario.receberMensagem("De " + nome + ": " + mensagem);
    }

    @Override
    public void receberMensagem(String mensagem) throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não pode receber mensagens.");
        }
        System.out.println("[" + nome + "] recebeu mensagem: " + mensagem);
    }
}
