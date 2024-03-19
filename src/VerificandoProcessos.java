public class VerificandoProcessos {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            long currentProcessId = ProcessHandle.current().pid();
            System.out.println("PID do processo atual: " + currentProcessId);
        } else {
            Process process = new ProcessBuilder(args[0]).start();
            ProcessHandle processHandle = process.toHandle();
            long processId = processHandle.pid();

            System.out.println("Nome do aplicativo: " + args[0]);
            System.out.println("PID do processo: " + processId);
        }
    }
}