package mainkick;


public class Category{
	private int categoryID;
	private String categoryName;
	private int[] projects;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int[] getProjects() {
		return projects;
	}

	public void setProjectsIn(int[] projectsIn) {
		this.projects = projectsIn;
	}
}
