package Robos;

import Comunicacao.CentralComunicacao;
import Interfaces.Comunicavel;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;

public class ModuloComunicacao {
    private final CentralComunicacao central;
    private final Robo roboDono;

    public ModuloComunicacao(CentralComunicacao central, Robo roboDono) {
        this.central = central;
        this.roboDono = roboDono;

    }
    public void enviarMensagem(Comunicavel destinatario, String mensagem) throws RoboDesligadoException, ErroComunicacaoException {
        if (!roboDono.estaLigado()) {
            throw new RoboDesligadoException(roboDono.nome + " está desligado! Não pode enviar mensagem.");
        }
        if (destinatario == null) {
            throw new ErroComunicacaoException("Destinatário inválido.");
        }

        destinatario.receberMensagem("De " + roboDono.nome + ": " + mensagem);
        central.registrarMensagem(roboDono.nome, mensagem);
    }

}
