package iFace2;

import java.util.Scanner;
import java.util.ArrayList;
import iFace2.User;
import iFace2.Message;

public class Main {
	public static ArrayList<User> accounts = new ArrayList<User>();
	public static ArrayList<Community> allCommunities = new ArrayList<Community>();
	static int aux;
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		User currentUser = null;
		int cont = 1, function, option;
				
		while(cont != 0)
		{
			function = 1;
			System.out.println("\n\n\n");
			System.out.println("### Bem vindo(a) ao iFace! ###");
			System.out.println("(0) Sair\n"
					+ "(1) Criar Conta\n"
					+ "(2) Fazer Login\n");
			
			cont = input.nextInt();
			
			if(cont == 0)
			{
				break;
			}
			else if(cont == 1)
			{	
				System.out.println("\n\n\n");
				System.out.println("### Criar Conta ###\n");
				User newUser = new User();
				newUser.createAccount(newUser, accounts);
				if(newUser.getLogin() == null) {
					continue;
				}
				accounts.add(newUser);
				currentUser = newUser;
				System.out.println("\nConta Criada com Sucesso!\n\n(0) Continuar\n");
				aux = input.nextInt();
			}
			else if(cont == 2)
			{
				System.out.println("\n\n\n");
				System.out.println("### Entrar ###");
				if(accounts.size() == 0) {
					System.out.print("\nLogin: ");
					String login;
					login = input.nextLine();
					System.out.println("Usuário não encontrado!\n");
					System.out.println("(0) Voltar\n");
					aux = input.nextInt();
					continue;
				}
				currentUser = currentUser.loginUser(accounts);
				if(currentUser.isLogged == false) {
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
					continue;
				}
				
			}
			else
			{
				System.out.println("\n\n\n");
				System.out.println("Opção inválida!\n");
				continue;
			}
			while(function != 0) {
				System.out.println("\n\n\n");
				System.out.println("\nOlá, "+ currentUser.getName() +"!\n\n"
						+ "(0) Sair\n"
						+ "(1) Editar perfil\n"
						+ "(2) Amigos\n"
						+ "(3) Mensagens\n"
						+ "(4) Comunidades\n"
						+ "(5) Recuperar Informações do Usuário\n"
						+ "(6) Remover Conta\n");
				function = input.nextInt();
				
				if(function == 0)
				{
					currentUser.isLogged = false;
					break;
				}
				else if(function == 1)
				{	
					System.out.println("\n\n\n");
					System.out.println("### Editar Perfil ###\n");
					currentUser.editAccount(currentUser, accounts);
				}
				else if(function == 2)
				{
					System.out.println("\n\n\n");
					System.out.println("### Amigos ###\n"
							+ "(0) Voltar\n"
							+ "(1) Lista de Amigos\n"
							+ "(2) Convites de amizade\n"
							+ "(3) Enviar Convite de Amizade\n");
					option = input.nextInt();
					if(option == 0) {
						continue;
					}
					else if(option == 1)
					{
						System.out.println("\n\n\n");
						System.out.println("### Lista de Amigos ###\n");
						currentUser.friendsList(currentUser);
					}
					else if(option == 2)
					{	
						System.out.println("\n\n\n");
						System.out.println("### Convites de Amizade ###\n");
						currentUser.answerInvites(currentUser); //, accounts);
					}
					else if(option == 3)
					{
						System.out.println("\n\n\n");
						System.out.println("### Enviar Convite de Amizade ###\n");
						currentUser.sendInvite(currentUser, accounts);
					}
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
				}
				else if(function == 3)
				{
					System.out.println("\n\n\n");
					System.out.println("### Mensagens ###\n\n"
							+ "(0) Voltar\n"
							+ "(1) Enviar Mensagem\n"
							+ "(2) Ver Mensagens");
					option = input.nextInt();
					if(option == 0) {
						continue;
					}
					else if(option == 1) {
						System.out.println("\n\n\n");
						System.out.println("### Enviar Mensagem ###\n");
						Message newMessage = new Message();
						newMessage.sendMessage(accounts, currentUser, newMessage);
					}
					else if(option == 2) {
						int n = currentUser.getReceivedMessages().size();
						Message msg;
						for(int i = n-1; i >= 0; i--) {
							System.out.println("\n\n\n");
							System.out.println("### Ver Mensagem ###\n");
							msg = (Message)currentUser.getReceivedMessages().get(i);
							msg.printMessage(msg);
							System.out.println("(0) Voltar\n"
									+ "(1) Excluir Mensagem\n"
									+ "(2) Próxima Mensagem\n");
							option = input.nextInt();
							if(option == 0) {
								break;
							}
							if(option == 1) {
								currentUser.getReceivedMessages().remove(i);
							}
						}
						if(n == 0) {
							System.out.println("\n\n\n");
							System.out.println("### Ver Mensagem ###\n");
							System.out.println("Sem Mensagens!\n");
						}
					}
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
				}
				else if(function == 4)
				{
					System.out.println("\n\n\n");
					System.out.println("### Comunidades ###\n"
							+ "(0) Voltar\n"
							+ "(1) Criar Comunidade\n"
							+ "(2) Entrar em uma Comunidade\n"
							+ "(3) Minhas Comunidades\n"
							+ "(4) Comunidades que sou Membro\n");
					option = input.nextInt();
					if(option == 1){
						System.out.println("\n\n\n");
						System.out.println("### Criar Comunidade ###\n");
						currentUser.createCommunity(currentUser, allCommunities);
						System.out.println("\nComunidade Criada com Sucesso!\n");
					}
					else if(option == 2){	
						System.out.println("\n\n\n");
						System.out.println("### Entrar em uma Comunidade ###\n");
						currentUser.addMember(currentUser, allCommunities);
					}
					else if(option == 3){
						System.out.println("\n\n\n");
						System.out.println("### Minhas Comunidades ###\n"
								+ "(0) Voltar\n"
								+ "(1) Ver Comunidades\n"
								+ "(2) Gerenciar Comunidade\n");
						option = input.nextInt();
						if(option == 1) {
							System.out.println("\n\n\n");
							System.out.println("### Ver Comunidades ###\n");
							currentUser.printCommunities(currentUser, currentUser.getMyCommunities());
						}
						else if(option == 2) {
							System.out.println("\n\n\n");
							System.out.println("### Gerenciar Comunidades ###\n");
							
						}
						else {
							continue;
						}
						System.out.println("\n(0) Voltarr\n");
						aux = input.nextInt();
					}
					else if(option == 4) {
						System.out.println("\n\n\n");
						System.out.println("### Comunidades que sou Membro ###");
						currentUser.printCommunities(currentUser, currentUser.getCommunities());
					}
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
				}
				else if(function == 5)
				{
					System.out.println("\n\n\n");
					System.out.println("### Recuperar Informações do Usuário ###\n");
					currentUser.printUserInfo(currentUser);
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
				}
				else if(function == 6)
				{
					System.out.println("\n\n\n");
					System.out.println("### Remover Conta ###\n");
					if(currentUser.removeAccount(currentUser, accounts) == true){
						break;
					}
					else {
						System.out.println("\n(0) Voltar\n");
						aux = input.nextInt();
					}		
				}
				else
				{
					System.out.println("Opção inválida!\n");
					System.out.println("\n(0) Voltar\n");
					aux = input.nextInt();
					continue;
				}
				
			}
		}
	}
}
