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
        System.out.println("Sensor de Proximidade (" + solicitante.getNome() + "): verificando arredores...");

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
                }
            }
        }

        if (!encontrouAlgo) {
            System.out.println("Nenhum robô ou obstáculo detectado ao redor.");
        }
    }
}
