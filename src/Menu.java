import java.util.Scanner;

public class Menu {
    int valor;
    Scanner sc = new Scanner(System.in);
    public Menu(int valor) {
        this.valor = valor;
    }
    public int[] CriarAmbiente(){
        System.out.println("Qual será seu X, Y e Z do ambiente?");

        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();
        return new int[]{x, y, z};
    }

    public boolean escolha(int opcao,Ambiente ambiente){
        switch(opcao){
            case 0:
                //chamar função sair
                return sair();
            case 1:
                //função criae robo
                criarRobo(ambiente);
                break;

            case 2:
                //chamar função cria sensor
                criaSensor(ambiente);
                break;

            case 3:
                if(ambiente.getRobosAtivos().size()>0) {
                    ClasseEscolher classeEsc = new ClasseEscolher(ambiente);
                    classeEsc.iniciar();
                }
                else{
                    System.out.println("Não existem robos ativos!");
                }
                break;
                //chamar função escolhe robo
            case 4:
                //chamar funnção que exclui robo
                excluiRobo(ambiente);
                break;



        }
        return true;
    }

    public boolean sair(){
        return false;
    }
    public void criarRobo(Ambiente ambiente){
        System.out.println("Qual tipo de robo?");
        System.out.println(" 1 - Lança chamas\n 2 - Reconhecimento\n 3 - Tanque\n 4 - Resgate aéreo");
        ClasseCriadora classeCriadora = new ClasseCriadora();
        int tipo = sc.nextInt();
        switch(tipo){

            case 1:
                classeCriadora.criarRoboLC(ambiente);
                break;
                //robo lança-chamas
            case 2:
                classeCriadora.criarRoboRec(ambiente);
                break;
                //robo reconhecimento
            case 3:
                classeCriadora.criarRoboTanque(ambiente);
                break;
                //robo tanque
            case 4:
                classeCriadora.criarRoboRA(ambiente);
                break;
                //robo resgate aereo


        }
    }
    public void excluiRobo(Ambiente ambiente) {
        ClasseEscolher classeE = new ClasseEscolher(ambiente);
        System.out.println("Escolha um para ser excluido");
        classeE.exibe();
        Robo robo = classeE.escolheUm();
        ambiente.removerRobo(robo);
    }
    private void criaSensor(Ambiente ambiente){
        ClasseEscolher classe = new ClasseEscolher(ambiente);
        System.out.println("Qual o raio do Sensor?");
        int raio = sc.nextInt();
        System.out.println("Qual tipo de sensor?\n 1 - Eletromag\n 2 - Proximidade");
        int opcao = sc.nextInt();
        classe.exibe();
        Robo robo = classe.escolheUm();
        switch(opcao){
            case 1:
                SensorEletromagnetico sensorEletromagnetico = new SensorEletromagnetico(raio,ambiente);
                robo.adicionarSensor(sensorEletromagnetico);
                break;
            case 2:
                SensorProximidade sensorProximidade = new SensorProximidade(raio,ambiente);
                robo.adicionarSensor(sensorProximidade);
                break;
        }

    }

}


