package Robos;/*
roboReconhecimento: responsável por realizar um reconhecimento do mapa.
cada robô tem um modo de reconhecimento, que eleva o robô até sua altitude máxima.
*/
import AmbienteP.Ambiente;
import Interfaces.*;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;
import java.util.ArrayList;

public class RoboReconhecimento extends Robo implements Sensoreavel, Comunicavel, Mapeador, Autonomo {
    boolean modoReconhecimento;
    ArrayList<Robo> mapaReconhecimento;
    public int altitudeMaxima;
    private ModuloComunicacao modulo;
    private GerenciadorSensor gerenciadorSensor;
    private ControleMovimentoAereo controleMovimentoAereo;
    public RoboReconhecimento(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente, ModuloComunicacao modulo, ControleMovimentoAereo controleMovimentoAereo, GerenciadorSensor gerenciadorSensor) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, ambiente);
        this.modoReconhecimento = false;
        this.mapaReconhecimento = new ArrayList<>();
        this.modulo = modulo;
        this.gerenciadorSensor = gerenciadorSensor;
        this.controleMovimentoAereo = controleMovimentoAereo;
        this.altitudeMaxima = altitudeMaxima;
    }

    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            modoReconhecimento = true;
            System.out.println("modo reconhecimento ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - posicaoZ, this, ambiente);
        } else {
            System.out.println(nome + " já está em modo reconhecimento");
        }
    }

    @Override
    public void mapearAmbiente() {
        System.out.println(nome + " está mapeando o ambiente.");
        // implementar lógica de mapeamento se desejar
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
        System.out.println(nome + " executando tarefa automática: mapeando o ambiente.");
        mapearAmbiente();
    }
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
