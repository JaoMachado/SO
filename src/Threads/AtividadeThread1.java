package Threads;

import java.util.ArrayList;
import java.util.Scanner;

/*Alunos: João Pedro Machado Silva
          Gabriel Cavalcante      
 */

public class AtividadeThread1 extends Thread {

    final static long NUMERO_TOTAL = 1_000_000_000L;

    private int[] linha;
    private long soma;

    public AtividadeThread1(int[] linha) {
        this.linha = linha;
        this.soma = 0;
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        long tempo = System.currentTimeMillis();

        System.out.println("Digite a quantidade de linhas: ");
        int linhas = teclado.nextInt();

        System.out.println("Digite a quantidade de colunas: ");
        int colunas = teclado.nextInt();

        int[][] array = new int[linhas][colunas];

        for(int i = 0; i < linhas; i++){
            for(int j = 0; j < colunas; j++){
                System.out.println("Digite o valor para posicao [" + i + "][" + j + "] do array");
                array[i][j] = teclado.nextInt();
            }
        }

        ArrayList <AtividadeThread1> lista = new ArrayList<AtividadeThread1>();

        for(int n = 0; n < linhas ; n++){
            lista.add(new AtividadeThread1(array[n]));
            lista.get(n).start();
        }

        boolean continuar = true;
        
        while (continuar) {
            int contagem = 0;
            for(int n = 0; n < linhas; n++){
                if(!lista.get(n).isAlive()){
                    contagem++;
                }
            }

            if(contagem == linhas){
                continuar = false;
            }
        }

        System.out.println("Soma das linhas da matriz:");
        
        for (int i = 0; i < linhas; i++) {
            System.out.println("Linha " + i + ": " + lista.get(i).getSoma());
        }

        System.out.println("Tempo final: " + ((System.currentTimeMillis() - tempo)) / 1000.0);
        System.out.println("Numero de Threads criadas: " + lista.size());
    }

    public long getSoma() {
        return soma;
    }

    public void run(){
        for(int i = 0; i < linha.length; i++){
            soma += linha[i];
        }
    }
}

