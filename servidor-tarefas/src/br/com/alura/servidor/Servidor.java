package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Servidor {

    private final ServerSocket serverSocket;
    private final ExecutorService poolDeThreads;
    private AtomicBoolean estaRodando;
    private BlockingQueue<String> fila;


    public Servidor() throws IOException {
        System.out.println("----Iniciando servidor----");
        this.serverSocket = new ServerSocket(12345);
        this.poolDeThreads = Executors.newCachedThreadPool(new FabricaDeThreads());//newFixedThreadPool(4, new FabricaDeThreads());
        this.estaRodando = new AtomicBoolean(true);
        this.fila = new ArrayBlockingQueue<>(2);
        iniciarConsumidores();
    }

    private void iniciarConsumidores() {
        for (int i = 0; i < 2; i++) {
           poolDeThreads.execute(new ConsumidorTarefa(fila));
        }
    }

    public static void main(String[] args) throws Exception {

        Servidor servidor = new Servidor();
        servidor.rodar();
        servidor.parar();

    }

    public void parar() throws IOException {
        estaRodando.set(false);
        serverSocket.close();
        poolDeThreads.shutdown();
    }

    public void rodar() throws IOException {
        while (this.estaRodando.get()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Aceitando novo cliente na porta " + socket.getPort());
                poolDeThreads.execute(new DistribuirTarefa(poolDeThreads, fila, socket, this));
                // Thread thread = new Thread(new DistribuirTarefa(socket));
                // thread.start();
            } catch(SocketException e) {
                System.out.println("Socket exception");
            }
        }
    }
}
