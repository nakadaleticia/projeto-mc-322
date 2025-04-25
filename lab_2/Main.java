public class Main {
    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100);

        // robôs terrestres
        RoboTanque tanque = new RoboTanque("tancudo", "Norte", 10, 10, 3, 2, 100, false);
        RoboLancaChamas chama = new RoboLancaChamas("foguinho", "Leste", 11, 10, 2, 5); // ao lado do tanque

        // robôs aéreos
        RoboResgateAereo resgate = new RoboResgateAereo("águia-resgate", "Sul", 5, 5, 0, 50, 3, false);
        RoboReconhecimento recon = new RoboReconhecimento("falcão-recon", "Oeste", 20, 20, 10, 40, false);

        // adiciona ao ambiente
        ambiente.adicionarRobo(tanque);
        ambiente.adicionarRobo(chama);
        ambiente.adicionarRobo(resgate);
        ambiente.adicionarRobo(recon);

        System.out.println("\n--- movimentação terrestre ---");
        tanque.mover(4, 0, 1); // velocidade = 4 > 3 (não pode)
        tanque.mover(2, 1, 2); // ok
        chama.mover(1, 0, 1);  // tentativa de mover para (12,10)
        chama.identificarObstaculo(ambiente); // identifica tancudo (12, 11)

        System.out.println("\n--- testando tanque ---");
        tanque.dispararMissil();
        tanque.dispararMissil(); // deve esgotar munição
        tanque.dispararMissil(); // tentativa com munição esgotada
        tanque.ativarModoDefensivo();
        tanque.receberDano(30);

        System.out.println("\n--- testando lança-chamas ---");
        chama.lancarChamas();
        chama.recarregarCombustivel(2);

        System.out.println("\n--- movimentação aérea ---");
        resgate.subir(60); // tenta subir além do limite
        resgate.evacuacaoDeEmergencia(); // sem modo ativado
        resgate.ativarModoEmergencia(); // modo ativado
        resgate.evacuacaoDeEmergencia();
        resgate.carregarVitima();
        resgate.carregarVitima();
        resgate.carregarVitima(); // excede capacidade
        resgate.descer(20);
        resgate.exibirPosicao(); // testa exibirPosicao para robôs aereos

        System.out.println("\n--- testando reconhecimento ---");
        recon.fazerReconhecimento(ambiente); // sem ativar modo
        recon.ativarModoReconhecimento();
        recon.descer(10); // altitude 30 < max
        recon.fazerReconhecimento(ambiente); // falha
        recon.subir(10); // volta ao máximo
        recon.fazerReconhecimento(ambiente);

        System.out.println("\n--- teste de limites do ambiente ---");
        /* terrestre */
        RoboTanque limite = new RoboTanque("terra-limite", "Leste", 99, 99, 2, 2, 10, false);
        ambiente.adicionarRobo(limite);
        limite.mover(5, 5, 1); // fora dos limites (104, 104)

        /* aéreo */
        RoboResgateAereo altitudeTeste = new RoboResgateAereo("aereo-limite", "Norte", 50, 50, 45, 50, 1, false);
        ambiente.adicionarRobo(altitudeTeste);
        altitudeTeste.subir(10); // já está em 45, max = 50 → só pode subir até 50
        altitudeTeste.mover(100, 0); // x = 150 → fora
        altitudeTeste.descer(100); // vai tentar descer até altitude negativa (vai para 0)

        System.out.println("\n--- verificação de obstáculos ---");
        tanque.identificarObstaculo(ambiente); // obstáculo: foguinho em (12, 10)
        recon.identificarObstaculo(ambiente); // não há obstáculos
        recon.identificarObstaculo(ambiente); // não há obstáculos

        System.out.println("\n--- teste extra: colisão entre robôs terrestres ---");
        RoboTanque trator = new RoboTanque("trator", "Sul", 12, 10, 3, 2, 50, false);
        ambiente.adicionarRobo(trator);
        trator.mover(-1, 0); // tenta ir para (11, 10), já ocupado por foguinho

        System.out.println("\n--- teste extra: colisão ao subir para altitude ocupada ---");
        RoboResgateAereo helico = new RoboResgateAereo("helico", "Norte", 20, 20, 30, 40, 2, true);
        ambiente.adicionarRobo(helico);
        helico.subir(10); // tenta subir para 40, já ocupado por falcão-recon

        System.out.println("\n--- teste extra: colisão ao descer para altitude ocupada ---");
        RoboResgateAereo bloqueador = new RoboResgateAereo("bloqueador", "leste", 20, 20, 20, 40, 1, false);
        ambiente.adicionarRobo(bloqueador);
        helico.descer(10); // tenta descer para 30, já ocupado por bloqueador

        System.out.println("\n--- teste extra: ativar modo reconhecimento em posição já ocupada ---");
        RoboReconhecimento vigia = new RoboReconhecimento("vigia", "Norte", 20, 20, 10, 40, false);
        ambiente.adicionarRobo(vigia);
        vigia.ativarModoReconhecimento(); // falcão-recon ainda está em (20,20,40)

        System.out.println("\n--- teste extra: ativar modo emergência em posição já ocupada ---");
        RoboResgateAereo resgatinho = new RoboResgateAereo("resgatinho", "Sul", 20, 20, 0, 40, 2, true);
        ambiente.adicionarRobo(resgatinho);
        resgatinho.ativarModoEmergencia(); // falcão-recon ainda está em (20,20,40)


        System.out.println("\n--- posições finais ---");
        tanque.exibirPosicao();
        chama.exibirPosicao();
        resgate.exibirPosicao();
        recon.exibirPosicao();
        limite.exibirPosicao();
        trator.exibirPosicao();
        helico.exibirPosicao();
        bloqueador.exibirPosicao();
        vigia.exibirPosicao();
        resgatinho.exibirPosicao();
        altitudeTeste.exibirPosicao();

    }
}
