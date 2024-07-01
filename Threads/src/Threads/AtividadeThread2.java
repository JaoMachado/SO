package Threads;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Deposito {
    private final Lock lock = new ReentrantLock();
    private final Condition condicao = lock.newCondition();
    private boolean emUso = false;

    public void solicitarAcesso() {
        lock.lock();
        try {
            while (emUso) {
                condicao.await();
            }
            emUso = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void liberarAcesso() {
        lock.lock();
        try {
            emUso = false;
            condicao.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Fornecedor implements Runnable {
    private final Deposito deposito;
    private final Random random;

    public Fornecedor(Deposito deposito) {
        this.deposito = deposito;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int tempoProducao = random.nextInt(10) + 1;
            int tempoEntrega = random.nextInt(10) + 1;
            int loja = random.nextInt(2) + 1;

            try {
                Thread.sleep(tempoProducao * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Fornecedor finalizou a produção para Loja " + loja);

            deposito.solicitarAcesso();
            System.out.println("Fornecedor entrou para desembarcar o item no depósito");

            try {
                Thread.sleep(5000); // Tempo de desembarque
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Fornecedor finalizou o desembarque do item no depósito");
            deposito.liberarAcesso();

            try {
                Thread.sleep(tempoEntrega * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Loja implements Runnable {
    private final int id;
    private final Deposito deposito;
    private final Random random;

    public Loja(int id, Deposito deposito) {
        this.id = id;
        this.deposito = deposito;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            int tempoSolicitacao = random.nextInt(15) + 5;

            System.out.println("Solicitação da Loja " + id + " para Fornecedor");

            try {
                Thread.sleep(tempoSolicitacao * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            deposito.solicitarAcesso();
            System.out.println("Loja " + id + " carregando item no Depósito");

            try {
                Thread.sleep(5000); // Tempo de carga
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Loja " + id + " finalizou a carga no Depósito");
            deposito.liberarAcesso();
        }
    }
}

public class AtividadeThread2 {
    public static void main(String[] args) {
        Deposito deposito = new Deposito();
        Thread fornecedorThread = new Thread(new Fornecedor(deposito));
        Thread loja1Thread = new Thread(new Loja(1, deposito));
        Thread loja2Thread = new Thread(new Loja(2, deposito));

        fornecedorThread.start();
        loja1Thread.start();
        loja2Thread.start();
    }
}
