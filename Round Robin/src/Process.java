import java.util.ArrayList;
import java.util.List;

public class Process {
    public String state;
    public int time;
    public List<Character> instructions;
    public int executionTime;

    public Process(int time, List<Character> instructions) {
        this.state = "new";
        this.time = time;
        this.instructions = new ArrayList<>(instructions);
        this.executionTime = 0;
    }
}
