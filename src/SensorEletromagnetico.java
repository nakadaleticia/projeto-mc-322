/*
sensorEletromagnetico: encontra robos ativos dentro do raio de alcance.
 */

import java.util.ArrayList;

public class SensorEletromagnetico extends Sensor {
    ArrayList<Robo> robosDetectados;

    public SensorEletromagnetico(int raio, Ambiente ambiente) {
        super(raio, ambiente);
        this.robosDetectados = new ArrayList<>();
    }

    private int calcularDistancia(int posicaoRoboX, int posicaoOutroX, int posicaoRoboY, int posicaoOutroY) {
        int distX = posicaoRoboX - posicaoOutroX;
        int distY = posicaoRoboY - posicaoOutroY;

        return (int) Math.sqrt(distX * distX + distY * distY);
    }

    @Override
    public void monitorar(Robo solicitante) {
        for (Robo r : ambiente.getRobosAtivos()) {
            if (r != solicitante) {
                int distancia = calcularDistancia(r.posicaoX, solicitante.posicaoX, r.posicaoY, solicitante.posicaoY);

                if (distancia <= raio) {
                    robosDetectados.add(r);
                }
            }
        }
    }
}
