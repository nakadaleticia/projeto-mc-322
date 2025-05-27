// Classe CentralComunicacao: responsável por armazenar e exibir o histórico de mensagens entre robôs

import java.util.ArrayList;

public class CentralComunicacao {
    private ArrayList<String> mensagens; // armazena as mensagens trocadas

    // Construtor: inicializa a lista de mensagens
    public CentralComunicacao() {
        this.mensagens = new ArrayList<>();
    }

    // metodo para registrar uma nova mensagem
    public void registrarMensagem(String remetente, String msg) {
        String mensagemFormatada = "[" + remetente + "]: " + msg;
        mensagens.add(mensagemFormatada);
    }

    // metodo para exibir todas as mensagens registradas
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
