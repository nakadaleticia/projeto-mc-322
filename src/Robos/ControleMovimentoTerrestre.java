package Robos;

import Interfaces.IControladorMovimento;
import AmbienteP.Ambiente;
import Exception.ForaDosLimitesException;
import Exception.ColisaoException;

public class ControleMovimentoTerrestre implements IControladorMovimento {
    protected int velocidadeMaxima;

    public ControleMovimentoTerrestre(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY, int deltaZ, int tempo, Ambiente ambiente, Robo r) {
        int velocidade = calcularVelocidade(deltaX, deltaY, tempo);
        if (velocidade > velocidadeMaxima) {
            System.out.println(r.nome + " não pode se mover. limite de velocidade excedido");
            return;
        }
        int novoX = r.getX() + deltaX;
        int novoY = r.getY() + deltaY;
        int novoZ = r.getZ();
        try {
            ambiente.moverEntidade(r, novoX, novoY, novoZ);
            r.setPosicao(novoX, novoY, novoZ);
            System.out.println(r.nome + " se moveu para (" + novoX + ", " + novoY + ", " + novoZ + ")");
        } catch (ForaDosLimitesException e) {
            System.out.println(r.nome + " não pode se mover: " + e.getMessage());
        } catch (ColisaoException e) {
            System.out.println(r.nome + " não pode se mover: " + e.getMessage());
        }
    }
    private int calcularVelocidade(int deltaX, int deltaY, int tempo) {
        double distancia = Math.sqrt(deltaX * deltaX + deltaY * deltaY); // calcula distância que robô deve percorrer
        double velocidade = distancia / tempo;

        return (int) velocidade; // truncamento para remover parte decimal
    }

}
