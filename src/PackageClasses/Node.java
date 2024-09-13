package PackageClasses;

public final class Node {
	Contact contato; 
	Node proximo;
	
	public Node(Contact contato) {
		this.contato = contato;
		this.proximo = null;
	}
}
