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
        robosAtivos.add(r); // adiciona robô à lista de robôs ativos
        System.out.println("robô " + r.nome + " adicionado ao ambiente");
    }

    public ArrayList<Robo> encontrarRobosAtivos() {
        return robosAtivos; // retorna lista com todos os robôs ativos
    }
}
