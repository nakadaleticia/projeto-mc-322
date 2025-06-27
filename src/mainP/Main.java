package mainP;

import AmbienteP.Ambiente;
import Comunicacao.CentralComunicacao;
import Interfaces.Entidade;
import MenuP.Menu;
import ObstaculoP.Obstaculo;
import Robos.*;
import Sensores.Sensor;
import Sensores.SensorEletromagnetico;
import Sensores.SensorProximidade;
import enums.TipoObstaculo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu('0');
        System.out.println("=== CRIAÇÃO DO AMBIENTE ===");
        int[] listDimen = menu.CriarAmbiente();
        Ambiente ambiente = new Ambiente(listDimen[0], listDimen[1], listDimen[2], null);
        CentralComunicacao c = new CentralComunicacao();
        Logger logger = new Logger("REGISTRO");
        //robo1
        GerenciadorSensor gerenciadorTanque = new GerenciadorSensor(logger);
        ModuloComunicacao moduloTanque = new ModuloComunicacao(c, null);
        ControleMovimentoTerrestre controleMovimentoTanque = new ControleMovimentoTerrestre(100,logger);
        SensorProximidade sensor1 = new SensorProximidade(10, ambiente, logger);
        //robo2
        GerenciadorSensor gerenciadorAlvo = new GerenciadorSensor(logger);
        ModuloComunicacao moduloAlvo = new ModuloComunicacao(c, null);
        ControleMovimentoTerrestre controleAlvo = new ControleMovimentoTerrestre(100,logger);
        SensorProximidade sensor2 = new SensorProximidade(5, ambiente, logger);

        //robo3
        GerenciadorSensor gerenciadorchama = new GerenciadorSensor(logger);
        ModuloComunicacao modulochama = new ModuloComunicacao(c, null);
        ControleMovimentoTerrestre controlechama = new ControleMovimentoTerrestre(100,logger);
        SensorProximidade sensor3 = new SensorProximidade(10, ambiente, logger);

        //robo4
        GerenciadorSensor gerenciadordummy = new GerenciadorSensor(logger);
        ModuloComunicacao modulodummy = new ModuloComunicacao(c, null);
        ControleMovimentoTerrestre controledummy = new ControleMovimentoTerrestre(100,logger);
        SensorEletromagnetico sensor4 = new SensorEletromagnetico(7, ambiente, logger);
        //robo5
        GerenciadorSensor gerenciadorresgate = new GerenciadorSensor(logger);
        ModuloComunicacao moduloresgate = new ModuloComunicacao(c, null);
        ControleMovimentoAereo controleresgate = new ControleMovimentoAereo(logger);
        SensorEletromagnetico sensor5 = new SensorEletromagnetico(10, ambiente, logger);

        //robo6
        GerenciadorSensor gerenciadorrecon = new GerenciadorSensor(logger);
        ModuloComunicacao modulorecon = new ModuloComunicacao(c, null);
        ControleMovimentoAereo controlerecon = new ControleMovimentoAereo(logger);
        SensorEletromagnetico sensor6 = new SensorEletromagnetico(5, ambiente, logger);

        // criar robôs
        RoboTanque tanque = new RoboTanque("Tanque", "N", 100, 5, 5, 2, ambiente, moduloTanque, controleMovimentoTanque, gerenciadorTanque);
        Robo alvo1 = new RoboTanque("Alvo1", "S", 100, 6, 5, 2, ambiente, moduloAlvo, controleAlvo, gerenciadorAlvo);
        RoboLancaChamas chama = new RoboLancaChamas("Chamas", "L", 100, 10, 10, 3, ambiente, modulochama, controlechama, gerenciadorchama);
        Robo dummy = new RoboTanque("Dummy", "S", 100, 11, 10, 2, ambiente, modulodummy, controledummy, gerenciadordummy);
        RoboResgateAereo resgate = new RoboResgateAereo("Resgate", "O", 100, 15, 15, 1, 6, ambiente, moduloresgate, controleresgate, gerenciadorresgate);
        RoboReconhecimento recon = new RoboReconhecimento("Recon", "S", 100, 0, 20, 1, 6, ambiente, modulorecon, controlerecon, gerenciadorrecon);
        //adicionar os robos nos sensores
        moduloTanque.setRobo(tanque);
        moduloAlvo.setRobo(alvo1);
        modulochama.setRobo(chama);
        modulodummy.setRobo(dummy);
        moduloresgate.setRobo(resgate);
        modulorecon.setRobo(recon);
        //adicionar os sensores no gerenciador
        gerenciadorTanque.adicionarSensor(sensor1);
        gerenciadorAlvo.adicionarSensor(sensor2);
        gerenciadorchama.adicionarSensor(sensor3);
        gerenciadordummy.adicionarSensor(sensor4);
        gerenciadorresgate.adicionarSensor(sensor5);
        gerenciadorrecon.adicionarSensor(sensor6);

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
