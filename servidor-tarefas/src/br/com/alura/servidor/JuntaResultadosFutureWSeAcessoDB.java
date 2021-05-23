package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JuntaResultadosFutureWSeAcessoDB implements Callable<Void> {


    private Future<String> futureChamaWS;
    private Future<String> futureAcessaDB;
    private PrintStream saidaCliente;

    public JuntaResultadosFutureWSeAcessoDB(Future<String> futureChamaWS,
                                            Future<String> futureAcessaDB,
                                            PrintStream saidaCliente) {
        this.futureChamaWS = futureChamaWS;
        this.futureAcessaDB = futureAcessaDB;
        this.saidaCliente = saidaCliente;
    }

    @Override
    public Void call() {

        System.out.println("Aguardando resultados do future WS e banco");

        try {
            String numeroMagico = futureChamaWS.get(10, TimeUnit.SECONDS);
            String numeroMagico2 = futureAcessaDB.get(10, TimeUnit.SECONDS);

            this.saidaCliente.println("Resultado comando c2:" + numeroMagico + numeroMagico2);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println("Cancelando execucao do comando C2");
           this.saidaCliente.println("Timeout da execucao do comando C2");
           this.futureChamaWS.cancel(true);
           this.futureAcessaDB.cancel(true);
        }

        System.out.println("Finalizou comando C2");
        return null;
    }
}
