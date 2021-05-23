package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;


public class ComandoC2AcessaBD implements Callable<String> {

    private PrintStream saidaCliente;

    public ComandoC2AcessaBD(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String call() throws Exception{

        System.out.println("Recebeu C2 - Acesso BD");
        saidaCliente.println("Processando C2 - Acesso BD");

        Thread.sleep(15000);


        System.out.println("Comando C2 - Acesso BD finalizado com sucesso");
        return Integer.toString(new Random().nextInt(100));
    }
}