package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int saldo;
    protected int[] papelMoeda = {2, 5, 10, 20, 50, 100};

    // Comissão, severidade baixa: O nome da variável valor deveria ser precoDoBilhete
    public TicketMachine(int valor) {
        this.valor = valor;
        this.saldo = 0;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[1] == quantia) {  //Defeito de Dados, severidade alta: a máquina só aceita a nota de R$5,00 e ignora as demais (2, 10, 20, 50, 100). O correto seria comparar com papelMoeda[i].
                achou = true;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }
        this.saldo += quantia;
    }

    public int getSaldo() {
        return saldo;
    }

    //Defeito de Comissão, severidade alta: o método getTroco não foi desenvolvido conforme a especificação
    public Iterator<Integer> getTroco() {
        return null;
    }

    /*Defeito de Comissão, severidade alta: apesar de não estar na especificação, entende-se que, se o saldo é maior que o valor,
    deveria descontar o valor do bilhete impresso do saldo.
    */
    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        }
        String result = "*****************\n";
        result += "*** R$ " + saldo + ",00 ****\n";
        result += "*****************\n";
        return result;
    }
}


