/*
sensorEletromagnetico: encontra robôs ativos dentro do raio de alcance.
*/

import java.util.ArrayList;

public class SensorEletromagnetico extends Sensor {
    private ArrayList<Robo> robosDetectados;

    public SensorEletromagnetico(int raio, Ambiente ambiente) {
        super(raio, ambiente);
        this.robosDetectados = new ArrayList<>();
    }

    private int calcularDistancia(int x1, int y1, int x2, int y2) {
        int distX = x1 - x2;
        int distY = y1 - y2;
        return (int) Math.sqrt(distX * distX + distY * distY);
    }

    @Override
    public void monitorar(Robo solicitante) {
        robosDetectados.clear();  // limpa a lista a cada monitoramento
        System.out.println("Sensor Eletromagnético (" + solicitante.getNome() + "): procurando robôs ativos no raio " + raio);

        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo robo && robo != solicitante) {
                int distancia = calcularDistancia(robo.getX(), robo.getY(), solicitante.getX(), solicitante.getY());
                if (distancia <= raio) {
                    robosDetectados.add(robo);
                    System.out.println("- Robô detectado: " + robo.getNome() + " a " + distancia + " unidades.");
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
