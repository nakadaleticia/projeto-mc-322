public class Main {
    public static void main(String[] args) {
        Ambiente ambiente = new Ambiente(100, 100);

        // robôs terrestres
        RoboTanque tanque = new RoboTanque("tancudo", "Norte", 10, 10, 3, 2, 100, false);
        RoboLancaChamas chama = new RoboLancaChamas("foguinho", "Leste", 11, 10, 2, 5); // ao lado do tanque

        // robôs aéreos
        RoboResgateAereo resgate = new RoboResgateAereo("águia-resgate", "Sul", 5, 5, 0, 50, 3, 0);
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
        resgate.ativarModoEmergencia();
        resgate.evacuacaoDeEmergencia();
        resgate.carregarVitima();
        resgate.carregarVitima();
        resgate.carregarVitima(); // excede capacidade
        resgate.descer(20);
        resgate.exibirPosicao();

        System.out.println("\n--- testando reconhecimento ---");
        RoboReconhecimento novoRecon = new RoboReconhecimento("espião", "Norte", 50, 50, 10, 40, false);
        ambiente.adicionarRobo(novoRecon);
        novoRecon.fazerReconhecimento(ambiente); // sem ativar modo
        novoRecon.ativarModoReconhecimento();
        novoRecon.descer(10); // altitude 30 < max
        novoRecon.fazerReconhecimento(ambiente); // falha
        novoRecon.subir(10); // volta ao máximo
        novoRecon.fazerReconhecimento(ambiente);

        System.out.println("\n--- teste de limites do ambiente ---");
        RoboTanque limite = new RoboTanque("tanque-limite", "Leste", 99, 99, 2, 2, 10, false);
        ambiente.adicionarRobo(limite);
        limite.mover(5, 5, 1); // fora dos limites

        System.out.println("\n--- verificação de obstáculos ---");
        tanque.identificarObstaculo(ambiente);
        recon.identificarObstaculo(ambiente);
        novoRecon.identificarObstaculo(ambiente);

        System.out.println("\n--- posições finais ---");
        tanque.exibirPosicao();
        chama.exibirPosicao();
        resgate.exibirPosicao();
        recon.exibirPosicao();
        novoRecon.exibirPosicao();
        limite.exibirPosicao();
    }
}
