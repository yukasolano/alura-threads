package br.com.alura.lista;

import java.util.List;

public class TarefaAdicionaElemento implements Runnable {

    private Lista lista;
    private int index;

    public TarefaAdicionaElemento(Lista lista, int index) {
        this.lista = lista;
        this.index = index;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++) {
            lista.add("Thread " + index + " - " + i);
        }

    }
}
