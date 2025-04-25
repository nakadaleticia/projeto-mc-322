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
        Ambiente ambiente = new Ambiente(20, 20, 10, null);

        System.out.println("\n=== TESTES: ROBO TANQUE ===");
        RoboTanque tanque = new RoboTanque("Tanque", "N", 100, 5, 5, 2, ambiente);
        Robo alvo1 = new RoboTanque("Alvo1", "S", 100, 6, 5, 2, ambiente);
        ambiente.adicionarRobo(tanque);
        ambiente.adicionarRobo(alvo1);

        tanque.dispararMissil(alvo1); // sucesso
        Robo alvo2 = new RoboTanque("Alvo2", "S", 100, 7, 5, 2, ambiente);
        ambiente.adicionarRobo(alvo2);
        tanque.dispararMissil(alvo2); // sucesso
        tanque.dispararMissil(alvo2); // falha
        tanque.recarregarMissil(); // sucesso
        tanque.recarregarMissil(); // falha
        tanque.ativarModoDefesa(); // sucesso
        tanque.ativarModoDefesa(); // 3
        tanque.ativarModoDefesa(); // 2
        tanque.ativarModoDefesa(); // 1
        tanque.ativarModoDefesa(); // 0
        tanque.ativarModoDefesa(); // falha

        System.out.println("\n=== TESTES: ROBO LANCA CHAMAS ===");
        RoboLancaChamas chama = new RoboLancaChamas("Chamas", "L", 100, 10, 10, 3, ambiente);
        Robo dummy = new RoboTanque("Dummy", "S", 100, 11, 10, 2, ambiente);
        ambiente.adicionarRobo(chama);
        ambiente.adicionarRobo(dummy);

        chama.lancarChamas(dummy); // sucesso
        chama.recarregarCombustivel(); // falha: cheio
        for (int i = 0; i < 20; i++) {
            chama.lancarChamas(dummy); // gasta combustível
        }
        chama.lancarChamas(dummy); // falha: sem combustível
        chama.recarregarCombustivel(); // sucesso

        System.out.println("\n=== TESTES: ROBO RESGATE AÉREO ===");
        RoboResgateAereo resgate = new RoboResgateAereo("Resgate", "O", 100, 15, 15, 1, 6, ambiente);
        ambiente.adicionarRobo(resgate);

        resgate.resgatarVitima(5, 5); // falha: sem modo emergência
        resgate.ativarModoEmergencia(); // sucesso
        resgate.ativarModoEmergencia(); // falha: já ativado
        for (int i = 0; i < 5; i++) {
            resgate.resgatarVitima(5, 5); // sucesso (5x)
        }
        resgate.resgatarVitima(5, 5); // falha: excede capacidade
        resgate.evacuacaoDeVitimas(0, 0); // sucesso
        resgate.evacuacaoDeVitimas(1, 1); // evacua sem vítimas

        System.out.println("\n=== TESTES: ROBO RECONHECIMENTO ===");
        RoboReconhecimento recon = new RoboReconhecimento("Recon", "S", 100, 2, 2, 2, 6, ambiente);
        ambiente.adicionarRobo(recon);

        recon.ativarModoReconhecimento(); // sucesso
        recon.ativarModoReconhecimento(); // falha: já ativado

        System.out.println("\n=== POSIÇÕES FINAIS ===");
        for (Robo r : ambiente.getRobosAtivos()) {
            r.exibirPosicao();
        }
    }
}