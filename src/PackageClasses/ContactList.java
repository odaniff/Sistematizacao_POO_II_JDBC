package PackageClasses;

public class ContactList {
	
	private Node head;
	
	public ContactList() {
		this.head = null;
	}
	
	public void addContact(Contact contato) {
		Node novo_no = new Node(contato);
		if(head == null) {
			head = novo_no;
		} else {
			Node no_atual = head;
			while(no_atual.proximo != null) {
				no_atual = no_atual.proximo;
			}
			no_atual.proximo = novo_no;
		}
	}
	
	public Contact searchContact(String nameOuPhone) {
		Node no_atual = head;
		while(no_atual != null) {
			if(no_atual.contato.getName().equalsIgnoreCase(nameOuPhone) || no_atual.contato.getPhoneNumber().equalsIgnoreCase(nameOuPhone)) {
				return no_atual.contato;
			} else {
				no_atual = no_atual.proximo;
			}
		}
		return null;
	}
	
	public boolean removeContact(String nameOuPhone) {
		if(head == null) {
			return false;
		}
		
		if(head.contato.getName().equalsIgnoreCase(nameOuPhone) || head.contato.getPhoneNumber().equalsIgnoreCase(nameOuPhone)) {
			head = head.proximo;
			return true;
		}
		
		Node no_atual = head;
		while(no_atual.proximo != null) {
			if(no_atual.proximo.contato.getName().equalsIgnoreCase(nameOuPhone) || no_atual.proximo.contato.getName().equalsIgnoreCase(nameOuPhone)) {
				no_atual.proximo = no_atual.proximo.proximo;
				return true;
			}
			no_atual = no_atual.proximo;
		}
		return false;
	}
	
	public void listContacts() {
		Node no_atual = head;
		if(no_atual == null) {
			System.out.println("Nenhum contato encontrado no Terminal, sua lista está vazia.");
			return;
		} 
		while(no_atual != null) {
			System.out.println(no_atual.contato);
			no_atual = no_atual.proximo;
		}
	}
}
