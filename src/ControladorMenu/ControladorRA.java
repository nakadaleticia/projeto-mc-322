package ControladorMenu;

import AmbienteP.Ambiente;
import Comunicacao.CentralComunicacao;
import Interfaces.Comunicavel;
import Robos.Robo;
import Robos.RoboResgateAereo;
import Exception.*;
import missaoP.MissaoBuscarPonto;
import missaoP.MissaoExplorar;
import missaoP.MissaoMonitorar;
import missaoP.MissaoPatrulhar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControladorRA {
    private final RoboResgateAereo robo;
    private final Ambiente ambiente;
    private final CentralComunicacao central;
    public ControladorRA(RoboResgateAereo robo, Ambiente ambiente, CentralComunicacao central) {
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
    private void escolherMissao(){
        Scanner scanner = new Scanner(System.in);
        String opcao = scanner.nextLine();
        System.out.println("--ESCOLHA UMA MISSÃO PARA O ROBO--");
        switch(opcao){
            case "1":
                MissaoExplorar m = new MissaoExplorar();
                robo.definirMissao(m);
                System.out.println("Missão definida!");
                break;
            case "2":
                List<int[]> pontosPatrulha = new ArrayList<int[]>() ;
                System.out.println("Quantos pontos o Robo vai patrulhar?");
                int num = scanner.nextInt();
                System.out.println("Digite os pontos que o robo vai ficar patrulhando");
                for (int i = 0; i < num; i++) {
                    int[] ponto = new int[2];

                    System.out.print("Digite a coordenada X: ");
                    ponto[0] = scanner.nextInt();


                    System.out.print("Digite a coordenada Y: ");
                    ponto[1] = scanner.nextInt();


                    System.out.println("Ponto lido: (" + ponto[0] + ", " + ponto[1] + ")");
                    pontosPatrulha.add(ponto);
                }

                MissaoPatrulhar m1 = new MissaoPatrulhar(pontosPatrulha);
                robo.definirMissao(m1);
                System.out.println("Missão definida!");
                break;
            case "3":
                MissaoMonitorar m2 = new MissaoMonitorar();
                robo.definirMissao(m2);
                System.out.println("Missão Definida!");
                break;
            case "4":
                System.out.print("Digite a coordenada X: ");
                int x = scanner.nextInt();


                System.out.print("Digite a coordenada Y: ");
                int y = scanner.nextInt();

                MissaoBuscarPonto m3 = new MissaoBuscarPonto(x,y);
                robo.definirMissao(m3);
                System.out.println("Missão definida!");
                break;

        }
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
        System.out.println("9 - Usar Sensores.Sensor(es)");
        System.out.println("10 - Mandar mensagem");
        System.out.println("11 - Receber mensagem");
        System.out.println("12 - Executar tarefa");
        System.out.println("13 - Definir missão");
        System.out.println("14 - Executar missão");
        System.out.println("15 - Abortar missão");
    }

    private void executarAcao(String opcao) {
        Scanner sc = new Scanner(System.in);
        switch (opcao) {
            case "w":
                robo.mover(0, 1, 0, 1, ambiente, robo);
                break;
            case "a":
                robo.mover(-1, 0, 0, 1, ambiente, robo);
                break;
            case "s":
                robo.mover(0, -1, 0, 1, ambiente, robo);
                break;
            case "d":
                robo.mover(1, 0, 0, -1, ambiente, robo);
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
                robo.mover(x, y, z, tempo, ambiente, robo);
                break;
            case "3":
                robo.exibirPosicao();
                break;
            case "4":
                robo.ativarModoEmergencia();
                break;
            case "5":
                System.out.println("Em qual coordenada está a vítima?");
                int xV = sc.nextInt();
                int yV = sc.nextInt();
                robo.resgatarVitima(xV, yV);
                break;
            case "6":
                System.out.println("Onde vai deixar as vítimas?");
                int xE = sc.nextInt();
                int yE = sc.nextInt();
                robo.evacuacaoDeVitimas(xE, yE);
                break;
            case "7":
                System.out.println("Vai subir quanto?");
                int subida = sc.nextInt();
                robo.subir(subida, robo, ambiente);
                break;
            case "8":
                System.out.println("Vai descer quanto?");
                int desc = sc.nextInt();
                robo.descer(desc, robo, ambiente);
                break;
            case "9":
                try {
                    robo.acionarSensores();
                } catch (RoboDesligadoException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;
            case "10":
                System.out.println("Escreva a mensagem:\n");
                String mensg = sc.nextLine();
                ClasseEscolher escolheRobo = new ClasseEscolher(ambiente,central);
                escolheRobo.exibe();
                Robo meuRobo = escolheRobo.escolheUm();
                Comunicavel comunicador = (Comunicavel) meuRobo;
                try {
                    robo.enviarMensagemPara(comunicador,mensg);
                } catch (RoboDesligadoException e) {
                    System.out.println("Robos.Robo desligado");
                } catch (ErroComunicacaoException e) {
                    System.out.println("Erro na comunicação");
                }
                central.registrarMensagem(robo.nome,mensg);
                break;
            case "11":
                System.out.println("Escreva a mensagem:\n");
                String mensg1 = sc.nextLine();
                try {
                    robo.receberMensagem(mensg1);
                } catch (RoboDesligadoException e) {
                    System.out.println("Robos.Robo desligado");
                }
                central.registrarMensagem(robo.nome,mensg1);
                break;
            case "12":
                System.out.println("Executando tarefa...\n");
                robo.executarTarefa();
                break;
            case "13":
                escolherMissao();
                break;
            case "14":
                robo.executarMissao(ambiente);
                break;
            case "15":
                robo.abortarMissao();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
