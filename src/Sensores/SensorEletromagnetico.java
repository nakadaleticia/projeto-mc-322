package Sensores;/*
sensorEletromagnetico: encontra robôs ativos dentro do raio de alcance.
*/

import AmbienteP.Ambiente;
import Interfaces.Entidade;
import Robos.Robo;
import Robos.Logger;

import java.util.ArrayList;

public class SensorEletromagnetico extends Sensor {
    private ArrayList<Robo> robosDetectados;

    public SensorEletromagnetico(int raio, Ambiente ambiente, Logger logger) {
        super(raio, ambiente, logger);
        this.robosDetectados = new ArrayList<>();
    }



    @Override
    public void monitorar(Robo solicitante) {
        robosDetectados.clear();  // limpa a lista a cada monitoramento
        System.out.println("Sensores.Sensor Eletromagnético (" + solicitante.getNome() + "): procurando robôs ativos no raio " + raio);

        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo robo && robo != solicitante) {
                int distancia = calcularDistancia(robo.getX(), robo.getY(), solicitante.getX(), solicitante.getY());
                if (distancia <= raio) {
                    robosDetectados.add(robo);
                    System.out.println("- Robô detectado: " + robo.getNome() + " a " + distancia + " unidades.");
                    logger.registrar("DETECÇÃO: "+ robo.getNome()+ " detectado com sucesso pelo Sensor Eletromagnetico!");

                }
            }
        }

        if (robosDetectados.isEmpty()) {
            System.out.println("Nenhum robô detectado pelo sensor eletromagnético.");
        }
    }

    // Opcional: método para retornar os robôs detectados
    public ArrayList<Robo> getRobosDetectados() {
        return new ArrayList<>(robosDetectados);
    }
}
