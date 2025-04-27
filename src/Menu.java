import java.util.Scanner;

public class Menu {
    int valor;
    public Menu(int valor) {
        this.valor = valor;
    }
    public int[] CriarAmbiente(){
        System.out.println("Qual será seu X, Y e Z do ambiente?");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        return new int[]{x, y, z};
    }
    public void Escolha(int opcao){
        switch(opcao){
            case 0:
                //chamar função sair
            case 1:
                //função criae robo
            case 2:
                //chamar função cria obstáculo
            case 3:
                //chamar função cria sensor
            case 4:
                //chamar função escolhe robo
            case 5:
                //chamar funnção que exclui robo

        }
    }
    public boolean sair(){
        return false;
    }
    public void criarRobo(Ambiente ambiente){
        System.out.println("Qual tipo de robo?");
        Scanner sc = new Scanner(System.in);
        int tipo = sc.nextInt();
        switch(tipo){
            case 0:
                sair();
            case 1:
                criarRoboLC(ambiente);
                //robo lança-chamas
            case 2:
                criarRoboRec(ambiente);
                //robo reconhecimento
            case 3:
                criarRoboTanque(ambiente);
                //robo tanque
            case 4:
                criarRoboRA(ambiente);
                //robo resgate aereo


        }
    }
    private void criarRoboLC(Ambiente ambiente){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do Robo?");
        String nome = sc.next();
        System.out.println("Qual a direção do Robo?");
        String direcao = sc.next();
        System.out.println("Qual a vida do Robo?");
        int vida = sc.nextInt();
        System.out.println("Qual a posicao x do Robo?");
        int x = sc.nextInt();
        System.out.println("Qual a posicao y do Robo?");
        int y = sc.nextInt();
        System.out.println("Qual a velocidade máxima do Robo?");
        int velocidadeMaxima = sc.nextInt();
        RoboLancaChamas roboLancaChamas = new RoboLancaChamas(nome,direcao,vida,x,y,velocidadeMaxima,ambiente);
        ambiente.adicionarRobo(roboLancaChamas);


        }
    private void criarRoboRec(Ambiente ambiente) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do Robo?");
        String nome = sc.next();
        System.out.println("Qual a direção do Robo?");
        String direcao = sc.next();
        System.out.println("Qual a vida do Robo?");
        int vida = sc.nextInt();
        System.out.println("Qual a posicao x do Robo?");
        int x = sc.nextInt();
        System.out.println("Qual a posicao y do Robo?");
        int y = sc.nextInt();
        System.out.println("Qual a altitude do Robo?");
        int altitude = sc.nextInt();
        System.out.println("Qual a altitude maxima do Robo?");
        int altitudeMaxima = sc.nextInt();
        RoboReconhecimento roboReconhecimento = new RoboReconhecimento(nome, direcao, vida, x, y, altitude, altitudeMaxima, ambiente);
        ambiente.adicionarRobo(roboReconhecimento);
    }
    private void criarRoboTanque(Ambiente ambiente){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do Robo?");
        String nome = sc.next();
        System.out.println("Qual a direção do Robo?");
        String direcao = sc.next();
        System.out.println("Qual a vida do Robo?");
        int vida = sc.nextInt();
        System.out.println("Qual a posicao x do Robo?");
        int x = sc.nextInt();
        System.out.println("Qual a posicao y do Robo?");
        int y = sc.nextInt();
        System.out.println("Qual a velocidade máxima do Robo?");
        int velocidadeMaxima = sc.nextInt();
        RoboTanque robotanque = new RoboTanque(nome,direcao,vida,x,y,velocidadeMaxima,ambiente);
    }
    private void criarRoboRA(Ambiente ambiente){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual o nome do Robo?");
        String nome = sc.next();
        System.out.println("Qual a direção do Robo?");
        String direcao = sc.next();
        System.out.println("Qual a vida do Robo?");
        int vida = sc.nextInt();
        System.out.println("Qual a posicao x do Robo?");
        int x = sc.nextInt();
        System.out.println("Qual a posicao y do Robo?");
        int y = sc.nextInt();
        System.out.println("Qual a altitude do Robo?");
        int altitude = sc.nextInt();
        System.out.println("Qual a altitude maxima do Robo?");
        int altitudeMaxima = sc.nextInt();
        RoboResgateAereo RoboRA = new RoboResgateAereo(nome,direcao,vida,x,y,altitude,altitudeMaxima,ambiente);
        ambiente.adicionarRobo(RoboRA);
    }

}


