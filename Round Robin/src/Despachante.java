import java.util.Queue;

public class Despachante {
    private Queue<Processo> processosProntos;

    public Despachante(Queue<Processo> processosProntos) {
        this.processosProntos = processosProntos;
    }

    public Processo proximoProcesso() {
        if (!processosProntos.isEmpty()) {
            return processosProntos.poll();
        }
        return null;
    }

    public void adicionarProcesso(Processo processo) {
        processosProntos.add(processo);
    }
}
