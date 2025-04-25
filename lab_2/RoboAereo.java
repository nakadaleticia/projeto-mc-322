public class RoboAereo extends Robo{
    int altitude;
    int altitudeMaxima;

    public RoboAereo(String nome, String direcao, int posicaoX, int posicaoY, int altitude, int altitudeMaxima) {
        super(nome, direcao, posicaoX, posicaoY);
        this.altitude = altitude;
        this.altitudeMaxima = altitudeMaxima;
    }

    public void subir(int metros) {
        int novaAltitude = Math.min(altitude + metros, altitudeMaxima);

        if (ambiente.posicaoOcupada(posicaoX, posicaoY, novaAltitude, this)) {
            System.out.println(nome + " não pode subir: posição em (" + posicaoX + ", " + posicaoY + ", " + novaAltitude + ") já ocupada");
            return;
        }

        altitude = novaAltitude;
        System.out.println(nome + " subiu para altitude " + altitude);
    }

    public void descer(int metros) {
        int novaAltitude = Math.max(altitude - metros, 0);

        if (ambiente.posicaoOcupada(posicaoX, posicaoY, novaAltitude, this)) {
            System.out.println(nome + " não pode descer: posição em (" + posicaoX + ", " + posicaoY + ", " + novaAltitude + ") já ocupada");
            return;
        }

        altitude = novaAltitude;
        System.out.println(nome + " desceu para altitude " + altitude);
    }

    @Override
    public void exibirPosicao() {
        System.out.println("posição " + nome +": (" + posicaoX + ", " + posicaoY + ", " + altitude + ") " + direcao);
    }
}
