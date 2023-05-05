package objects;

public enum Reactions {
	NON(0, "unlike", "/resources/Like-Button-Transparent.png"),
	LIKE(1, "like", "/resources/acty-like.png");
	
	
	private int id;
	private String name;
	private String imgSource;
	
	Reactions(int id, String name, String imgSource) {
		this.id = id;
		this.name = name;
		this.imgSource = imgSource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgSource() {
		return imgSource;
	}

	public void setImgSource(String imgSource) {
		this.imgSource = imgSource;
	}
}
