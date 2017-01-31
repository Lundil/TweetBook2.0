package tools;
import java.util.ArrayList;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

/** manipulation des informations dans la base de données */

public class Model{

	protected ResultSet result;
	protected PreparedStatement statement;
	protected Connection connection = null;

	public void initialize(){
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("database");
			connection = ds.getConnection();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/** récupère l'ID d'un utilisateur pour confirmer sa connexion
	* @param login : String
	* @param password : String
	* @return String */
	public String getID(String login, String password){
		String id = null;
		try{
			statement = connection.prepareStatement("select IDUser from users where login = ? and password = ?");
			statement.setString(1,login);
			statement.setString(2, password);
			result = statement.executeQuery();
			result.next();
			id = result.getString(1);
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return id;
			} catch(Exception e){
				e.printStackTrace();
				return id;
			}
		}
	}

	/** récupère l'ID d'un utilisateur pour confirmer sa connexion
	* @param login : String
	* @param password : String
	* @return String */
	public int getNumberOfFriend(User user){
		int count = -1;
		try{
			statement = connection.prepareStatement("select count(*) from friendWith where IDUser1 = ?");
			statement.setInt(1,user.getId());
			result = statement.executeQuery();
			result.next();
			count = result.getInt(1);
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return count;
			} catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		}
	}

	/** récupère une liste des liens de photo des 5 premiers amis d'un utilisateur
	* @param user : User
	* @return String */
	public ArrayList<String> getProfilPhotoOf5Friend(User user){
		int i = 0;
		ArrayList<String> photos = new ArrayList<String>();
		try{
			statement = connection.prepareStatement("select profilPhoto from users where IDUser in (select IDUser2 from friendWith where IDUser1 = ?) group by LastName order by LastName asc");
			statement.setInt(1,user.getId());
			result = statement.executeQuery();
			while(result.next() && i <5)
				photos.add(result.getString(1));

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return photos;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/** récupère les informations sur l'utilisateur à partir de son ID
	* @param id : String
	* @return User */
	public User getUser(int id){
		User user = null;
		try{
			statement = connection.prepareStatement("select IDUser, lastName, firstName, email, phoneNumber, birthDay, birthPlace, address, login, profilPhoto from users where IDUser = ?");
			statement.setInt(1, id);
			result = statement.executeQuery();

			if(result.next()){
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
								result.getString(5), result.getString(6), result.getString(7), result.getString(8),
								result.getString(9), result.getString(10));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return user;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

		/** récupère les informations sur l'utilisateur à partir de son ID
	* @param id : String
	* @return User */
	public User getUserByLogin(String login){
		User user = null;
		try{
			statement = connection.prepareStatement("select IDUser, lastName, firstName, email, phoneNumber, birthDay, birthPlace, address, login, profilPhoto from users where login = ?");
			statement.setString(1, login);
			result = statement.executeQuery();

			if(result.next()){
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
								result.getString(5), result.getString(6), result.getString(7), result.getString(8),
								result.getString(9), result.getString(10));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return user;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/** récupère les informations sur l'utilisateur à partir de son ID
	* @param id : String
	* @return User */
	public ArrayList<User> getFriend(User user){
		ArrayList<User> users = new ArrayList<User>();
		try{
			statement = connection.prepareStatement("select u.IDUser, u.lastName, u.firstName, u.email, u.phoneNumber, u.birthDay, u.birthPlace, u.address, u.login, u.profilPhoto from users u where u.IDUser in (select f.IDUser1 from friendWith f where f.IDUser2 = ?)");
			statement.setInt(1, user.getId());
			result = statement.executeQuery();

			while(result.next()){
				users.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
								result.getString(5), result.getString(6), result.getString(7), result.getString(8),
								result.getString(9), result.getString(10)));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return users;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/** récupère les informations sur l'utilisateur à partir de son ID
	* @param id : String
	* @return User */
	public User getUser(String login, String password){
		User user = null;
		try{
			statement = connection.prepareStatement("select IDUser, lastName, firstName, email, phoneNumber, birthDay, birthPlace, address, login, profilPhoto from users where login = ? and password = ?");
			statement.setString(1, login);
			statement.setString(2, password);
			result = statement.executeQuery();

			if(result.next()){
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
								result.getString(5), result.getString(6), result.getString(7), result.getString(8),
								result.getString(9), result.getString(10));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return user;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}


	/** récupère les publications d'un utilisateur à partir de son ID
	* @param id : String
	* @return ArrayList<Publication> */
	public ArrayList<Publication> getFriendsPublication(User user){
		ArrayList<Publication> publications = new ArrayList<Publication>();
		try{
			statement = connection.prepareStatement("select p.titlePublication, p.content, p.date u.firstName, u.lastName from publications inner join users u on p.IDUser =  u.IDUser where p.IDUser in (select f.IDUser1 from friendWith f where f.IDUser2 = ?)");
			statement.setInt(1,user.getId());
			result = statement.executeQuery();
			Publication publication;
			while(result.next()){
				publication = new Publication(result.getString(1), result.getString(2), result.getString(3),  result.getInt(4));
				publications.add(publication);
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return publications;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/** récupère les publications d'un utilisateur à partir de son ID
	* @param id : String
	* @return ArrayList<Publication> */
	public ArrayList<Publication> getPublication(User user){
		ArrayList<Publication> publications = new ArrayList<Publication>();
		try{
			statement = connection.prepareStatement("select p.titlePublication, p.content, p.date, u.lastName, u.firstName from publications p inner join users u on u.IDUser = p.IDAuthor where IDAuthor = ?");
			statement.setInt(1,user.getId());
			result = statement.executeQuery();
			Publication publication;
			while(result.next()){
				publication = new Publication(result.getString(1), result.getString(2), result.getString(3), user.getId(), result.getString(4), result.getString(5));
				publications.add(publication);
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return publications;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	/** récupère les publications d'un utilisateur à partir de son ID
	* @param id : String
	* @return ArrayList<Publication> */
	public ArrayList<Group> getGroup(User creator){
		ArrayList<Group> groups = new ArrayList<Group>();
		try{
			statement = connection.prepareStatement("select IDGroup, titleGroup from groups where IDCreator = ?");
			statement.setInt(1,creator.getId());
			result = statement.executeQuery();
			Group group;
			while(result.next()){
				group = new Group(result.getInt(1), result.getString(2), creator);
				groups.add(group);
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return groups;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	public User getFriend(int id){
		User user = null;
		try{
			statement = connection.prepareStatement("select IDUser, lastName, firstName, email, phoneNumber, birthDay, birthPlace, address, profilPhoto from users where IDUser = ?");
			statement.setInt(1, id);
			result = statement.executeQuery();
			if(result.next()){
				user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
								result.getString(5), result.getString(6), result.getString(7), result.getString(8),
								result.getString(9));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return user;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}	
	}

	public ArrayList<User> getPotientialFriend(User user){
		ArrayList<User> friends = null;
		try{
			statement = connection.prepareStatement("select u.IDUser, u.lastName, u.firstName u.profilPhoto from users u where u.IDUser in (select f1.IDUser1 from friendWith f1 where f1.IDUser2 in (select f2.IDUser1 where f2.IDUser2 = ?))");
			statement.setInt(1, user.getId());
			result = statement.executeQuery();
			while(result.next()){
				friends.add(new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4)));
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return friends;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	public void createUser(String firstName, String lastName, String mail, String phoneNumber, String date, String place, String address, String login, String password){
		try{
			//Insert l'utilisateur
			statement = connection.prepareStatement("insert into users(lastName, firstName, email, phoneNumber, birthDay, birthPlace, address, login, password) values(?,?,?,?,?,?,?,?,?)");
			statement.setString(1,lastName);
			statement.setString(2,firstName);
			statement.setString(3,mail);
			statement.setString(4,phoneNumber);
			statement.setString(5,date);
			statement.setString(6,place);
			statement.setString(7,address);
			statement.setString(8,login);
			statement.setString(9,password);
			statement.executeUpdate();
			//Insert le role de l'utilisateur ajouté
			statement = connection.prepareStatement("insert into roles values (?, 'role1')");
			statement.setString(1,login);
			statement.executeUpdate();
			System.out.println("création user de login : " + login);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	//creator = IDUser
	public void createEvent(String title, String description, String place, String date, String creator){
		try{
			//création évènement
			statement = connection.prepareStatement("insert into events(titleEvent, description, place, date, IDCreator) values(?,?,?,?,?)");
			statement.setString(1,title);
			statement.setString(2,description);
			statement.setString(6,place);
			statement.setString(5,date);
			statement.setString(7, creator);
			statement.executeUpdate();

			System.out.println("création event de titre : " + title);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	// author = IDUser
	public void createPublication(String title, String content, int author){
		try{
			//création publication
			statement = connection.prepareStatement("insert into publications(titlePublication, content, date, IDAuthor) values(?,?,now,?)");
			statement.setString(1,title);
			statement.setString(2,content);
			statement.setInt(3, author);
			statement.executeUpdate();

			System.out.println("creation publication de titre : " + title);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void addUserToGroup(int id, int user){
		try{
			//ajout d'utilisateurs au groupe
			statement = connection.prepareStatement("insert into belongToGroup(IDGroup, IDUser) values(?,?)");
			statement.setInt(1,id);
			statement.setInt(2,user);
			statement.executeUpdate();
			System.out.println("ajout utilisateur au groupe d'id' : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void addFriend(int id, User user){
		try{
			//ajout d'utilisateurs au groupe
			statement = connection.prepareStatement("insert into friendWith(IDUser1, IDUser2) values(?,?); insert into friendWith(IDUser2, IDUser1) values(?,?)");
			statement.setInt(1,id);
			statement.setInt(2,user.getId());
			statement.setInt(3,id);
			statement.setInt(4,user.getId());
			statement.executeUpdate();
			System.out.println("ajout ami : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}


	public void addUsersToGroup(int id, ArrayList<User> users){
		if(users.size() > 1){
			for(User user : users)
				addUserToGroup(id, user.getId());
		}
	}

	// author = IDUser
	public String createGroup(String title, User creator){
		String id = null;
		try{
			//création groupe
			statement = connection.prepareStatement("insert into groups(titleGroup, IDCreator) values(?,?)");
			statement.setString(1,title);
			statement.setInt(2,creator.getId());
			statement.executeUpdate();
			//récupération de l'ID
			statement = connection.prepareStatement("select IDGroup from groups where titleGroup = ? and IDCreator = ?");
			statement.setString(1,title);
			statement.setInt(2,creator.getId());
			result = statement.executeQuery();
			//ajout des utilisateurs au group
			if(result.next()){
				id = result.getString(1);
				System.out.println("creation groupe  : " + id);
			}

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				result.close();
				statement.close();
				connection.close();
				return id;
			} catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
	}

	// id = IDUser
	public void updateUser(int id, String firstName, String lastName, String mail, String phoneNumber, String date, String place, String address, String login, String profilPhoto){
		try{
			statement = connection.prepareStatement("update from users set firstName = ?, lastName = ?, email = ?, phoneNumber = ? birthDay = ?, birthPlace = ?, address = ?, profilPhoto = ?, login = ? where IDUser = ?");
			statement.setString(1,firstName);
			statement.setString(2,lastName);
			statement.setString(3, mail);
			statement.setString(4, phoneNumber);
			statement.setString(5, date);
			statement.setString(6, place);
			statement.setString(7, address);
			statement.setString(8, profilPhoto);
			statement.setString(9, login);
			statement.executeUpdate();
			System.out.println("màj user d'Id : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	// creator = IDUser id = IDEvent
	public void updateEvent(int id, String title, String description, String place, String date, String creator){
		try{
			statement = connection.prepareStatement("update from events set title = ?, description = ?, place = ?, date = ? where IDEvent = ? and IDCreator = ?");
			statement.setString(1,title);
			statement.setString(2,description);
			statement.setString(3, place);
			statement.setString(4, date);
			statement.setString(5, creator);
			statement.executeUpdate();

			System.out.println("màj d'event d'Id : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	// author = IDUser id = IDPublication
	public void updatePublication(int id, String title, String content, int author){
		try{
			statement = connection.prepareStatement("update from publications set title = ?, content = ?, date = now where IDPublication = ? and IDAuthor = ?");
			statement.setString(1,title);
			statement.setString(2,content);
			statement.setInt(3, id);
			statement.setInt(4, author);
			statement.executeUpdate();
			System.out.println("màj publication d'Id : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	// author = IDUser id = IDPublication
	public void deletePublication(int id, int author){
		try{
			statement = connection.prepareStatement("delete from publications where IDPublication = ? and IDAuthor = ?");
			statement.setInt(1, id);
			statement.setInt(2, author);
			statement.executeUpdate();
			System.out.println("suppression publication d'Id : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void removeUserFromGroup(int id, User creator, int user){
		try{
			statement = connection.prepareStatement("delete from belongToGroup g1 where g1.IDGroup = (select g2.IDGroup from groups g2 where g2.IDGroup = ? and g2.IDCreator = ?) and g1.IDUser = ?");
			statement.setInt(1, id);
			statement.setInt(2, creator.getId());
			statement.setInt(3, user);
			statement.executeUpdate();
			System.out.println("suppression de l'utilisateur : " + user + " du groupe : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}	
	}

	public void removeFriend(int id, User user){
		try{
			statement = connection.prepareStatement("delete from griendWith where (IDUser2 = ? and IDUser1 = ?) or (IDUser1 = ? and IDUser2 = ?)");
			statement.setInt(1, id);
			statement.setInt(2, user.getId());
			statement.setInt(3, id);
			statement.setInt(4, user.getId());
			statement.executeUpdate();
			System.out.println("suppression ami : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}	
	}

	// author = IDUser id = IDPublication
	public void deleteGroup(int id, User creator){
		try{
			statement = connection.prepareStatement("delete from belongToGroup g1 where g1.IDGroup = (select g2.IDGroup from groups g2 where g2.IDGroup = ? and g2.IDCreator = ?)");
			statement.setInt(1, id);
			statement.setInt(2, creator.getId());
			statement.executeUpdate();
			statement = connection.prepareStatement("delete from groups where IDGroup = ? and IDCreator = ?");
			statement.setInt(1, id);
			statement.setInt(2, creator.getId());
			statement.executeUpdate();
			System.out.println("suppression du groupe d'Id : " + id);

		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
				statement.close();
				connection.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}