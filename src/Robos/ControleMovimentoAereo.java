package Robos;
import Interfaces.IControladorMovimento;
import AmbienteP.Ambiente;
import Exception.ForaDosLimitesException;
import Exception.ColisaoException;

public class ControleMovimentoAereo implements IControladorMovimento {
    public Logger logger;
    public ControleMovimentoAereo(Logger logger) {
        this.logger = logger;
    }
    @Override
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo, Ambiente ambiente, Robo r) {
        int novoX = r.getX() + deltaX;
        int novoY = r.getY() + deltaY;
        int novoZ = r.getZ() + deltaZ;
        try {
            ambiente.moverEntidade(r, novoX, novoY, novoZ);
            r.setPosicao(novoX, novoY, novoZ);
            System.out.println(r.nome + " se moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
            logger.registrar("MOVIMENTO: "+ r.nome+ " moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
        } catch (ForaDosLimitesException e) {
            System.out.println(r.nome + " não pode se mover: " + e.getMessage());
        } catch (ColisaoException e) {
            System.out.println(r.nome + " não pode se mover: " + e.getMessage());
        }
    }

    public void subir(int metros, Robo r, Ambiente ambiente) {
        int novoZ = metros;

        mover(0, 0, novoZ, -1, ambiente, r);
    }

    public void descer(int metros, Robo r, Ambiente ambiente){
        int novoZ =  - metros;
        mover(0,0,novoZ, -1, ambiente, r);
    }

}
