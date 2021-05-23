
package br.com.alura.threads;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcaoBotao implements ActionListener {

    private JTextField primeiro;
    private JTextField segundo;
    private JLabel resultado;

    public AcaoBotao(JTextField primeiro,
                     JTextField segundo,
                     JLabel resultado) {
        this.primeiro = primeiro;
        this.segundo = segundo;
        this.resultado = resultado;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        TarefaCalculador tarefa = new TarefaCalculador(primeiro, segundo, resultado);
        Thread threadCalculador = new Thread(tarefa, "Thread Calculador");
        threadCalculador.start();

        ImprimeString tarefaImprime = new ImprimeString();
        Thread threadImprime = new Thread(tarefaImprime,  "Thread imprime");
        threadImprime.start();


    }

}