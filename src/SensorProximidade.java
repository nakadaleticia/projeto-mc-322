/*
sensorProximidade: encontra robos ativos dentro do raio de alcance.
exibe o nome, a distancia e a posicao dos robos detectados.
pode ser utilizado por qualquer tipo de robo.
 */

import java.util.ArrayList;

public class SensorProximidade extends Sensor {
    public SensorProximidade(int raio, Ambiente ambiente) {
        super(raio, ambiente);
    }

    private int calcularDistancia(Robo r1, Robo r2) {
        int dx = r1.posicaoX - r2.posicaoX;
        int dy = r1.posicaoY - r2.posicaoY;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void monitorar(Robo solicitante) {
        System.out.println("Sensor de Proximidade (" + solicitante.nome + "): verificando arredores...");

        for (Robo r : ambiente.getRobosAtivos()) {
            if (r != solicitante) {
                int distancia = calcularDistancia(r, solicitante);

                if (distancia <= raio) {
                    System.out.println("- Robô detectado: " + r.nome + " a " + distancia + " unidades. Posição: (" +
                            r.posicaoX + ", " + r.posicaoY + ", " + r.posicaoZ + ")");
                }
            }
        }
    }
}
