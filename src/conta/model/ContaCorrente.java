package conta.model;

public class ContaCorrente extends Conta {

	// Atributos
	private float limite;

	// Método Especial - Método Construtor
	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}
	
	//Métodos de Acessos
	public float getLimite() {
		return limite;
	}
	
	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	@Override
	public boolean sacar(float valor) {

		if (this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;
		}

		this.setSaldo(this.getSaldo() - valor);
		return true;
	}
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de Credito: " + this.limite);
	}
}
