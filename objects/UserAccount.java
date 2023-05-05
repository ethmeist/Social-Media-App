package objects;

import java.io.Serializable;
import java.util.LinkedList;

public class UserAccount implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6348960366345784389L;
	private String username;
	private String password;
	private LinkedList<Post> myPosts;
	private LinkedList<UserAccount> followers;
	private LinkedList<UserAccount> following;
	
	public UserAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.myPosts = new LinkedList<>();
		this.followers = new LinkedList<>();
		this.following = new LinkedList<>();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LinkedList<Post> getMyPosts(){
		return myPosts;
	}
	
	public LinkedList<UserAccount> getMyFollowers() {
		return followers;
	}
	public LinkedList<UserAccount> getMyFollowing() {
		return following;
	}
	
	public void removeFollower(String username) {
		followers.remove(AccountCenter.getInstance().findUser(username));
	}
	
	public void removeFollowing(String username) {
		following.remove(AccountCenter.getInstance().findUser(username));
	}
	
	 public boolean isFollowing (String username){
	 	for (UserAccount user : following) {
	 		if (user.getUsername().equals(username)) {
	 			return true;
	 		}
	 	}
	 	return false;
	 }
	 
	 public boolean isFollowedBy(String username) {
		 for (UserAccount user : followers) {
			 if (user.getUsername().equals(username)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public LinkedList<Post> getMeFriend() {
		 LinkedList<Post> myNiggas = new LinkedList<>();
		 
		 //iterate through all posts until we find someone we're following
		 //when we find a post posted by person we follow, we add to tail
		 for (Post post : PostCenter.getInstance().getAllPosts()) {
			 if (isFollowing(post.getUserPosted())) {
				 myNiggas.addLast(post);
			 }
		 }
		 return myNiggas;
	 } 
	 
}
