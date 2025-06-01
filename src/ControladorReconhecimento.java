import java.util.Scanner;

public class ControladorReconhecimento {
    private final RoboReconhecimento robo;
    private final Ambiente ambiente;
    private final CentralComunicacao central;

    public ControladorReconhecimento(RoboReconhecimento robo , Ambiente ambiente, CentralComunicacao central) {

        this.robo = robo;
        this.ambiente = ambiente;
        this.central = central;
    }

    public void iniciar() {
        mostrar();
        Scanner teclado = new Scanner(System.in);
        String opcao = teclado.nextLine();
        executarAcao(opcao);
    }

    public void mostrar() {
        System.out.println("\n=== CONTROLE RECONHECIMENTO ===");
        System.out.println("w a s d (mover)");
        System.out.println("0 - Sair");
        System.out.println("1 - Receber dano");
        System.out.println("2 - Mover (mais de uma direção de uma vez)");
        System.out.println("3 - Exibir Posição");
        System.out.println("4 - Ligar Reconhecimento");
        System.out.println("5 - Subir");
        System.out.println("6 - Descer");
        System.out.println("7 - Usar Sensor(es)");
        System.out.println("8 - Mandar mensagem");
        System.out.println("9 - Receber mensagem");
        System.out.println("10 - Executar tarefa");
    }

    public void executarAcao(String opcao) {
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
                System.out.println("Quanto dano ele receberá?");
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
                robo.ativarModoReconhecimento();
                break;
            case "5":
                System.out.println("Vai subir quanto?");
                int subida = sc.nextInt();
                robo.subir(subida);
                break;
            case "6":
                System.out.println("Vai descer quanto?");
                int desc = sc.nextInt();
                robo.descer(desc);
                break;
            case "7":
                try {
                    robo.acionarSensores();
                } catch (RoboDesligadoException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case "8":
                System.out.println("Escreva a mensagem:\n");
                String mensg = sc.nextLine();
                ClasseEscolher escolheRobo = new ClasseEscolher(ambiente,central);
                escolheRobo.exibe();
                Robo meuRobo = escolheRobo.escolheUm();
                Comunicavel comunicador = (Comunicavel) meuRobo;
                try {
                    robo.enviarMensagem(comunicador,mensg);
                } catch (RoboDesligadoException e) {
                    System.out.println("Robo desligado");
                } catch (ErroComunicacaoException e) {
                    System.out.println("Erro na comunicação");
                }
                central.registrarMensagem(robo.nome,mensg);
                break;
            case "9":
                System.out.println("Escreva a mensagem:\n");
                String mensg1 = sc.nextLine();
                try {
                    robo.receberMensagem(mensg1);
                } catch (RoboDesligadoException e) {
                    System.out.println("Robo desligado");
                }
                central.registrarMensagem(robo.nome,mensg1);
                break;
            case "10":
                System.out.println("Executando tarefa...\n");
                robo.executarTarefa();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
