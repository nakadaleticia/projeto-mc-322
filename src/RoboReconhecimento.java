/*
roboReconhecimento: responsável por realizar um reconhecimento do mapa.
cada robô tem um modo de reconhecimento, que eleva o robô até sua altitude máxima.
*/

import java.util.ArrayList;

public class RoboReconhecimento extends RoboAereo implements Sensoreavel, Comunicavel, Mapeador, Autonomo {
    boolean modoReconhecimento;
    ArrayList<Robo> mapaReconhecimento;

    public RoboReconhecimento(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, altitudeMaxima, ambiente);
        this.modoReconhecimento = false;
        this.mapaReconhecimento = new ArrayList<>();
    }

    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            modoReconhecimento = true;
            System.out.println("modo reconhecimento ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - posicaoZ);
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
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
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

    // Implementação da interface Autonomo
    @Override
    public void executarTarefa() {
        System.out.println(nome + " executando tarefa automática: mapeando o ambiente.");
        mapearAmbiente();
    }
}
