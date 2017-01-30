package tools;
import java.util.ArrayList;

public class Event{

	//informations sur l'évènement
	private String title, description, place, date;
	private int creator;
	//login des participants (utilisateurs)
	private ArrayList<User> users;

	public Event(String title, String description, String place, String date, User creator){
		this.title = title;
		this.description = description;
		this.place = place;
		this.date = date;
		this.creator = creator.getId();
		this.users = new ArrayList<User>();
		this.users.add(creator);
	}
	public void addUser(User name){
		if(!this.users.contains(name))
			this.users.add(name);
	}
	public void removeUser(User name){
		if(this.users.contains(name) && creator != name.getId())
			this.users.remove(name);
	}


	//SET ---------------------------------------------------------------
	public void setTitle(String name){
		this.title = name;
	}
	public void setDescription(String name){
		this.description = name;
	}
	public void setDate(String name){
		this.date = name;
	}
	public void setPlace(String name){
		this.place = name;
	}
	public void setUsers(ArrayList<User> name){
		this.users = name;
	}

	//GET ---------------------------------------------------------------
	public String getTitle(){
		return this.title;
	}
	public String getDescription(){
		return this.description;
	}
	public String getDate(){
		return this.date;
	}
	public String getPlace(){
		return this.place;
	}
	public int getCreator(){
		return this.creator;
	}
	public ArrayList<User> getUsers(){
		return this.users;
	}
}