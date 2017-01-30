package tools;
import java.util.ArrayList;

public class Publication{

	//informations sur la publication;
	private String title, content, date;
	private int author;

	public Publication(String title, String content, String date, int author){
		this.title = title;
		this.content = content;
		this.date = date;
		this.author = author;
	}
	public void setTitle(String name){
		this.title = name;
	}
	public void setContent(String name){
		this.content = name;
	}
	public void setDate(String name){
		this.date = name;
	}
	public String getTitle(){
		return this.title;
	}
	public String getContent(){
		return this.content;
	}
	public String getDate(){
		return this.date;
	}
	public int getAuthor(){
		return this.author;
	}
}