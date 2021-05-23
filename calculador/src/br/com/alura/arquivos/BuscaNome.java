package br.com.alura.arquivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BuscaNome implements Runnable {

    private String nomeArquivo;
    private String nome;

    public BuscaNome(String nomeArquivo,
                     String nome) {
        this.nomeArquivo = nomeArquivo;
        this.nome = nome;
    }


    @Override
    public void run() {

        try {
            Scanner scanner = new Scanner(new File(nomeArquivo));
            int numeroLinha = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(nome.toLowerCase())) {
                    System.out.println(String.format("%s - %d - %s - id: %d", nomeArquivo, numeroLinha, line, Thread.currentThread().getId()));
                }
                numeroLinha++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
