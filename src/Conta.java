import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    private Cliente cliente;
    private Banco banco;
    private int agencia;
    private int numero;
    protected double saldo;
    private List<Movimentacao> movimentacoes;

    public Conta(Cliente cliente, Banco banco) {
        this.cliente = cliente;
        this.banco = banco;
        this.agencia = this.AGENCIA_PADRAO;
        this.numero = this.SEQUENCIAL++;
        this.movimentacoes = new ArrayList<>();
        this.saldo = 0;
    }

    @Override
    public void sacar(double valor) {
        if (valor < this.saldo) {
            this.saldo = this.saldo - valor;
            this.movimentar("Saque", valor);
        }
    }

    @Override
    public void depositar(double valor) {
        this.saldo = this.saldo + valor;
        this.movimentar("Deposito", valor);
    }

    @Override
    public void tranferir(Conta contaDestino, double valor) {
        if (valor < this.saldo) {
            this.saldo = this.saldo - valor;
            contaDestino.depositar(valor);
            String descricaoMovimento = String.format("Transferencia para: %s, Banco: %s", contaDestino.cliente.getNome(), contaDestino.banco.getNome());
            contaDestino.receberTransferencia(this, valor);
            this.movimentar(descricaoMovimento, valor);
        }
    }

    @Override
    public String gerarExtrato() {
        String movimentacoes = this.movimentacoes.stream().map(movimentacao -> String.format("%s\t\t\tR$%.2f\n\r", movimentacao.getDescricao(), movimentacao.getValor())).collect(Collectors.joining());
        String extrato;
        extrato = String.format("=== %s ===\n\rAgência: %d\n\rNúmero: %d\n\rNome: %s\n\rSaldo: R$%.2f\n\r--------------------------------\n\r%s"
                , this.banco.getNome(), this.agencia, this.numero, this.cliente.getNome(), this.saldo, movimentacoes);
        return extrato;
    }

    private void receberTransferencia(Conta contaRemetente, double valor) {
        this.saldo = this.saldo + valor;
        String descricaoMovimento = String.format("Transferencia recebida de: %s, Banco: %s", contaRemetente.cliente.getNome(), contaRemetente.banco.getNome());
        this.movimentar(descricaoMovimento, valor);
    }

    protected void movimentar(String descricao, double valor) {
        Movimentacao movimentacao = new Movimentacao(descricao, valor);
        this.movimentacoes.add(movimentacao);
    }
}
