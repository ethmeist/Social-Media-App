package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;


public class PostCenter {
	
	private LinkedList<Post> allPosts;
	private static PostCenter instance;
	public static final String FILENAME = "MEEPOSTT.dat";
	
	private PostCenter() {
		load();
	}

	public static PostCenter getInstance() {
		if (instance == null) {
			instance = new PostCenter();
		}
		return instance;
	}
	
	public void addNewPost(String caption) {
		Post post = new Post();
		post.setUserPosted(AccountCenter.getInstance().getThisUser().getUsername());
		post.setCaption(caption);
		post.setDate(Utils.getDate());
		post.setLikeCounter(0);
		post.setCommentCounter(0);
		allPosts.addFirst(post);
	}
	
	public LinkedList<Post> getAllPosts() {
		return allPosts;
	}
	
	public Post findPost(String caption) {
		for (Post post : allPosts) {
			if (post.getCaption().equals(caption)) {
				return post;
			}
		}
		return null;
	}
	
	public void save() {
		File file = new File(FILENAME);
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(allPosts);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		File file = new File(FILENAME);
		if (file.exists()) {
			try (ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));) {
				this.allPosts = (LinkedList<Post>)dis.readObject();
			} catch (Exception e) {e.printStackTrace();}
		} else {
			this.allPosts = new LinkedList<>();
		}
	}
}
