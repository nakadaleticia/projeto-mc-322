import java.util.ArrayList;

public class CentralComunicacao {
    private ArrayList<String> mensagens;

    public CentralComunicacao() {
        this.mensagens = new ArrayList<>();
    }

    public void registrarMensagem(String remetente, String msg) {
        String mensagemFormatada = "[" + remetente + "]: " + msg;
        mensagens.add(mensagemFormatada);
    }

    public void exibirMensagens() {
        if (mensagens.isEmpty()) {
            System.out.println("Nenhuma mensagem registrada até o momento.");
        } else {
            System.out.println("Histórico de mensagens:");
            for (String mensagem : mensagens) {
                System.out.println(mensagem);
            }
        }
    }
}
