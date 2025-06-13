package Robos;

import Sensores.Sensor;

import java.util.ArrayList;

public class GerenciadorSensor {
    private final ArrayList<Sensor> sensores;

    public GerenciadorSensor() {
        sensores = new ArrayList<>();
    }
    public void adicionarSensor(Sensor sensor) {
        sensores.add(sensor);
        System.out.println("Sensor adicionado com sucesso");
    }
    public void usarSensores(Robo r){
        for (Sensor s : sensores) {
            s.monitorar(r);
        }
    }

}
