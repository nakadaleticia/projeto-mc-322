import AmbienteP.Ambiente;
import ObstaculoP.Obstaculo;
import Robos.RoboLancaChamas;
import Robos.RoboReconhecimento;
import Robos.RoboResgateAereo;
import Robos.RoboTanque;
import enums.TipoObstaculo;

public class TesteCompletoLab4 {

    public static void main(String[] args) {

        // Criar ambiente
        System.out.println("Testando criação do ambiente...");
        Ambiente ambiente = new Ambiente(10, 10, 5, null);
        System.out.println("Esperado: Ambiente.Ambiente criado 10x10x5");

        // Criar robôs
        System.out.println("\nTestando criação de robôs...");
        // Robos.RoboTanque(nome, direcao, vida, x, y, velocidadeMaxima, ambiente)
        RoboTanque tanque = new RoboTanque("Tanque", "N", 100, 1, 1, 2, ambiente);
        RoboLancaChamas chama = new RoboLancaChamas("Chamas", "L", 100, 2, 2, 3, ambiente);
        RoboResgateAereo resgate = new RoboResgateAereo("Resgate", "O", 100, 3, 3, 1, 5, ambiente);
        RoboReconhecimento recon = new RoboReconhecimento("Recon", "S", 100, 4, 4, 2, 5, ambiente);
        System.out.println("Esperado: Robôs criados e posicionados");

        // Adicionar entidades no ambiente
        ambiente.adicionarEntidade(tanque);
        ambiente.adicionarEntidade(chama);
        ambiente.adicionarEntidade(resgate);
        ambiente.adicionarEntidade(recon);
        System.out.println("Esperado: Robôs adicionados no ambiente");

        Obstaculo obstaculo = new Obstaculo(5, 6, 5, 6, 1, TipoObstaculo.PAREDE);
        ambiente.adicionarEntidade(obstaculo);
        System.out.println("Esperado: Obstáculo adicionado");

        // Testar movimentação válida
        System.out.println("\nTestando movimentação válida...");
        tanque.mover(1, 0, 0, 1); // distancia=1, tempo=1, velocidade=1 <= 2 (ok)
        System.out.println("Esperado: Movimento para (2,1,2) confirmado");
        tanque.exibirPosicao();

        // Testar movimentação fora dos limites
        System.out.println("\nTestando movimentação fora dos limites...");
        // Vamos andar 9 unidades (de 2,1 até 11,1) em 10 segundos, velocidade = 0.9 <= 2 (ok)
        try {
            tanque.mover(9, 0, 0, 10);
        } catch (Exception e) {
            System.out.println("Esperado: Erro ao mover fora dos limites: " + e.getMessage());
        }

        // Testar movimentação para posição ocupada (obstáculo)
        System.out.println("\nTestando movimentação para posição ocupada...");
        // Vamos andar de (2,1) para (5,5) em 6 segundos, distancia=5, velocidade ~0.83 <= 2 (ok)
        try {
            tanque.mover(3, 4, -1, 6); // (2+3,1+4,2-1) = (5,5,1), onde há obstáculo
        } catch (Exception e) {
            System.out.println("Esperado: Erro de colisão: " + e.getMessage());
        }

        // Testar sensores com robô ligado
        System.out.println("\nTestando sensores com robô ligado...");
        try {
            tanque.acionarSensores();
            System.out.println("Esperado: Sensores acionados com sucesso");
        } catch (Exception e) {
            System.out.println("Erro inesperado ao acionar sensores: " + e.getMessage());
        }

        // Testar sensores com robô desligado (deve lançar exceção)
        System.out.println("\nTestando sensores com robô desligado...");
        tanque.desligar();
        try {
            tanque.acionarSensores();
        } catch (Exception e) {
            System.out.println("Esperado: Exceção ao acionar sensores desligado: " + e.getMessage());
        }
        tanque.ligar();

        // Testar ataque do tanque (dano fatal)
        System.out.println("\nTestando ataque do tanque...");
        tanque.atacar(chama);
        System.out.println("Esperado: Ataque realizado, chama perdeu vida.");

        // Testar ataque do lança-chamas (20% dano)
        System.out.println("\nTestando ataque do lança-chamas...");
        chama.atacar(tanque);
        System.out.println("Esperado: Ataque realizado, tanque perdeu 20% da vida.");

        // Testar resgate do resgate aéreo
        System.out.println("\nTestando resgate...");
        resgate.ativarModoEmergencia();
        resgate.resgatarVitima(1, 1);
        System.out.println("Esperado: Vítima resgatada");

        // Testar evacuação de vítimas
        System.out.println("\nTestando evacuação...");
        resgate.evacuacaoDeVitimas(0, 0);
        System.out.println("Esperado: Vítimas evacuadas para (0,0)");

        // Testar mapeamento do reconhecimento
        System.out.println("\nTestando mapeamento...");
        recon.mapearAmbiente();
        System.out.println("Esperado: Mapeamento realizado");

        // Testar comunicação entre robôs
        System.out.println("\nTestando comunicação...");
        try {
            tanque.enviarMensagem(chama, "Alvo detectado!");
            chama.receberMensagem("Confirmado, atacando!");
            System.out.println("Esperado: Mensagens enviadas e recebidas corretamente");
        } catch (Exception e) {
            System.out.println("Erro na comunicação: " + e.getMessage());
        }

        // Testar remoção automática ao receber dano fatal
        System.out.println("\nTestando remoção automática...");
        System.out.println("Vida do dummy antes: " + tanque.getVida());
        tanque.receberDano(tanque.getVida());
        System.out.println("Esperado: Dummy destruído e removido do ambiente");

        // Testar exclusão manual
        System.out.println("\nTestando exclusão manual...");
        ambiente.removerEntidade(recon);
        System.out.println("Esperado: Recon removido do ambiente");

        // Visualizar mapa final
        System.out.println("\nMapa final do ambiente:");
        ambiente.visualizarAmbiente();

        System.out.println("\n=== Fim dos testes ===");
    }
}
