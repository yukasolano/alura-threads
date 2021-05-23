package br.com.alura.arquivos;

public class LeituraArquivos {

    public static void main(String[] args) {

        String nome = "da";


        Thread threadAss1 = new Thread(new BuscaNome("assinaturas1.txt", nome));
        Thread threadAss2 = new Thread(new BuscaNome("assinaturas2.txt", nome));
        Thread threadAuto = new Thread(new BuscaNome("autores.txt", nome));

        threadAss1.start();
        threadAss2.start();
        threadAuto.start();
    }
}
