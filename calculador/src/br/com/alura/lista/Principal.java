package br.com.alura.lista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        Lista lista = new Lista();
        //List<String> lista = Collections.synchronizedList(new ArrayList<>());
        //List<String> lista = new Vector<>();

        for (int i = 0; i < 10; i++) {
            new Thread(new TarefaAdicionaElemento(lista, i)).start();
        }

        Thread.sleep(2000);

        //for (String s : lista) {
        //    System.out.println(s);
        //}

        new Thread(new TarefaImprimir(lista)).start();
       // for (int i= 0; i < lista.size(); i++ ) {
       //     System.out.println(lista.pegaElemento(i));
       // }

    }
}
