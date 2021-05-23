package br.com.alura.servidor;

public class TratadorDeExcecao implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread,
                                  Throwable throwable) {
        System.out.println("Deu excecao na thread " + thread.getName() + ", " + throwable.getMessage());
    }
}
