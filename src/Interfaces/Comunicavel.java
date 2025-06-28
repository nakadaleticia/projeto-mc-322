package Interfaces;
import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;
public interface Comunicavel {
    void enviarMensagemPara(Comunicavel destinatario, String mensagem) throws RoboDesligadoException, ErroComunicacaoException;
    void receberMensagem(String mensagem) throws RoboDesligadoException;
}
