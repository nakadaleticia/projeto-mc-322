import java.util.Scanner;

public class ControladorTanque {
    private final RoboTanque robo;
    private final Ambiente ambiente;

    public ControladorTanque(RoboTanque robo, Ambiente ambiente) {
        this.robo = robo;
        this.ambiente = ambiente;
    }

    public void iniciar() {
        mostrar();
        Scanner teclado = new Scanner(System.in);
        String opcao = teclado.nextLine();
        executarAcao(opcao);
    }

    public void mostrar() {
        System.out.println("\n=== CONTROLE TANQUE ===");
        System.out.println("w a s d (mover)");
        System.out.println("0 - Sair");
        System.out.println("1 - Receber dano");
        System.out.println("2 - Mover (mais de uma direção de uma vez)");
        System.out.println("3 - Exibir Posição");
        System.out.println("4 - Ativar Modo Defesa");
        System.out.println("5 - Recarregar Míssil");
        System.out.println("6 - Disparar Míssil");
        System.out.println("7 - Usar Sensor(es)");
    }

    private void executarAcao(String opcao) {
        Scanner sc = new Scanner(System.in);
        switch (opcao) {
            case "w":
                robo.mover(0, 1, 0, 1);
                break;
            case "a":
                robo.mover(-1, 0, 0, 1);
                break;
            case "s":
                robo.mover(0, -1, 0, 1);
                break;
            case "d":
                robo.mover(1, 0, 0, -1);
                break;
            case "0":
                break;
            case "1":
                System.out.println("Quanto de dano ele receberá?");
                int dano = sc.nextInt();
                robo.receberDano(dano);
                break;
            case "2":
                System.out.println("Escolha os delta x e y e o tempo");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int tempo = sc.nextInt();
                robo.mover(x, y, 0, tempo);
                break;
            case "3":
                robo.exibirPosicao();
                break;
            case "4":
                robo.ativarModoDefesa();
                break;
            case "5":
                robo.recarregarMissil();
                break;
            case "6":
                ClasseEscolher CE = new ClasseEscolher(ambiente);
                System.out.println("Escolha o robô que você quer atacar");
                CE.exibe();
                Robo alvo = CE.escolheUm();
                if (alvo != null) {
                    robo.dispararMissil(alvo);
                } else {
                    System.out.println("Nenhum robô selecionado.");
                }
                break;
            case "7":
                try {
                    robo.acionarSensores();
                } catch (RoboDesligadoException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
