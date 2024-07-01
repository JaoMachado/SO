import java.util.Queue;

public class Escalonador {
    private Despachante despachante;
    private int quantum;
    private static final int TEMPO_A = 5;
    private static final int TEMPO_B = 7;
    private static final int TEMPO_C = 9;
    private static final int TEMPO_D = 10;

    public Escalonador(int quantum, Queue<Processo> processosProntos) {
        this.despachante = new Despachante(processosProntos);
        this.quantum = quantum;
    }

    public void executar() {
        Processo processoAtual;
        while ((processoAtual = despachante.proximoProcesso()) != null) {
            System.out.println("5.2 - Entrada de processo no processador: " + processoAtual.getId());
    
            int tempoTotalProcesso = 0; // Variável para armazenar o tempo total de execução do processo
    
            while (!processoAtual.getInstrucoes().isEmpty()) {
                char instrucao = processoAtual.getInstrucoes().remove(0);
                int tempoInstrucao = obterTempoInstrucao(instrucao);
    
                // Processamento da instrução
                System.out.println("Processando instrução " + instrucao + " por " + tempoInstrucao + " segundos.");
                simularTempo(tempoInstrucao);
                tempoTotalProcesso += tempoInstrucao;
    
                if (tempoTotalProcesso >= quantum) {
                    // Quantum esgotado, coloca o processo de volta no despachante
                    System.out.println("Quantum esgotado para o processo: " + processoAtual.getId());
                    despachante.adicionarProcesso(processoAtual);
                    break; // Sai do loop interno ao esgotar o quantum
                }
            }
    
            // Verifica se todas as instruções do processo foram processadas
            if (processoAtual.getInstrucoes().isEmpty()) {
                System.out.println("5.4 - Finalização de processo " + processoAtual.getId() + " e tempo total de execução: " + tempoTotalProcesso + " segundos.");
                System.out.println("5.3 - Saída de processo do processador: " + processoAtual.getId());
            } else {
                // Caso o processo não tenha terminado, adiciona de volta ao despachante
                despachante.adicionarProcesso(processoAtual);
            }
        }
    }


    private int obterTempoInstrucao(char instrucao) {
        switch (instrucao) {
            case 'A':
                return TEMPO_A;
            case 'B':
                return TEMPO_B;
            case 'C':
                return TEMPO_C;
            case 'D':
                return TEMPO_D;
            default:
                return 0; // Tratar caso de instrução inválida, se necessário
        }
    }

    private void simularTempo(int segundos) {
        try {
            Thread.sleep(segundos * 1000); // Converte segundos para milissegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
