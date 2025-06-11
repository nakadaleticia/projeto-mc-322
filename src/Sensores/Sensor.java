package Sensores;/*
os sensore sao responsaveis pela percepcao de mundo dos robos.
cada robo pode ter um ou mais sensores.
o sensor 'enxerga' o ambiente para o robo.
 */

import AmbienteP.Ambiente;
import Robos.Robo;

public class Sensor {
    public Ambiente ambiente;
    public final int raio; // raio de alcance do sensor

    public Sensor(int raio, Ambiente ambiente) {
        this.raio = raio;
        this.ambiente = ambiente;
    }

    /* metodo depende do tipo de sensor */
    public void monitorar(Robo solicitante) {
        return;
    }
}
