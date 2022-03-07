public class App {
    public static void main(String[] args) {
        Banco bancoInter = new Banco("Banco Inter");
        Banco nuBank = new Banco("Nu Bank");
        Cliente victor = new Cliente("Victor");
        Cliente joao = new Cliente("João");
        ContaPoupanca contaVictor = new ContaPoupanca(victor, bancoInter);
        ContaCorrente contaJoao = new ContaCorrente(joao, nuBank);

        contaVictor.depositar(1000);
        contaJoao.depositar(1000);
        contaVictor.gerarRendimento();

        contaVictor.tranferir(contaJoao, 100);
        contaJoao.tranferir(contaVictor, 300);

        contaJoao.sacar(50);
        contaJoao.sacar(25);
        contaJoao.sacar(100);
        contaJoao.sacar(200);
        contaJoao.depositar(500);

        contaVictor.sacar(20);
        contaVictor.depositar(20);
        contaVictor.sacar(100);
        contaVictor.sacar(200);
        contaVictor.depositar(500);
        contaVictor.depositar(300);
        contaVictor.gerarRendimento();

        contaVictor.tranferir(contaJoao, 200);
        contaVictor.gerarRendimento();

        System.out.println(contaVictor.gerarExtrato());
        System.out.println(contaJoao.gerarExtrato());
    }
}
