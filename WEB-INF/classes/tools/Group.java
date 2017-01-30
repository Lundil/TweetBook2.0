package tools;
import java.util.ArrayList;

public class Group{

	//informations sur le groupe
	private String title;
	private int id,  creator;
	private ArrayList<User> users;

	public Group(int id, String title, User creator){
		this.id = id;
		this.title = title;
		this.creator = creator.getId();
		this.users = new ArrayList<User>();
		this.users.add(creator);
	}
	public boolean containsUser(int id){
		for(User user : this.users){
			if(user.getId() == id)
				return true;
		}
		return false;
	}
	public void addUser(User name){
		if(!this.containsUser(name.getId()))
			this.users.add(name);
	}
	public void removeUser(User name){
		if(this.containsUser(name.getId()))
			this.users.remove(name);
	}
	public void setTitle(String name){
		this.title = name;
	}
	public void setUsers(ArrayList<User> name){
		this.users = name;
	}
	public int getId(){
		return this.id;
	}
	public String getTitle(){
		return this.title;
	}
	public int getCreator(){
		return this.creator;
	}
	public ArrayList<User> getUsers(){
		return this.users;
	}
}