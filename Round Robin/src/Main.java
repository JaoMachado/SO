import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Processo> processosProntos = new ArrayDeque<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader("Round Robin/src/processos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String id = linha.substring(0, 3); // Assume que o ID do processo tem 3 caracteres
                String instrucoes = linha.substring(3);
    
                Processo processo = new Processo(id, instrucoes);
                processosProntos.add(processo);
                System.out.println("Entrada de processo na Lista de processos prontos: " + processo.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        Escalonador escalonador = new Escalonador(5, processosProntos); // Quantum fixo de 5 unidades
    
        escalonador.executar();
    }
    
}
