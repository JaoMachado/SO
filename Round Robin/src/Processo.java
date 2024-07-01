import java.util.ArrayList;
import java.util.List;

public class Processo {
    private String id;
    private List<Character> instrucoes;

    public Processo(String id, String instrucoes) {
        this.id = id;
        this.instrucoes = new ArrayList<>();
        for (char c : instrucoes.toCharArray()) {
            this.instrucoes.add(c);
        }
    }

    public String getId() {
        return id;
    }

    public List<Character> getInstrucoes() {
        return instrucoes;
    }
}
