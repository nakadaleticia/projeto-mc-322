package missaoP;

import Interfaces.Missao;
import Robos.AgenteInteligente;
import AmbienteP.Ambiente;
import java.util.List;

public class MissaoPatrulhar implements Missao {
    private final List<int[]> pontosDePatrulha;
    private int indicePontoAtual = 0;

    public MissaoPatrulhar(List<int[]> pontosDePatrulha) {
        this.pontosDePatrulha = pontosDePatrulha;
    }

    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente) {
        if (pontosDePatrulha == null || pontosDePatrulha.isEmpty()) {
            System.out.println("Missão de Patrulha cancelada: nenhum ponto definido.");
            return;
        }

        int[] alvo = pontosDePatrulha.get(indicePontoAtual);
        System.out.println(robo.getNome() + " patrulhando em direção a (" + alvo[0] + ", " + alvo[1] + ")");

        // verifica se chegou no destino
        if (robo.getX() == alvo[0] && robo.getY() == alvo[1]) {
            System.out.println(robo.getNome() + " chegou ao ponto de patrulha " + indicePontoAtual + ".");
            // Avança para o próximo ponto, voltando ao início se necessário
            indicePontoAtual = (indicePontoAtual + 1) % pontosDePatrulha.size();
            return;
        }

        // Move um passo (uma unidade apenas) em direção ao alvo
        int deltaX = Integer.compare(alvo[0], robo.getX());
        int deltaY = Integer.compare(alvo[1], robo.getY());

        robo.mover(deltaX, deltaY, 0, -1, ambiente, robo);
    }
}
