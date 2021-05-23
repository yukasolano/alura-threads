package br.com.alura.servidor;

import java.io.PrintStream;

public class ComandoC1 implements Runnable {

    private PrintStream saidaCliente;
    public ComandoC1(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public void run() {

        System.out.println("Execuntando C1");

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saidaCliente.println("Comando C1 finalizado com sucesso");
    }
}
