import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scheduler {
    public static void main(String[] args) {
        Map<Character, Integer> instructionsTime = new HashMap<>();
        instructionsTime.put('A', 5);
        instructionsTime.put('B', 7);
        instructionsTime.put('C', 9);
        instructionsTime.put('D', 10);

        List<Process> processList = new ArrayList<>();
        List<Process> scheduler = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("Round Robin/src/processos.txt"))) {
            String line;
            for (int i = 0; i < 4; i++) {
                line = br.readLine();
                int time = Integer.parseInt(line.substring(0, 3));
                List<Character> instructions = new ArrayList<>();
                for (int j = 3; j < line.length(); j++) {
                    instructions.add(line.charAt(j));
                }
                processList.add(new Process(time, instructions));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double quantum = 5;
        int globalTime = 0;
        int instTime = 0;
        int processCount = 0;
        int processAmount = processList.size();

        while (processCount < processAmount) {
            globalTime++;
            for (int i = 0; i < processList.size(); i++) {
                if (processList.get(i).time == globalTime) {
                    Process p = processList.remove(i);
                    p.state = "ready";
                    scheduler.add(p);
                    System.out.println("Processo " + p.time + " pronto");
                }
            }

            if (!scheduler.isEmpty()) {
                if (scheduler.get(0).state.equals("ready")) {
                    System.out.println("Entrada de processo no processador: " + scheduler.get(0).time);
                    System.out.println("Processando instrucao " + scheduler.get(0).instructions.get(0));
                    scheduler.get(0).state = "running";
                }
                quantum++;
                instTime++;
                if (instTime == instructionsTime.get(scheduler.get(0).instructions.get(0))) {
                    scheduler.get(0).executionTime += instTime;
                    instTime = 0;
                    System.out.println("Saida do processo " + scheduler.get(0).time + " do processador.");
                    scheduler.get(0).instructions.remove(0);
                    if (scheduler.get(0).instructions.isEmpty()) {
                        scheduler.get(0).state = "terminated";
                        System.out.println("Processo " + scheduler.get(0).time + " finalizado (tempo de execucao: " + scheduler.get(0).executionTime + ")");
                        scheduler.remove(0);
                        processCount++;
                    } else if (quantum >= 5) {
                        quantum = 0;
                        scheduler.get(0).state = "ready";
                        scheduler.add(scheduler.remove(0));
                    }
                } 
                try {
                    // Esperar pelo tempo de execução da instrução atual
                    Thread.sleep(instructionsTime.get(scheduler.get(0).instructions.get(0)) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
