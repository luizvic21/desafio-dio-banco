public class ContaPoupanca extends Conta implements IContaPoupanca {

    public ContaPoupanca(Cliente cliente, Banco banco) {
        super(cliente, banco);
    }

    @Override
    public void gerarRendimento() {
        double rendimento = this.saldo * 0.02;
        this.saldo = this.saldo + rendimento;
        this.movimentar("Redimento de conta", rendimento);
    }
}
