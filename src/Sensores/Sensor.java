package Sensores;/*
os sensore sao responsaveis pela percepcao de mundo dos robos.
cada robo pode ter um ou mais sensores.
o sensor 'enxerga' o ambiente para o robo.
 */

import AmbienteP.Ambiente;
import Robos.Robo;

public abstract class Sensor {
    public Ambiente ambiente;
    public final int raio; // raio de alcance do sensor

    public Sensor(int raio, Ambiente ambiente) {
        this.raio = raio;
        this.ambiente = ambiente;
    }

    protected int calcularDistancia(int x1, int y1, int x2, int y2) {
        int distX = x1 - x2;
        int distY = y1 - y2;
        return (int) Math.sqrt(distX * distX + distY * distY);
    }

    /* metodo depende do tipo de sensor */
    public abstract void monitorar(Robo solicitante);
}
