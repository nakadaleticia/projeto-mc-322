/*
sensorProximidade: encontra robos e obstaculos ativos dentro do raio de alcance.
exibe o nome, a distancia e a posicao dos robos detectados.
exibe o tipo, altura e se o obstaculo bloqueia passagem.
pode ser utilizado por qualquer tipo de robo.
*/

import java.util.ArrayList;

public class SensorProximidade extends Sensor {
    public SensorProximidade(int raio, Ambiente ambiente) {
        super(raio, ambiente);
    }

    private int calcularDistancia(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void monitorar(Robo solicitante) {
        System.out.println("Sensor de Proximidade (" + solicitante.nome + "): verificando arredores...");

        boolean encontrouAlgo = false;

        // detectar outros robôs
        for (Robo r : ambiente.getRobosAtivos()) {
            if (r != solicitante) {
                int distancia = calcularDistancia(r.posicaoX, r.posicaoY, solicitante.posicaoX, solicitante.posicaoY);
                if (distancia <= raio) {
                    encontrouAlgo = true;
                    System.out.println("- Robô detectado: " + r.nome + " a " + distancia + " unidades. Posição: (" +
                            r.posicaoX + ", " + r.posicaoY + ", " + r.posicaoZ + ")");
                }
            }
        }

        // detectar obstáculos
        for (Obstaculo o : ambiente.getObstaculos()) {
            int centroX = (o.getPosicaoX1() + o.getPosicaoX2()) / 2;
            int centroY = (o.getPosicaoY1() + o.getPosicaoY2()) / 2;
            int distancia = calcularDistancia(centroX, centroY, solicitante.posicaoX, solicitante.posicaoY);

            if (distancia <= raio) {
                encontrouAlgo = true;
                System.out.println("- Obstáculo detectado: " + o.getTipoInt() +
                        " (altura=" + o.getAltura() + ", bloqueiaPassagem=" + o.getTipoInt().isBloqueiaPassagem() + ")" +
                        " a " + distancia + " unidades. Posição aproximada: (" + centroX + ", " + centroY + ")");
            }
        }

        if (!encontrouAlgo) {
            System.out.println("Nenhum robô ou obstáculo detectado ao redor.");
        }
    }
}