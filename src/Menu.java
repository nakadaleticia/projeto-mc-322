import java.util.Scanner;

public class Menu {
    int valor;
    Scanner sc = new Scanner(System.in);

    public Menu(int valor) {
        this.valor = valor;
    }

    public int[] CriarAmbiente() {
        System.out.println("Qual será seu X, Y e Z do ambiente?");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        return new int[]{x, y, z};
    }

    public boolean escolha(int opcao, Ambiente ambiente) {
        switch (opcao) {
            case 0:
                return sair();

            case 1:
                visualizarMapa(ambiente);
                break;

            case 2:
                listarRobos(ambiente);
                break;

            case 3:
                escolherRobo(ambiente);
                break;

            case 4:
                excluirRobo(ambiente);
                break;

            case 5:
                listarMensagens();
                break;

            default:
                System.out.println("Opção inválida.");
        }
        return true;
    }

    public boolean sair() {
        return false;
    }

    private void visualizarMapa(Ambiente ambiente) {
        ambiente.visualizarAmbiente();
    }

    private void listarRobos(Ambiente ambiente) {
        System.out.println("\n=== Lista de Robôs ===");
        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo robo) {
                System.out.println("- " + robo.getNome() + " | Tipo: " + robo.getClass().getSimpleName() + " | Ligado: " + robo.estaLigado());
            }
        }
    }

    private void escolherRobo(Ambiente ambiente) {
        ClasseEscolher classeE = new ClasseEscolher(ambiente);
        classeE.iniciar();
    }

    private void excluirRobo(Ambiente ambiente) {
        ClasseEscolher classeE = new ClasseEscolher(ambiente);
        System.out.println("Escolha um para ser excluido");
        classeE.exibe();
        Robo robo = classeE.escolheUm();
        if (robo != null) {
            ambiente.removerEntidade(robo);
        } else {
            System.out.println("Nenhum robô selecionado.");
        }
    }

    private void listarMensagens() {
        System.out.println("\n=== Mensagens ===");
        CentralComunicacao central = new CentralComunicacao(); // isso pode ser adaptado para instância única no seu sistema
        central.exibirMensagens();
    }
}
