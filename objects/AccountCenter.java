package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class AccountCenter {

	private static AccountCenter instance;
	private TreeMap<String, UserAccount> userMap;
	private UserAccount thisUser;
	private static final String FILENAME = "MEEUSERS.dat";
	
	private AccountCenter() {
		load();
	}
	public static AccountCenter getInstance() {
		if (instance == null) {
			instance = new AccountCenter();
		}
		return instance;
	}
	
	public boolean addUser(String username, String password) {
		UserAccount user = new UserAccount(username, password);
		if (!userMap.containsKey(user.getUsername())) {
			userMap.put(user.getUsername(), user);
			return true;
		}	
		return false;
	}
	
	public boolean validate(String username, String password) {
		if (userMap.containsKey(username)) {
			thisUser = userMap.get(username);
			if (thisUser.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean exists(String username) {
		return userMap.containsKey(username);
	}
	
	public UserAccount findUser(String username) {
		return userMap.get(username);
	}
	
	public UserAccount getThisUser() {
		return thisUser;
	}
	
	public void save() {
		File file = new File(FILENAME);
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
			oos.writeObject(userMap);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		File file = new File(FILENAME);
		if (file.exists()) {
			try (ObjectInputStream dis = new ObjectInputStream(new FileInputStream(file));) {
				this.userMap = (TreeMap<String, UserAccount>)dis.readObject();
			} catch (Exception e) {e.printStackTrace();}
		} else {
			this.userMap = new TreeMap<>();
		}
	}
	
}
