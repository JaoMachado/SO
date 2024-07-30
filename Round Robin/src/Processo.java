import java.util.ArrayList;
import java.util.List;

public class Processo {
    private String id;
    private List<Character> instrucoes;
    private boolean finalizado; // Adicionado para controlar se o processo foi finalizado

    public Processo(String id, String instrucoes) {
        this.id = id;
        this.instrucoes = new ArrayList<>();
        for (char c : instrucoes.toCharArray()) {
            this.instrucoes.add(c);
        }
        this.finalizado = false; // Inicialmente, o processo não está finalizado
    }

    public String getId() {
        return id;
    }

    public List<Character> getInstrucoes() {
        return instrucoes;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
