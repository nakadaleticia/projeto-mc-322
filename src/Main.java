/*
1. criar ambiente e obstáculos
2. criar robôs (todos os tipos)
3. adicionar obstáculos e robôs no ambiente
4. testar movimentação: válida, inválida (limite), posição ocupada
5. testar sensores: proximidade, eletromagnético
6. testar interações: chamas, míssil, resgatar, evacuar, reconhecimento
7. testar identificarObstaculo
8. testar dano, morte, blindagem, remoção automática
9. exibir status finais
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CRIAÇÃO DO AMBIENTE ===");
        Ambiente ambiente = new Ambiente(10, 10, 10, null); // cria ambiente 10x10x10

        System.out.println("\n=== CRIAÇÃO DE OBSTÁCULOS ===");
        Obstaculo buraco = new Obstaculo(3, 3, 3, 3, 0, TipoObstaculo.BURACO); // buraco ocupa uma célula (3,3)
        Obstaculo vitima = new Obstaculo(5, 5, 5, 5, 2, TipoObstaculo.VITIMA); // vítima está em (5,5)
        ambiente.adicionarObstaculo(buraco);
        ambiente.adicionarObstaculo(vitima);

        System.out.println("\n=== CRIAÇÃO DE ROBÔS ===");

        RoboLancaChamas rc = new RoboLancaChamas("Chamas", "N", 100, 2, 2, 3);
        RoboTanque rt = new RoboTanque("Tanque", "S", 100, 4, 4, 2);
        RoboReconhecimento rr = new RoboReconhecimento("Recon", "L", 100, 1, 1, 2, 6);
        RoboResgateAereo ra = new RoboResgateAereo("Resgate", "O", 100, 6, 6, 2, 8);

        rc.setAmbiente(ambiente);
        rt.setAmbiente(ambiente);
        rr.setAmbiente(ambiente);
        ra.setAmbiente(ambiente);

        ambiente.adicionarRobo(rc);   // Chamas em (2,2)
        ambiente.adicionarRobo(rt);   // Tanque em (4,4)
        ambiente.adicionarRobo(rr);   // Recon em (1,1)
        ambiente.adicionarRobo(ra);   // Resgate em (6,6)

        System.out.println("\n=== TESTES DE MOVIMENTAÇÃO ===");
        rc.mover(1, 0, 0, 1); // move para (3,2) -> permitido
        rt.mover(10, 10, 0, 1); // fora dos limites -> deve bloquear
        rr.mover(0, 0, 0, 1); // tenta mover para a própria posição -> permitido
        ra.mover(-1, -1, 0, -1); // aéreo, sem limite de velocidade -> permitido

        System.out.println("\n=== TESTES DE SENSORES ===");
        SensorProximidade sp1 = new SensorProximidade(5, ambiente);
        SensorEletromagnetico se1 = new SensorEletromagnetico(5, ambiente);

        rc.adicionarSensor(sp1);  // adiciona sensor de proximidade ao Chamas
        rt.adicionarSensor(se1);  // adiciona sensor eletromagnético ao Tanque
        rc.usarSensores();        // deve detectar robôs próximos
        rt.usarSensores();        // deve detectar robôs próximos

        System.out.println("\n=== TESTES DE COMBATE ===");
        rc.lancarChamas(rt);        // Tanque recebe 20 de dano
        rt.ativarModoDefesa();      // ativa blindagem, blindagem-- -> 3 restantes
        rc.lancarChamas(rt);        // Tanque recebe dano reduzido, modo defesa desativado
        rt.recarregarMissil();      // recarrega para 2
        rt.dispararMissil(rc);      // Chamas destruído e removido do ambiente

        System.out.println("\n=== TESTES DE RESGATE ===");
        ra.ativarModoEmergencia();         // sobe até altitude máxima
        ra.resgatarVitima(5, 5);           // move para vítima (5,5), resgata
        ra.evacuacaoDeVitimas(0, 0);       // move para (0,0) e esvazia vítimas

        System.out.println("\n=== TESTES DE RECONHECIMENTO ===");
        rr.ativarModoReconhecimento();     // sobe até altitude máxima (6)

        System.out.println("\n=== TESTE DE IDENTIFICAR OBSTÁCULO ===");
        rt.identificarObstaculo(); // deve identificar obstáculo (dependendo da posição final)

        System.out.println("\n=== STATUS FINAL DOS ROBÔS NO AMBIENTE ===");
        for (Robo r : ambiente.getRobosAtivos()) {
            r.exibirPosicao(); // imprime posição final de cada robô sobrevivente
        }
    }
}
