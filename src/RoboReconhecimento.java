/*
roboReconhecimento: responsável por realizar um reconhecimento do mapa.
cada robo tem um modo de reconhecimento, que eleva o robo ate sua altitude maxima.
*/

import java.util.ArrayList;

public class RoboReconhecimento extends RoboAereo implements Sensoreavel, Comunicavel {
    boolean modoReconhecimento;
    ArrayList<Robo> mapaReconhecimento;

    public RoboReconhecimento(String nome, String direcao, int vida, int posicaoX, int posicaoY, int altitude, int altitudeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, altitude, altitudeMaxima, ambiente);
        this.modoReconhecimento = false;
        this.mapaReconhecimento = new ArrayList<>();
    }

    // ativa o modo de reconhecimento e sobe para a altitude máxima
    public void ativarModoReconhecimento() {
        if (!modoReconhecimento) {
            modoReconhecimento = true;
            System.out.println("modo reconhecimento ativado. " + nome + " está subindo...");
            subir(altitudeMaxima - posicaoZ);
        } else {
            System.out.println(nome + " já está em modo reconhecimento");
        }
    }

    // implementa a interface Sensoreavel
    public void acionarSensores() throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
    }

    // implementa a interface Comunicavel
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
