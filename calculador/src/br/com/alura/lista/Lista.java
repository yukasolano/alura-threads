package br.com.alura.lista;


public class Lista {
    private String[] elementos = new String[1000];
    private int indice = 0;

    public synchronized void add(String elemento) {
        this.elementos[indice] = elemento;
        this.indice++;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (this.indice == this.size()) {
            System.out.println("lista está cheia, notificando");
            this.notify();
        }
    }

    public int size() {
        return this.elementos.length;
    }

    public String pegaElemento(int posicao) {
        return this.elementos[posicao];
    }

    public boolean estaCheia() {
        return this.indice == this.size();
    }
}
