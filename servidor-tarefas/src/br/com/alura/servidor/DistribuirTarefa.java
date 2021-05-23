package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefa implements Runnable {


    private final ExecutorService poolDeThread;
    private final Servidor servidor;
    private final Socket socket;
    private final BlockingQueue<String> filaComandos;


    public DistribuirTarefa(ExecutorService poolDeThread, BlockingQueue<String> filaComandos, Socket socket, Servidor servidor) {
        this.poolDeThread = poolDeThread;
        this.socket = socket;
        this.servidor = servidor;
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {
        System.out.println("Aceitando socket " + socket.getPort());
        try {
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();

                System.out.println("Comando recebido:" + comando);
                switch (comando) {
                    case "c1": {
                        saidaCliente.println("Confirmação comando c1");
                        this.poolDeThread.execute(new ComandoC1(saidaCliente));
                        break;
                    }
                    case "c2": {
                        saidaCliente.println("Confirmação comando c2");
                        Future<String> futureChamaWS = this.poolDeThread.submit(new ComandoC2ChamaWS(saidaCliente));
                        Future<String> futureAcessaDB = this.poolDeThread.submit(new ComandoC2AcessaBD(saidaCliente));
                        this.poolDeThread.submit(new JuntaResultadosFutureWSeAcessoDB(futureChamaWS, futureAcessaDB, saidaCliente));
                        break;
                    }
                    case "c3": {
                        saidaCliente.println("Confirmação comando c3");
                        filaComandos.put(comando);
                        saidaCliente.println("Comando adicionado na fila");
                        break;
                    }
                    case "fim": {
                        saidaCliente.println("Desligando o servidor");
                        servidor.parar();
                        break;
                    }
                    default: {
                        saidaCliente.println("Comando não encontrado");
                    }
                }
            }
            saidaCliente.close();
            entradaCliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finalizando socket " + socket.getPort());
    }
}
