package Robos;

import Sensores.Sensor;

import java.util.ArrayList;

public class GerenciadorSensor {
    private final ArrayList<Sensor> sensores;
    public Logger logger;
    public GerenciadorSensor(Logger logger) {

        sensores = new ArrayList<>();
        this.logger = logger;
    }
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
        System.out.println("Sensor adicionado com sucesso");
    }
    public void usarSensores(Robo r){
        logger.registrar("SENSOR: "+r.getNome()+ " ativou o sensor com sucesso");
        for (Sensor s : sensores) {
            s.monitorar(r);
        }
    }

}
