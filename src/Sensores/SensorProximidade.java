package Sensores;/*
sensorProximidade: encontra robos e obstaculos ativos dentro do raio de alcance.
exibe o nome, a distancia e a posicao dos robos detectados.
exibe o tipo, altura e se o obstaculo bloqueia passagem.
pode ser utilizado por qualquer tipo de robo.
*/

import AmbienteP.Ambiente;
import Interfaces.Entidade;
import ObstaculoP.Obstaculo;
import Robos.Robo;
import Robos.Logger;

public class SensorProximidade extends Sensor {
    public SensorProximidade(int raio, Ambiente ambiente, Logger logger) {
        super(raio, ambiente, logger);
    }



    @Override
    public void monitorar(Robo solicitante) {
        System.out.println("Sensores.Sensor de Proximidade (" + solicitante.getNome() + "): verificando arredores...");

        boolean encontrouAlgo = false;

        for (Entidade entidade : ambiente.getEntidades()) {
            if (entidade == solicitante) {
                continue; // ignora o próprio robô
            }

            if (entidade instanceof Robo robo) {
                int distancia = calcularDistancia(robo.getX(), robo.getY(), solicitante.getX(), solicitante.getY());
                if (distancia <= raio) {
                    encontrouAlgo = true;
                    System.out.println("- Robô detectado: " + robo.getNome() + " a " + distancia + " unidades. Posição: (" +
                            robo.getX() + ", " + robo.getY() + ", " + robo.getZ() + ")");
                    logger.registrar("DETECÇÃO: "+ robo.getNome()+ " detectado com sucesso pelo Sensor Proximidade!");
                }
            } else if (entidade instanceof Obstaculo obstaculo) {
                int centroX = (obstaculo.getPosicaoX1() + obstaculo.getPosicaoX2()) / 2;
                int centroY = (obstaculo.getPosicaoY1() + obstaculo.getPosicaoY2()) / 2;
                int distancia = calcularDistancia(centroX, centroY, solicitante.getX(), solicitante.getY());

                if (distancia <= raio) {
                    encontrouAlgo = true;
                    System.out.println("- Obstáculo detectado: " + obstaculo.getTipoObstaculo() +
                            " (altura=" + obstaculo.getAltura() + ", bloqueiaPassagem=" + obstaculo.getTipoObstaculo().isBloqueiaPassagem() + ")" +
                            " a " + distancia + " unidades. Posição aproximada: (" + centroX + ", " + centroY + ")");
                    logger.registrar("DETECÇÃO: "+obstaculo.getTipoObstaculo()+ " detectado com sucesso pelo Sensor Proximidade!");
                }
            }
        }

        if (!encontrouAlgo) {
            System.out.println("Nenhum robô ou obstáculo detectado ao redor.");
        }
    }
}
