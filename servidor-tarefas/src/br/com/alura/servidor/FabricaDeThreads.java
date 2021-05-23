package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

    private static int numero = 1;
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "Thread servidor tarefas " + numero);
        numero++;
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        return thread;
    }
}
