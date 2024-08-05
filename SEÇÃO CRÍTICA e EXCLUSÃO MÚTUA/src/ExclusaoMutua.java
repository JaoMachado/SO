import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExclusaoMutua {
    static final Lock lock = new ReentrantLock();
    static final String NOME_ARQUIVO = "secaocritica.txt";
    
    public static void main(String[] args) {
        Thread t1 = new Thread(new EscritaArquivo(1));
        Thread t2 = new Thread(new EscritaArquivo(2));
        Thread t3 = new Thread(new EscritaArquivo(3));
        
        t1.start();
        t2.start();
        t3.start();
    }
}