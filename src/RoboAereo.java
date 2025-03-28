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
            System.out.println(nome + " não pode ultrapassar altitude máxima. nova altitude = altitude máxima: " + altitudeMaxima);
        } else {
            altitude += metros;
            System.out.println(nome + " subiu para altitude " + altitude);
        }
    }

    public void descer(int metros) {
        if ((altitude - metros) < 0) {
            altitude = 0;
            System.out.println(nome + " não pode descer abaixo de 0. nova altitude: " + altitude);
        } else {
            altitude -= metros;
            System.out.println(nome + " desceu para altitude " + altitude);
        }
    }

    @Override
    public void exibirPosicao() {
        System.out.println("posição " + nome +": (" + posicaoX + ", " + posicaoY + ", " + altitude + ") " + direcao);
    }
}
