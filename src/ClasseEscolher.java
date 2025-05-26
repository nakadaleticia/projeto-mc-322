import java.util.ArrayList;
import java.util.Scanner;

public class ClasseEscolher {
    ArrayList<Robo> robosAtivos;
    Ambiente ambiente;
    public ClasseEscolher(Ambiente ambiente) {
        robosAtivos = ambiente.getRobosAtivos();
        this.ambiente = ambiente;
    }
    public void iniciar() {
        exibe();
        verClasse();
    }
    public void exibe() {
        int cont = 0;
        for (Robo robo : robosAtivos) {
            cont ++;
            System.out.println(cont + " - "+  robo.nome);

        }
    }
    public Robo escolheUm() {
        System.out.println("Escolha um robo");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        return robosAtivos.get(num-1);
    }
    private void verClasse () {
        Robo robo = escolheUm();

        if (robo != null) {
            System.out.println("Voce escolheu o "+ robo.nome + "\n");
            switch (robo) {
                case RoboLancaChamas roboLanca -> {
                    ControladorLC controladorLC = new ControladorLC(roboLanca, ambiente);
                    controladorLC.iniciar();
                }
                case RoboReconhecimento roboReconhecimento -> {
                    ControladorReconhecimento controladorReconhecimento = new ControladorReconhecimento(roboReconhecimento);
                    controladorReconhecimento.iniciar();
                }
                case RoboTanque roboTanque -> {
                    ControladorTanque controladorTanque = new ControladorTanque(roboTanque, ambiente);
                    controladorTanque.iniciar();
                }
                case RoboResgateAereo roboResgateAereo -> {
                    ControladorRA controladorRA = new ControladorRA(roboResgateAereo);
                    controladorRA.iniciar();
                }
                default -> {
                }
            }

        }
    }
}
