package iFace2;

import java.util.ArrayList;
import java.util.Scanner;

public class User {
	private String login;
	private String password;
	public String name; 
	private static ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Message> sentMessages = new ArrayList<Message>();
	private ArrayList<Message> receivedMessages = new ArrayList<Message>();
	private ArrayList<User> invites = new ArrayList<User>();
	private ArrayList<Community> communities = new ArrayList<Community>();
	private ArrayList<Community> myCommunities = new ArrayList<Community>();
	public boolean isLogged = false;
	
	Scanner input = new Scanner(System.in);
	
	public User() {
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
		this.invites = invites;
		this.communities = communities;
	}
	public ArrayList getSentMessages() {
		return sentMessages;
	}
	public ArrayList getReceivedMessages() {
		return receivedMessages;
	}
	public ArrayList getFriends() {
		return friends;
	}
	public ArrayList getInvites() {
		return invites;
	}
	public ArrayList getCommunities() {
		return communities;
	}
	public ArrayList getMyCommunities() {
		return myCommunities;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogin() {
		return login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void createAccount(User newUser, ArrayList<User> accounts) {
		String login;
		String name;
		String password;
		
		System.out.print("\nLogin: ");
		login = input.nextLine();
		User existentUser = searchUser(accounts, 1, login);
		if(existentUser != null) {
			System.out.println("Este Login já está sendo usado!\n");
			return;
		}
		System.out.print("\nName: ");
		name = input.nextLine();
		User existentName = searchUser(accounts, 2, login);
		if(existentName != null) {
			System.out.println("Este Nome já está sendo usado!\n");
			return;
		}
		System.out.print("\nPassword: ");
		password = input.nextLine();
		newUser.setLogin(login);
		newUser.setName(name);
		newUser.setPassword(password);
	}
	public User loginUser(ArrayList<User> accounts) {
		User currentUser;// = new User();
		String login;
		String password;
		int cont = 1;
		
		System.out.print("\nLogin: ");
		login = input.nextLine();
		currentUser = searchUser(accounts, 1, login);
		currentUser.isLogged = false;
		//if(currentUser != null){
			System.out.print("\nPassword: ");
			password = input.nextLine();
			if(password.equals(currentUser.getPassword())){
				currentUser.isLogged = true;
				
			}
			else {
				System.out.println("\n\n\n");
				System.out.println("(0) Voltar\n");
				System.out.println("Login ou Senha incorretos!\n");
		//	}
			
		}
		return currentUser;
	}
	public void editAccount(User currentUser, ArrayList<User> accounts){
		System.out.println("### Selecione a opção que deseja editar: ###\n"
				+ "(0) Voltar\n"
				+ "(1) Login\n"
				+ "(2) Senha\n"
				+ "(3) Nome");
		int edit = input.nextInt();
		String newString;
		if(edit == 1){
			System.out.println("Digite o novo Login:\n");
			newString = input.next();
			User existentUser = searchUser(accounts, 1, newString);
			if(existentUser != null) {
				System.out.println("Este Login já está sendo usado!\n");
				return;
			}
			currentUser.setLogin(newString);
			
		}
		else if(edit == 2){
			System.out.println("Digite a nova senha:\n");
			newString = input.next();
			currentUser.setPassword(newString);
			
		}
		else if(edit == 3){
			System.out.println("Digite o novo Nome:\n");
			newString = input.next();
			User existentName = searchUser(accounts, 2, newString);
			if(existentName != null) {
				System.out.println("Este Nome já está sendo usado!\n");
				return;
			}
			currentUser.setName(newString);
		}
		else {
			return;
		}
	}
	public User searchUser(ArrayList<User> accounts, int attribute, String element) {
		int n = accounts.size();
		
		if(attribute == 1) { // search login
			
			for(int i = 0; i < n; i++) {
				if(element.equals(accounts.get(i).login))
				{
					return accounts.get(i);
				}
			}
		}
		else if(attribute == 2) { // search name
			
			for(int i = 0; i < n; i++) {
				if(element.equalsIgnoreCase(accounts.get(i).name))
				{
					return accounts.get(i);
				}
			}
		}
		return null;
	}
	
	public void friendsList(User currentUser) {
		int n = currentUser.getFriends().size();
		User friend = new User();
		for(int i = 0; i < n; i++) {
			friend = (User)currentUser.getFriends().get(i);
			System.out.println(friend.getName());
		}	
	}
	public void answerInvites(User currentUser) { //, ArrayList accounts) {
		int n = currentUser.getInvites().size();
		User newFriend = new User();
		int option;
		for(int i = 0; i < n; i++) {
			newFriend = (User)currentUser.getInvites().get(i);
			System.out.println(newFriend.getName() + " quer ser seu Amigo!\n"
					+ "(0) Voltar\n"
					+ "(1) Aceitar\n"
					+ "(2) Recusar\n"
					+ "(3) Próximo Convite\n");
			option = input.nextInt();
			
			if(option == 1) {
				currentUser.getFriends().add(newFriend);
				newFriend.getFriends().add(currentUser);
				currentUser.getInvites().remove(i);
				n = currentUser.getInvites().size();
				System.out.println("Agora você e " + newFriend.getName() + " são Amigos!\n");
			}
			else if(option == 2) {
				currentUser.getInvites().remove(i);
			}
			else if(option == 3) {
				if(i == n-1)
				{
					i = -1;
				}
			}
			else {
				return;
			}
		}
	}
	public void sendInvite(User currentUser, ArrayList accounts) {
		String friendName;
		System.out.print("Digite o nome do usuário que deseja adicionar: ");
		friendName = input.nextLine();
		User newFriend = searchUser(accounts, 2, friendName);
		if(newFriend.getLogin() == null)
		{
			System.out.println("\nUsuário não encontrado!\n");
			return;
		}
		else {
			newFriend.getInvites().add(currentUser);
			System.out.println("\nConvite Enviado!\n");
			return;
		}
	}
	public void createCommunity(User currentUser, ArrayList<Community> allCommunities) {
		Community newCommunity = new Community();
		Community auxCommunity = new Community();
		String name;
		String description;
		System.out.println("Digite o nome da nova Comunidade:\n");
		name = input.nextLine();
		int n = allCommunities.size();
		for(int i = 0; i < n; i++) {
			auxCommunity = (Community)allCommunities.get(i);
			if(name.equalsIgnoreCase(auxCommunity.getName())) {
				System.out.println("Essa Comunidade já Existe!\n");
				return;
			}
		}
		newCommunity.setName(name);
		System.out.println("\nDigite a Descrição da nova Comunidade:\n");
		description = input.nextLine();
		newCommunity.setDescription(description);
		currentUser.getMyCommunities().add(newCommunity);
		newCommunity.setOwner(currentUser);
		allCommunities.add(newCommunity);
	}
	public void addMember(User currentUser, ArrayList<Community> allCommunities) {
		String name;
		String search;
		Community currentCommunity = new Community();
		System.out.println("Digite o nome da Comunidade que deseja entrar:\n");
		name = input.nextLine();
		int n = allCommunities.size();
		for(int i = 0; i < n; i++) {
			currentCommunity = (Community)allCommunities.get(i);
			search = currentCommunity.getName();
			if(name.equalsIgnoreCase(search)) {
				currentCommunity.members.add(currentUser);
				currentUser.getCommunities().add(currentCommunity);
				break;
			}
		}
		System.out.println("Comunidade não Encontrada!\n");
	}
	public void printCommunities(User currentUser, ArrayList<Community> communities) {
		int n = currentUser.getMyCommunities().size();
		Community currentCommunity;
		for(int i = 0; i < n; i++) {
			currentCommunity = communities.get(i);
			System.out.println("\nComunidade: " + currentCommunity.name + "\nDescrição: " + currentCommunity.description);
		}
	}
	public void printUserInfo(User currentUser) {
		System.out.println("->Login: " + currentUser.getLogin() +
				"\n->Nome: " + currentUser.getName() +
				"\n->Minhas Comunidades:");
		currentUser.printCommunities(currentUser, currentUser.getMyCommunities());
		System.out.println("->Comunidades que sou Membro:");
		currentUser.printCommunities(currentUser, currentUser.getCommunities());
		System.out.println("->Amigos:");
		currentUser.friendsList(currentUser);
		System.out.println("->Mensagens:");
		int n = currentUser.getReceivedMessages().size();
		Message msg;
		for(int i = n-1; i >= 0; i--) {
			msg = (Message)currentUser.getReceivedMessages().get(i);
			msg.printMessage(msg);
		}	
	}
	public boolean removeAccount(User currentUser, ArrayList<User> accounts) {
		System.out.println("Tem Certeza que deseja remover sua conta? (Não pode ser desfeito)\n"
				+ "(1) Sim\n"
				+ "(2) Não\n");
		int option = input.nextInt();
		int n, m; 
		int i, j;
		if(option == 1)
		{		
			n = currentUser.getMyCommunities().size();
			Community currentCommunity = new Community();
			User auxUser = new User();
			for(i = 0; i < n; i++) {
				currentCommunity = (Community)currentUser.getMyCommunities().get(i);
				m = currentCommunity.members.size();
				for(j = 0; j < m; j++) {
					auxUser = currentCommunity.members.get(i);
					leaveCommunity(auxUser, currentCommunity);
				}
				currentCommunity.setName(null);
				currentCommunity.setDescription(null);
			}
			n = currentUser.getCommunities().size();
			for(i = 0; i < n; i++) {
				currentCommunity = (Community)currentUser.getCommunities().get(i);
				leaveCommunity(currentUser, currentCommunity);
			}
			n = currentUser.getFriends().size();
			for(i = 0; i < n; i++) {
				auxUser = (User)currentUser.getFriends().get(i);
				removeFriend(currentUser, auxUser);
			}
			n = currentUser.getReceivedMessages().size();
			for(i = 0; i < n; i++) {
				currentUser.getReceivedMessages().remove(i);
			}
			n = currentUser.getSentMessages().size();
			Message currentMessage = new Message();
			for(i = 0; i < n; i++) {
				currentMessage = (Message)currentUser.getSentMessages().get(i);
				currentUser.deleteSentMessage(currentMessage);
			}
			n = accounts.size();
			for(i = 0; i < n; i++) {
				if(currentUser.getLogin().equals(accounts.get(i).login))
				{
					accounts.remove(i);
					break;
				}
			}
			currentUser.setName(null);
			currentUser.setPassword(null);
			currentUser.setLogin(null);
			return true;
		}
		else
		{
			return false;
		}
	}
	public void leaveCommunity(User currentUser, Community currentCommunity) {
		int n = currentCommunity.members.size();
		String login;
		for(int i = 0; i < n; i++) {
			login = currentCommunity.members.get(i).getLogin();
			if(login.equals(currentUser.getLogin())) {
				currentCommunity.members.remove(i);
				break;
			}
		}
	}
	public void removeFriend(User currentUser, User friend) {
		String login = currentUser.getLogin();
		String friendLogin = friend.getLogin();
		String auxLogin;
		User auxUser = new User();
		int n = currentUser.getFriends().size();
		for(int i = 0; i < n; i++) {
			auxUser = (User)currentUser.getFriends().get(i);
			if(friendLogin.equals(auxUser.getLogin())) {
				currentUser.getFriends().remove(i);
				break;
			}
		}
		n = friend.getFriends().size();
		for(int i = 0; i < n; i++) {
			auxUser = (User)friend.getFriends().get(i);
			if(login.equals(auxUser.getLogin())) {
				friend.getFriends().remove(i);
			}
		}
	}
	public void deleteSentMessage(Message msg) {
		String text = msg.getText();
		User auxUser = msg.getReceiver();
		Message auxMessage = new Message();
		int n = auxUser.getReceivedMessages().size();
		for(int i = 0; i < n; i++) {
			auxMessage = (Message)auxUser.getReceivedMessages().get(i);
			if(text.equals(auxMessage.getText()) && msg.getSender().equals(auxMessage.getSender()))
			{
				auxUser.getReceivedMessages().remove(i);
			}
		}
	}
	public void manageCommunity(User currentUser) {
		String name;
		String auxString;
		Community currentCommunity = new Community();
		User auxUser = new User();
		int aux;
		System.out.print("Digite o nome da Comunidade que deseja Gerenciar: ");
		name = input.nextLine();
		int n = currentUser.getMyCommunities().size();
		for(int i = 0; i < n; i++) {
			currentCommunity = (Community)currentUser.getMyCommunities().get(i);
			if(name.equals(currentCommunity.name)) {
				System.out.println("(1) Excluir Membro\n"
						+ "(2) Excluir Comunidade\n");
				aux = input.nextInt();
				if(aux == 1) {
					System.out.print("Digite o nome do Membro que deseja Excluir: ");
					auxString = input.nextLine();
					auxUser = currentUser.searchUser(currentCommunity.members, 2, auxString);
					if(auxUser != null) {
						currentUser.leaveCommunity(auxUser, currentCommunity);
						System.out.println("O Usuário " + auxString + "foi removido da Comunidade\n");
					}	
				}
				else if(aux == 2) {
					int m = currentCommunity.members.size();
					for(int j = 0; j < m; j++) {
						auxUser = currentCommunity.members.get(i);
						leaveCommunity(auxUser, currentCommunity);
					}
					currentCommunity.setDescription(null);
					currentCommunity.setName(null);
					currentCommunity.setOwner(null);
					currentUser.getMyCommunities().remove(i);
					System.out.print("A Comunidade " + name + "foi excluída\n");
				}
			}
		}
		
	}
}


