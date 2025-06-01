import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu('0');
        System.out.println("=== CRIAÇÃO DO AMBIENTE ===");
        int[] listDimen = menu.CriarAmbiente();
        Ambiente ambiente = new Ambiente(listDimen[0], listDimen[1], listDimen[2], null);

        // criar robôs
        RoboTanque tanque = new RoboTanque("Tanque", "N", 100, 5, 5, 2, ambiente);
        Robo alvo1 = new RoboTanque("Alvo1", "S", 100, 6, 5, 2, ambiente);
        RoboLancaChamas chama = new RoboLancaChamas("Chamas", "L", 100, 10, 10, 3, ambiente);
        Robo dummy = new RoboTanque("Dummy", "S", 100, 11, 10, 2, ambiente);
        RoboResgateAereo resgate = new RoboResgateAereo("Resgate", "O", 100, 15, 15, 1, 6, ambiente);
        RoboReconhecimento recon = new RoboReconhecimento("Recon", "S", 100, 0, 20, 1, 6, ambiente);


        // adicionar robôs no ambiente
        ambiente.adicionarEntidade(recon);
        ambiente.adicionarEntidade(resgate);
        ambiente.adicionarEntidade(chama);
        ambiente.adicionarEntidade(dummy);
        ambiente.adicionarEntidade(tanque);
        ambiente.adicionarEntidade(alvo1);

        // adicionar obstáculo
        Obstaculo obstaculo = new Obstaculo(1, 2, 1, 2, 3, TipoObstaculo.VITIMA);
        ambiente.adicionarEntidade(obstaculo);

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("O que deseja fazer?");
            System.out.println(" 0 - Sair\n 1 - Visualizar Mapa\n 2 - Listar Robôs\n 3 - Escolher Robô\n 4 - Excluir Robô\n 5 - Listar Mensagens");
            int opcao = sc.nextInt();
            boolean escolha = menu.escolha(opcao, ambiente);
            if (!escolha) {
                break;
            }
        }

        // POSIÇÕES FINAIS
        System.out.println("\n=== POSIÇÕES FINAIS ===");
        for (Entidade e : ambiente.getEntidades()) {
            if (e instanceof Robo robo) {
                robo.exibirPosicao();
            }
        }
    }
}
