package Robos;
import AmbienteP.Ambiente;
import Interfaces.Missao;
public abstract class AgenteInteligente extends Robo{
    protected Missao  missao;

    public AgenteInteligente(String nome, String direcao, int vida, int posicaoX, int posicaoY, int posicaoZ, Ambiente ambiente) {

        super(nome, direcao, vida, posicaoX, posicaoY, posicaoZ, ambiente);
        this.missao = null;
    }

    public void definirMissao(Missao m){
        this.missao = m;
    }
    public void abortarMissao(){
        this.missao = null;
    }

    public abstract void executarMissao(Ambiente ambiente);


}
