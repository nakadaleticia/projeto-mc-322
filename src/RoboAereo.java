public class RoboAereo extends Robo{
    int altitude;
    int altitudeMaxima;

    public RoboAereo(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima) {
        super(nome, direcao, posicaoX, posicaoY);
        this.altitude = altitude;
        this.altitudeMaxima = altitudeMaxima;
    }

    public void subir(int metros) {
        if ((altitude + metros) > altitudeMaxima) {
            altitude = altitudeMaxima;
        } else {
            altitude += metros;
        }
    }

    public void descer(int metros) {
        if ((altitude - metros) < 0) {
            altitude = 0;
        } else {
            altitude -= metros;
        }
    }
}
