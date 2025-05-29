/*
roboLancaChamas: responsável por lançar chama em robôs alvos (TERRESTRES).
cada robô tem um tanque de combustível de 100L.
o tanque de combustível é recarregável.
o disparo da chama gasta 5L de combustível.
o dano da chama é de 20% da vida do robô alvo.
*/

public class RoboLancaChamas extends RoboTerrestre implements Sensoreavel, Comunicavel, Atacante, Autonomo {
    private int numCombustivel;

    public RoboLancaChamas(String nome, String direcao, int vida, int posicaoX, int posicaoY, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, direcao, vida, posicaoX, posicaoY, velocidadeMaxima, ambiente);
        this.numCombustivel = 100;
    }

    public void recarregarCombustivel() {
        if (numCombustivel < 100) {
            numCombustivel = 100;
            System.out.println(nome + " recarregou o tanque de combustível");
        } else {
            System.out.println("Tanque de combustível já está cheio");
        }
    }

    public void lancarChamas(Robo alvo) {
        if (numCombustivel >= 5) {
            numCombustivel -= 5;
            System.out.println(nome + " ateou fogo em " + alvo.getNome());
            int dano = (int) (0.2 * alvo.getVida());
            alvo.receberDano(dano);
        } else {
            System.out.println(nome + " não pode atear fogo. É necessário reabastecer o tanque de combustível");
        }
    }

    @Override
    public void atacar(Entidade alvo) {
        if (alvo instanceof Robo roboAlvo) {
            lancarChamas(roboAlvo);
        } else {
            System.out.println("Alvo inválido para ataque.");
        }
    }
    @Override
    public void acionarSensores() throws RoboDesligadoException {
        if (!this.estaLigado()) {
            throw new RoboDesligadoException(nome + " está desligado! Não é possível acionar sensores.");
        }
        this.usarSensores();
    }

    // Implementação da interface Autonomo
    @Override
    public void executarTarefa() {
        System.out.println(nome + " executando tarefa automática: lançando chamas no alvo padrão.");
        atacar(this);
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
