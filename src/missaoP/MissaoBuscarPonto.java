package missaoP;

import Interfaces.Missao;
import Robos.AgenteInteligente;
import AmbienteP.Ambiente;

public class MissaoBuscarPonto implements Missao {
    private int alvoX;
    private int alvoY;
    private boolean concluida = false;

    public MissaoBuscarPonto(int x, int y) {
        this.alvoX = x;
        this.alvoY = y;
    }

    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente) {
        if (concluida) {
            System.out.println(robo.getNome() + " já concluiu a missão de buscar o ponto.");
            return;
        }

        System.out.println(robo.getNome() + " em missão para buscar o ponto (" + alvoX + ", " + alvoY + ").");

        if (robo.getX() == alvoX && robo.getY() == alvoY) {
            System.out.println(robo.getNome() + " chegou ao destino! Missão concluída.");
            concluida = true;
            return;
        }

        int deltaX = Integer.compare(alvoX, robo.getX());
        int deltaY = Integer.compare(alvoY, robo.getY());

        robo.mover(deltaX, deltaY, 0, -1, ambiente, robo);
    }
}
