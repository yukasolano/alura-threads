package br.com.alura.servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Conexão estabelida");


        Thread threadEnviaComando = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Pode enviar comandos");
                    PrintStream saida = new PrintStream(socket.getOutputStream());
                    Scanner scanner = new Scanner(System.in);
                    while (scanner.hasNextLine()) {

                        String line = scanner.nextLine();
                        if (line.trim().equals("")) {
                            break;
                        }
                        saida.println(line);

                    }
                    saida.close();
                    scanner.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadRecebeResposta = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("Recebendo dados do servidor");
                    Scanner respostaServidor = new Scanner(socket.getInputStream());
                    while(respostaServidor.hasNextLine()) {
                        String line = respostaServidor.nextLine();
                        System.out.println(line);
                    }
                    respostaServidor.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadEnviaComando.start();
        threadRecebeResposta.start();

        threadEnviaComando.join(); //significa thread main esperar a enviaComnando terminar parapoder finalizar

        System.out.println("Fechando socket do cliente");
        socket.close();
    }
}
