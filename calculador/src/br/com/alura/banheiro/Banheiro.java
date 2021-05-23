package br.com.alura.banheiro;

public class Banheiro {

    private boolean ehSujo = true;

    public void fazNumero1() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " bate na porta do banheiro");

        synchronized (this) {
            System.out.println(nome + " entrando no banheiro");

            while (ehSujo) {
                esperaLaFora(nome);
            }
            System.out.println(nome + " fazendo coisa rapida");

            dormeUmPouco(8000);
            this.ehSujo = true;

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando a mao");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    public void fazNumero2() {

        String nome = Thread.currentThread().getName();

        System.out.println(nome + " bate na porta do banheiro");

        synchronized (this) {

            System.out.println(nome + " entrando no banheiro");

            while (ehSujo) {
                esperaLaFora(nome);
            }

            System.out.println(nome + " fazendo coisa demorada");

            dormeUmPouco(15000);

            this.ehSujo = true;

            System.out.println(nome + " dando descarga");
            System.out.println(nome + " lavando a mao");
            System.out.println(nome + " saindo do banheiro");
        }
    }

    public void limpa() {
        String nome = Thread.currentThread().getName();

        System.out.println(nome + " bate na porta do banheiro");

        synchronized (this) {
            System.out.println(nome + " limpando banheiro");
            if (!ehSujo) {
                System.out.println(nome + ", não está sujo, vou sair");
                return;
            }
            this.ehSujo = false;
            dormeUmPouco(13000);

            this.notifyAll();
            System.out.println(nome + " saindo do banheiro");
        }
    }

    private void dormeUmPouco(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void esperaLaFora(String nome) {
        System.out.println(nome + " eca, banheiro tá sujo");
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
