package missaoP;

import Exception.RoboDesligadoException;
import Exception.ErroComunicacaoException;
import Interfaces.Missao;
import Robos.AgenteInteligente;
import Interfaces.Comunicavel;
import Interfaces.Sensoreavel;
import AmbienteP.Ambiente;

public class MissaoMonitorar implements Missao {
    private int turnosMonitorando = 0;

    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente) {
        turnosMonitorando++;
        System.out.println(robo.getNome() + " está monitorando a área. Turno de monitoramento: " + turnosMonitorando);

        // Verifica se o robô tem a capacidade de usar sensores
        if (robo instanceof Sensoreavel) {
            // Converte (cast) para o tipo da interface e chama o método
            try {
                ((Sensoreavel) robo).acionarSensores();
            }
            catch (RoboDesligadoException e) {
                System.out.println("Robo Desligado");
            }
        } else {
            System.out.println(robo.getNome() + " não possui sensores para monitorar.");
        }

        // A cada 5 turnos, envia uma mensagem de status se for capaz
        if (turnosMonitorando % 5 == 0 && robo instanceof Comunicavel) {
            System.out.println(robo.getNome() + " enviando relatório de status...");
            try {
                ((Comunicavel) robo).enviarMensagemPara((Comunicavel) robo, "Tudo limpo na minha posição.");
            }
            catch (RoboDesligadoException e) {
                System.out.println("Robo Desligado");
            }
            catch (ErroComunicacaoException e) {
                System.out.println("Erro comunicacao");
            }
        }

    }
}