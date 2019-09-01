package iFace2;

import java.util.ArrayList;
import java.util.Scanner;
import iFace2.User;

public class Community {
	public String name;
	public String description;
	public User owner;
	public ArrayList<User> members = new ArrayList<User>();
	Scanner input = new Scanner(System.in);
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwner() {
		return owner;
	}
}

