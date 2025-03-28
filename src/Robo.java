public class Robo {
    String nome;
    String direcao;
    int posicaoX;
    int posicaoY;

    public Robo(String nome, String direcao, int posicaoX, int posicaoY) {
        this.nome = nome;
        this.direcao = direcao;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    Ambiente ambiente;

    public void setAmbiente(Ambiente ambiente) { // este metodo foi adicionado para que o robô possa verificar os limites antes de se mover
        this.ambiente = ambiente;
    }

    public void identificarObstaculo(Ambiente ambiente) {
        int obstaculosIdentificados = 0;
        System.out.println(nome + " está verificando obstáculos...");

        for (Robo outro : ambiente.encontrarRobosAtivos()) {
            if (outro != this) {
                int dx = Math.abs(outro.posicaoX - this.posicaoX);
                int dy = Math.abs(outro.posicaoY - this.posicaoY);

                if ((dx == 1 && dy <= 1) || (dy == 1 && dx <= 1)) {
                    obstaculosIdentificados++;

                    if (outro instanceof RoboAereo) {
                        RoboAereo aereo = (RoboAereo) outro;
                        System.out.println("obstáculo detectado: " + outro.nome + " em (" + outro.posicaoX + ", " + outro.posicaoY + ", " + aereo.altitude + ")");
                    } else {
                        System.out.println("obstáculo detectado: " + outro.nome + " em (" + outro.posicaoX + ", " + outro.posicaoY + ")");
                    }
                }
            }
        }

        if (obstaculosIdentificados == 0) {
            System.out.println(nome + " não identificou nenhum obstáculo nas posições adjacentes");
        }
    }


    public void mover(int deltaX, int deltaY) {
        posicaoX += deltaX;
        posicaoY += deltaY;
        if (ambiente.dentroDosLimites(posicaoX, posicaoY)) {
            System.out.println(nome + " moveu para (" + posicaoX + ", " + posicaoY + ")");
        } else {
            System.out.println(nome + " não pode se mover para fora dos limites do ambiente");
        }
    }

    public void exibirPosicao() {
        System.out.println("posição " + nome +": (" + posicaoX + ", " + posicaoY + ") " + direcao);
    }


}
