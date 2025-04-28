import java.util.Scanner;

public class ControladorRA {
    private final RoboResgateAereo robo;

    public ControladorRA(RoboResgateAereo robo) {
        this.robo = robo;
    }
    public void iniciar() {
        mostrar();
        Scanner teclado = new Scanner(System.in);
        String opcao = teclado.nextLine();
        executarAcao(opcao);
    }

    public void mostrar() {
        System.out.println("\n=== CONTROLE RESGATE AÉREO ===");
        System.out.println("w a s d (mover)");
        System.out.println("0 - Sair");
        System.out.println("1 - Receber dano");
        System.out.println("2 - Mover (mais de uma direção de uma vez)");
        System.out.println("3 - Exibir Posição");
        System.out.println("4 - Ativar Modo Emergencia");
        System.out.println("5 - Resgatar Vítimas");
        System.out.println("6 - Evacuar");
        System.out.println("7 - Subir");
        System.out.println("8 - Descer");
        System.out.println("9 - Usar Sensor(es)");

    }

    private void executarAcao(String opcao) {
        Scanner sc = new Scanner(System.in);
        switch (opcao) {
            case "w":
                robo.mover(0,1,0,1);
                break;
            case "a":
                robo.mover(-1,0,0,1);
                break;
            case "s":
                robo.mover(0,-1,0,1);
                break;
            case "d":
                robo.mover(1,0,0,-1);
                break;
            case "0":
                break;
            case "1":
                System.out.println("Quando dano ele receberá?");
                int dano = sc.nextInt();
                robo.receberDano(dano);
                break;
            case "2":
                System.out.println("Escolha os delta x, y e z e o tempo");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                int tempo = sc.nextInt();
                robo.mover(x, y, z, tempo);
                break;
            case "3":
                robo.exibirPosicao();
                break;
            case "4":
                robo.ativarModoEmergencia();
                break;
            case "5":
                System.out.println("Em qual Coordenada está a vítima?");
                int xV = sc.nextInt();
                int yV = sc.nextInt();
                robo.resgatarVitima(xV, yV);
                break;
            case "6":
                System.out.println("Onde Vai deixar as vítimas?");
                int xE = sc.nextInt();
                int yE = sc.nextInt();
                robo.evacuacaoDeVitimas(xE, yE);
                break;
            case "7":
                System.out.println("Vai subir quanto?");
                int subida = sc.nextInt();
                robo.subir(subida);
                break;
            case "8":
                System.out.println("Vai descer quanto?");
                int desc = sc.nextInt();
                robo.descer(desc);
                break;
            case "9":
                robo.usarSensores();
                break;
        }

    }
}
