/*
roboTanque: responsável por atirar misseis em robos alvos.
cada robo pode carregar ate 2 misseis (misseis sao recarregaveis).
cada robo tem um modo defesa, que reduz o dano recebido em 20%.
o modo defesa pode ser utilizado ate 4 vezes (blindagem).
o modo defesa precisa ser reativado apos o robo receber dano.
o dano do missil é fatal.
*/

import java.util.ArrayList;

public class RoboTanque extends RoboTerrestre implements Sensoreavel, Comunicavel {
    private int numMissil, blindagem;
    boolean modoDefesa;

    public RoboTanque(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, velocidadeMaxima, ambiente);
        this.numMissil = 2;
        this.blindagem = 4;
        this.modoDefesa = false;
    }

    // ativa o modo defesa (reduz dano e consome blindagem)
    public void ativarModoDefesa() {
        if (blindagem > 0) {
            modoDefesa = true;
            blindagem--;
            System.out.println(nome + " ativou o modo defesa. blindagem restante: " + blindagem);
        } else {
            System.out.println(nome + " nao pode ativar o modo defesa. nenhuma blindagem restante");
        }
    }

    // sobreescreve o receberDano para aplicar o modo defesa
    @Override
    protected void receberDano(int dano) {
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
            getAmbiente().removerRobo(this);
            System.out.println(nome + " foi destruído!");
        }
    }

    // recarrega os misseis (máximo de 2)
    public void recarregarMissil() {
        if (numMissil < 2) {
            numMissil = 2;
            System.out.println(nome + " recarregou seus mísseis");
        } else {
            System.out.println(nome + " só pode carregar 2 mísseis simultaneamente");
        }
    }

    // dispara um míssil em outro robô (causa dano fatal)
    public void dispararMissil(Robo alvo) {
        if (numMissil > 0) {
            numMissil--;
            System.out.println(nome + " disparou míssil em " + alvo.nome);
            alvo.receberDano(alvo.vida); // dano fatal
        } else {
            System.out.println(nome + " nao possui mísseis restantes. é necessário recarregar");
        }
    }

    /* implementação da interface Sensoreavel */
    public void acionarSensores() throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
    }

    /* implementação da interface Comunicavel */
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
