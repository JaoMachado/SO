package Threads;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MyThread extends Thread {

    final static long NUMERO_TOTAL = 1_000_000_000L;

    private long numeros;

    public MyThread(long qtd_numeros) {
        numeros = qtd_numeros;
    }

    public static void main(String[] args) throws Exception {
        long tempo = System.currentTimeMillis();
        int qtdThreads = args.length > 0 ? Integer.parseInt(args[0]) : 1;

        ArrayList <MyThread> lista = new ArrayList<MyThread>();

        for(int n = 0; n < qtdThreads ; n++){
            lista.add(new MyThread(NUMERO_TOTAL / qtdThreads));
            lista.get(lista.size()-1).start();
        }

        boolean continuar = true;
        
        while (continuar) {
            int contagem = 0;
            for(int n = 0; n < qtdThreads; n++){
                if(!lista.get(n).isAlive()){
                    contagem++;
                }
            }

            if(contagem == qtdThreads){
                continuar = false;
            }
                
        }

        System.out.println("Tempo final: " + ((System.currentTimeMillis() - tempo)) / 1000.0);
    }

    public void run(){
        Random rn = new Random();
        long soma = 0;

        for(int i = 0; i < numeros; i++){
            soma += Math.pow(rn.nextDouble(), 10);
        }
    }
}

