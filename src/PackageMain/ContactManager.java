package PackageMain;

import PackageClasses.Contact;
import PackageClasses.ContactList;
/*import PackageClasses.Node;*/
import PackageConexao.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactManager {

	public static void main(String[] args) {
		 ContactList contactList = new ContactList();
		 Scanner scanner = new Scanner(System.in);
		 int choice = 0;
	
		 do {
			 System.out.println("\n||Bem-vindo(a) ao Sistema de Gerenciamento de Contatos||");
			 System.out.println("1 - Adicionar Contato.");
			 System.out.println("2 - Buscar Contato.");
			 System.out.println("3 - Remover Contato.");
			 System.out.println("4 - Listar Contatos.");
			 System.out.println("5 - Sair.");
			 System.out.print("Escolha uma Opção: ");
			 choice = scanner.nextInt();
			 scanner.nextLine();
			 
			 switch (choice) {
			 	case 1:
			 		System.out.print("\nDigite o Nome do contato: ");
					String name = scanner.nextLine();
					System.out.print("Digite o Número de Telefone do contato: ");
					String phoneNumber = scanner.nextLine();
					System.out.print("Digite o E-mail do contato: ");
					String email = scanner.nextLine();
					Contact contato_novo = new Contact(name, phoneNumber, email);
					contactList.addContact(contato_novo);
					
					String sql_add = "INSERT INTO CONTACTS (name, phonenumber, email) VALUES (?, ?, ?)";
                    PreparedStatement ps_add = null;

                    try {
                        ps_add = Conexao.getConexao().prepareStatement(sql_add);
                        ps_add.setString(1, name);
                        ps_add.setString(2, phoneNumber);
                        ps_add.setString(3, email);

                        ps_add.execute();
                        ps_add.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
					
					System.out.println("Contato adicionado com sucesso.");
			 		break;
			 		
			 	case 2:
			 		System.out.print("\nDigite o Nome ou Número de Telefone do contato que deseja buscar: ");
			 		String nameOuPhone = scanner.nextLine();
			 		if(contactList.searchContact(nameOuPhone) != null) {
			 			System.out.println("\nContato encontrado no Terminal: "); 
			 			System.out.println(contactList.searchContact(nameOuPhone)+ "\n");
			 		} else {
			 			System.out.println("\nContato não encontrado no Terminal.\n");
			 		}
			 		
                    String sql_search = "SELECT * FROM contacts WHERE name = ? OR phonenumber = ?";
                    PreparedStatement ps_search = null;
                    ResultSet rs_search = null;

                    try {    
                        ps_search = Conexao.getConexao().prepareStatement(sql_search);
                        ps_search.setString(1, nameOuPhone);
                        ps_search.setString(2, nameOuPhone);

                        rs_search = ps_search.executeQuery();
                        System.out.println("Contato encontrado na Base de dados:");
                        
                        if (rs_search.isBeforeFirst() == false) {
                            System.out.println("Nenhum Contato cadastrado na Base de dados.");
                        }
                        while (rs_search.next() == true) {    
                            System.out.println("Nome: " +rs_search.getString(1)+ 
                            ", Número de Telefone: " +rs_search.getString(2)+
                            ", E-mail: " +rs_search.getString(3));
                        }

                        ps_search.execute();
                        ps_search.close();
                        rs_search.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }  
			 		
			 		break;
			 		
			 	case 3:
			 		System.out.print("\nDigite o Nome ou Número de Telefone do contato que deseja remover: ");
			 		String nameOuPhone2 = scanner.nextLine();
			 		if(contactList.removeContact(nameOuPhone2) == true) {
			 			System.out.println("Contato removido do Terminal.\n");
			 		} else {
			 			System.out.println("Contato não encontrado no Terminal.\n");
			 		}
			 		
			 		String sql_checkRemove = "SELECT COUNT(*) FROM contacts WHERE name = ? OR phonenumber = ?";
			 		String sql_remove = "DELETE FROM contacts WHERE name = ? OR phonenumber = ?";
			 		PreparedStatement ps_checkRemove = null;
                    PreparedStatement ps_remove = null;

                    try {
						
                    	/*
						 * ps_remove = Conexao.getConexao().prepareStatement(sql_remove);
						 * ps_remove.setString(1, nameOuPhone2); ps_remove.setString(2, nameOuPhone2);
						 * 
						 * System.out.println("Contato removido da Base de Dados.");
						 * 
						 * ps_remove.execute(); ps_remove.close();
						 */
                    	
                    	ps_checkRemove = Conexao.getConexao().prepareStatement(sql_checkRemove);
                        ps_checkRemove.setString(1, nameOuPhone2);
                        ps_checkRemove.setString(2, nameOuPhone2);
                        ResultSet rs_checkRemove = ps_checkRemove.executeQuery();

                        if (rs_checkRemove.next() == true && rs_checkRemove.getInt(1) > 0) {
                            ps_remove = Conexao.getConexao().prepareStatement(sql_remove);
                            ps_remove.setString(1, nameOuPhone2);
                            ps_remove.setString(2, nameOuPhone2);
                            ps_remove.execute();
                            System.out.println("Contato removido da Base de Dados.");
                        } else {
                            System.out.println("Contato não encontrado na Base de Dados.");
                        }

                        
                        ps_checkRemove.execute();
                        ps_checkRemove.close();
                    	rs_checkRemove.close();
                    	
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
			 		
			 		break;
			 		
			 	case 4:
			 		System.out.println("\nLista de Contatos cadastrados no Terminal: ");
			 		contactList.listContacts();
			 		
			 		String sql_list = "SELECT * FROM contacts ORDER BY name ASC";
                    PreparedStatement ps_list = null;
                    ResultSet rs_list = null;

                    try {
                        ps_list = Conexao.getConexao().prepareStatement(sql_list);

                        rs_list = ps_list.executeQuery();
                        System.out.println("\nLista de Contatos cadastrados na Base de dados:");
                        
                        if (rs_list.isBeforeFirst() !=true) {
                            System.out.println("Nenhum Contato cadastrado na Base de dados.");
                        }
                        while (rs_list.next() == true) {
                            System.out.println("Nome: " +rs_list.getString(1)+ 
                            ", Número de Telefone: " +rs_list.getString(2)+
                            ", E-mail: " +rs_list.getString(3));
                        }
                        
                        ps_list.execute();
                        ps_list.close();
                        rs_list.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
			 		
			 		break;
			 		
			 	case 5:
			 		System.out.println("\nSaindo do Sistema...");
			 		break;
			 		
			 	default:
			 		System.out.println("\nEscolha inválida. Por favor, escolha uma opção válida.");
			 		break;
			}
				
		 } while(choice != 5); {
			 scanner.close();
		 }		 
	}

}