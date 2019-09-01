package iFace2;

import java.util.ArrayList;
import java.util.Scanner;
import iFace2.User;

public class Message {
	private String text;
	private User sender;
	private User receiver;
	Scanner input = new Scanner(System.in);
	
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getSender() {
		return sender;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public User getReceiver() {
		return receiver;
	}
	public void printMessage(Message currentMessage) {
		System.out.println("De: " + currentMessage.getSender().getName());
		System.out.println(currentMessage.getText() + "\n");
	}
	public void sendMessage(ArrayList<User> accounts, User senderUser, Message newMessage){
		String text;
		String receiver;
		User receiverUser = new User();
		System.out.println("Digite o nome do Destinatário: ");
		receiver = input.nextLine();
		
		receiverUser = receiverUser.searchUser(accounts, 2, receiver);
		if(receiverUser.getLogin() == null) {
			System.out.println("Usuário não encontrado!\n");
			return;
		}
		else {
			newMessage.setReceiver(receiverUser);
			newMessage.setSender(senderUser);
			System.out.println("Digite a mensagem a ser enviada: ");
			text = input.nextLine();
			newMessage.setText(text);
			senderUser.getSentMessages().add(newMessage);
			receiverUser.getReceivedMessages().add(newMessage);
		}	
	}

}
