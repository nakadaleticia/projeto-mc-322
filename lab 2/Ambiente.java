import java.util.ArrayList;

public class Ambiente {
    int largura;
    int altura;
    ArrayList<Robo> robosAtivos = new ArrayList<>();

    public Ambiente(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public boolean dentroDosLimites(int x, int y) {
        return (0 <= x && x <= largura && 0 <= y && y <= altura);
    }

    public void adicionarRobo(Robo r) {
        r.setAmbiente(this); // para verificação dos limites
        robosAtivos.add(r); // adiciona robô à lista de robôs ativos
        System.out.println("robô " + r.nome + " adicionado ao ambiente em (" + r.posicaoX + ", " + r.posicaoY + ") " + r.direcao);
    }

    public ArrayList<Robo> encontrarRobosAtivos() {
        return robosAtivos; // retorna lista com todos os robôs ativos
    }

    // metodo para verificar se há outro robô na posição final após se mover
    public boolean posicaoOcupada(int x, int y, int altitude, Robo solicitante) {
        for (Robo r : robosAtivos) {
            if (r != solicitante && r.posicaoX == solicitante.posicaoX && r.posicaoY == solicitante.posicaoY) {
                if (r instanceof RoboAereo && solicitante instanceof RoboAereo) {
                    RoboAereo rAereo = (RoboAereo) r;
                    if (rAereo.altitude == altitude) return true;
                } else if (!(r instanceof RoboAereo) && !(solicitante instanceof RoboAereo)) {
                    return true;
                }
            }
        }
        return false;
    }
}
