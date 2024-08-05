import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class EscritaArquivo implements Runnable {
    private final int idThread;
    private int contadorEscrita = 0;
    private final Random random = new Random();

    public EscritaArquivo(int idThread) {
        this.idThread = idThread;
    }

    @Override
    public void run() {
        while (contadorEscrita < 10) {
            try {
                // Espera entre 5 a 10 segundos antes de tentar acessar o arquivo
                Thread.sleep((random.nextInt(6) + 5) * 1000);
                
                // Tentativa de adquirir o lock
                if (ExclusaoMutua.lock.tryLock()) {
                    try {
                        System.out.println("Thread " + idThread + " abriu o arquivo");

                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ExclusaoMutua.NOME_ARQUIVO, true))) {
                            writer.write("Thread " + idThread + ": Primeira linha escrita\n");
                            writer.flush();
                            Thread.sleep(1000);
                            System.out.println("Thread " + idThread + " escreveu em arquivo");
                            
                            writer.write("Thread " + idThread + ": Segunda linha escrita\n");
                            writer.flush();
                            Thread.sleep(1000);
                            System.out.println("Thread " + idThread + " escreveu em arquivo");
                            
                            writer.write("Thread " + idThread + ": Terceira linha escrita\n");
                            writer.flush();
                            Thread.sleep(1000);
                            System.out.println("Thread " + idThread + " escreveu em arquivo");
                        }

                        System.out.println("Thread " + idThread + " fechou o arquivo");
                        contadorEscrita++;
                    } finally {
                        ExclusaoMutua.lock.unlock();
                    }
                } else {
                    System.out.println("Thread " + idThread + " tentou acessar o arquivo sem sucesso");
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
