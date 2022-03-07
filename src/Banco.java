import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    @Getter
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public Conta abrirContaCorrente(Cliente cliente) {
        Conta conta = new ContaCorrente(cliente, this);
        contas.add(conta);
        return conta;
    }

    public Conta abrirContaPoupanca(Cliente cliente) {
        Conta conta = new ContaPoupanca(cliente, this);
        contas.add(conta);
        return conta;
    }
}
