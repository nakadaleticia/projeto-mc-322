import java.util.ArrayList;
import java.util.Scanner;

public class ClasseEscolher {
    ArrayList<Entidade> entidades;
    Ambiente ambiente;

    public ClasseEscolher(Ambiente ambiente) {
        this.entidades = ambiente.getEntidades();
        this.ambiente = ambiente;
    }

    public void iniciar() {
        exibe();
        verClasse();
    }

    public void exibe() {
        int cont = 0;
        for (Entidade entidade : entidades) {
            if (entidade instanceof Robo robo) {
                cont++;
                System.out.println(cont + " - " + robo.getNome());
            }
        }
    }

    public Robo escolheUm() {
        System.out.println("Escolha um robo");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cont = 0;
        for (Entidade entidade : entidades) {
            if (entidade instanceof Robo robo) {
                cont++;
                if (cont == num) {
                    return robo;
                }
            }
        }
        return null; // se não encontrou
    }

    private void verClasse() {
        Robo robo = escolheUm();

        if (robo != null) {
            System.out.println("Voce escolheu o " + robo.getNome() + "\n");
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
