package objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Post implements Serializable{

	private String userPosted;
	private String date;
	private String caption;
	private int likeCounter;
	private int commentCounter;
	private LinkedList<Post> comments = new LinkedList<>();;
	
	public String getUserPosted() {
		return userPosted;
	}
	public void setUserPosted(String userPosted) {
		this.userPosted = userPosted;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLikeCounter() {
		return likeCounter;
	}
	public void setLikeCounter(int likeCounter) {
		this.likeCounter = likeCounter;
	}
	public int getCommentCounter() {
		return commentCounter;
	}
	public void setCommentCounter(int commentCounter) {
		this.commentCounter = commentCounter;
	}
	public LinkedList<Post> getComments() {
		return comments;
	}
	
	
}
