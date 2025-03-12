public class Main {
    public static void main(String[] args) {
        // creates
        Ambiente ambiente = new Ambiente(10, 10);

        // creates robot
        Robo robo1 = new Robo("R2D2", 0, 0);
        Robo robo2 = new Robo("C3PO", 5,5);

        // tests moving method
        robo1.mover(2, 3);
        robo2.mover(-3, -4);

        // tests limit verification method
        if (ambiente.dentroDosLimites(robo1.posicaoX, robo1.posicaoY)) {
            System.out.println(robo1.nome + " dentro dos limites");
        } else {
            System.out.println(robo1.nome + " fora dos limites");
        }

        if (ambiente.dentroDosLimites(robo2.posicaoX, robo2.posicaoY)) {
            System.out.println(robo2.nome + " dentro dos limites");
        } else {
            System.out.println(robo2.nome + " fora dos limites");
        }

        // print position
        robo1.exibirPosicao();
        robo2.exibirPosicao();
    }
}
