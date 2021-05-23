package br.com.alura.banheiro;

public class Limpeza implements Runnable {

    private Banheiro banheiro;

    public Limpeza(Banheiro banheiro) {
        this.banheiro = banheiro;
    }

    @Override
    public void run() {
        while (true) {
            this.banheiro.limpa();
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
