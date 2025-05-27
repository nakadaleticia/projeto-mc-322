/*
roboTanque: responsável por atirar mísseis em robôs alvos.
cada robô pode carregar até 2 mísseis (mísseis são recarregáveis).
cada robô tem um modo defesa, que reduz o dano recebido em 20%.
o modo defesa pode ser utilizado até 4 vezes (blindagem).
o modo defesa precisa ser reativado após o robô receber dano.
o dano do míssil é fatal.
*/

import java.util.ArrayList;

public class RoboTanque extends RoboTerrestre implements Sensoreavel, Comunicavel, Atacante, Autonomo {
    private int numMissil, blindagem;
    boolean modoDefesa;

    public RoboTanque(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, velocidadeMaxima, ambiente);
        this.numMissil = 2;
        this.blindagem = 4;
        this.modoDefesa = false;
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
        System.out.println(nome + " executando tarefa automática: atacando alvo padrão.");
        atacar(this);
    }
}
