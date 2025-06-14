package Robos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String caminhoDoArquivo;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Logger(String nomeArquivo) {
        this.caminhoDoArquivo = nomeArquivo;
        // Limpa o arquivo antigo ao iniciar um novo log
        try (PrintWriter writer = new PrintWriter(caminhoDoArquivo)) {
            writer.print("");
        } catch (IOException e) {
            System.err.println("Erro ao limpar o arquivo de log: " + e.getMessage());
        }
    }

    public void registrar(String mensagem) {
        // O 'true' no FileWriter indica que vamos adicionar ao final do arquivo (append)
        try (FileWriter fileWriter = new FileWriter(caminhoDoArquivo, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            String dataHora = dtf.format(LocalDateTime.now());
            printWriter.println("[" + dataHora + "] " + mensagem);

        } catch (IOException e) {
            System.err.println("Erro ao registrar no log: " + e.getMessage());
        }
    }
}
