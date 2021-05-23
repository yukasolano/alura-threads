package br.com.alura.servidor;

import java.util.concurrent.BlockingQueue;

public class ConsumidorTarefa implements Runnable {

    BlockingQueue<String> filaComandos;

    public ConsumidorTarefa(BlockingQueue<String> fila) {
        this.filaComandos = fila;
    }

    @Override
    public void run() {
        try {

            String comando = null;
            while((comando = filaComandos.take()) != null) {
                System.out.println("Consumindo comando " + comando + ", " + Thread.currentThread().getName());
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            throw  new RuntimeException(e);
        }
    }
}
