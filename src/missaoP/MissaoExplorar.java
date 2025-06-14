package missaoP;

import Interfaces.Missao;
import Robos.AgenteInteligente;
import AmbienteP.Ambiente;
import java.util.Random;

public class MissaoExplorar implements Missao {
    private final Random aleatorio = new Random();

    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente) {
        System.out.println(robo.getNome() + " está em Missão de Exploração...");

        // Gera um movimento aleatório em X e Y (-1, 0 ou 1)
        int deltaX = aleatorio.nextInt(3) - 1; // Gera -1, 0 ou 1
        int deltaY = aleatorio.nextInt(3) - 1; // Gera -1, 0 ou 1

        // Garante que o robô não fique parado
        if (deltaX == 0 && deltaY == 0) {
            deltaX = 1; // Força um movimento se o resultado for (0,0)
        }


        robo.mover(deltaX, deltaY, 0, -1, ambiente, robo);
    }
}
