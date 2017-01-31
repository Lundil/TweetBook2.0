package tools;
import java.util.ArrayList;

public class User{

	//informations persomnelles de l'utilisateur;
	private String lastName, firstName, mail, phoneNumber, date, place, address, login, profilPhoto;
	private int id;
	//ID des groupes et des évènements auquels participe l'utilisateur
	private ArrayList<Event> events;
	private ArrayList<Group> groups;
	private ArrayList<Publication> publications;
	private ArrayList<User> friends;

	public User(){
		
	}

	public User(String login){
		this.login = login;
	}
	public User(int id, String lastName, String firstName, String mail, String phoneNumber,
	String date, String place, String address, String login, String profilPhoto){
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.date = date;
		this.place = place;
		this.address = address;
		this.login = login;
		this.profilPhoto = profilPhoto;
		this.publications = new ArrayList<Publication>();
		this.events = new ArrayList<Event>();
		this.groups = new ArrayList<Group>();
		this.friends = new ArrayList<User>();
	}
	public User(int id, String lastName, String firstName, String profilPhoto){
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.profilPhoto = profilPhoto;
	}
	public User(int id, String lastName, String firstName, String mail, String phoneNumber,
	String date, String place, String address, String profilPhoto){
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
		this.date = date;
		this.place = place;
		this.address = address;
		this.profilPhoto = profilPhoto;
		this.friends = new ArrayList<User>();
	}

	public boolean containsFriend(int id){
		for(User user : this.friends){
			if(user.getId() == id)
				return true;
		}
		return false;
	}
	public void addPublication(Publication name){
		this.publications.add(name);
	}
	public void addFriend(User name){
		if(!this.containsFriend(name.getId()))
			this.friends.add(name);
	}
	public void setLastName(String name){
		this.lastName = name;
	}
	public void setFirstName(String name){
		this.firstName = name;
	}
	public void setMail(String name){
		this.mail = name;
	}
	public void setphoneNumber(String name){
		this.phoneNumber = name;
	}
	public void setDate(String name){
		this.date = name;
	}
	public void setPlace(String name){
		this.place = name;
	}
	public void setLogin(String name){
		this.login = name;
	}
	public void setProfilPhoto(String name){
		this.profilPhoto = name;
	}
	public void setPublications(ArrayList<Publication> name){
		this.publications = name;
	}
	public void setFriends(ArrayList<User> name){
		this.friends = name;
	}
	public void setEvents(ArrayList<Event> name){
		this.events = name;
	}
	public void setGroups(ArrayList<Group> name){
		this.groups = name;
	}
	public int getId(){
		return this.id;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getMail(){
		return this.mail;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public String getDate(){
		return this.date;
	}
	public String getPlace(){
		return this.place;
	}
	public String getAddress(){
		return this.address;
	}
	public String getLogin(){
		return this.login;
	}
	public String getProfilPhoto(){
		return this.profilPhoto;
	}
	public ArrayList<Publication> getPublications(){
		return this.publications;
	}
	public ArrayList<Event> getEvents(){
		return this.events;
	}
	public ArrayList<Group> getGroups(){
		return this.groups;
	}
	public ArrayList<User> getFriends(){
		return this.friends;
	}
}