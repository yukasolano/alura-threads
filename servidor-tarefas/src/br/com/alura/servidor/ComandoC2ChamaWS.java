package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;


public class ComandoC2ChamaWS implements Callable<String> {

    private PrintStream saidaCliente;

    public ComandoC2ChamaWS(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String call() throws Exception{

        System.out.println("Recebeu C2 - Chama WS");
        saidaCliente.println("Processando C2 - Chama WS");

        Thread.sleep(15000);


        System.out.println("Comando C2 - Chama WS finalizado com sucesso");
        return Integer.toString(new Random().nextInt(100));
    }
}