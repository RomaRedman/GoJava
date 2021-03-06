package kickstarter;

import java.util.Iterator;
import java.util.Random;

import kickstarter.engine.Category;
import kickstarter.engine.Project;
import kickstarter.engine.Quote;
import kickstarter.storages.Storage;
import kickstarter.storages.relations.Relations;

public class Model {
	private Storage<Quote> quotes;
	private Storage<Category> categories;
	private Storage<Project> projects;
	private Relations<Project, Category> projectsInCategory;

	public Model(Storage<Quote> quotes, Storage<Category> categories, Storage<Project> projects,
			Relations<Project, Category> projectsInCategory) {
		this.quotes = quotes;
		this.categories = categories;
		this.projects = projects;
		this.projectsInCategory = projectsInCategory;
	}

	public void addQuote(Quote quote) {
		quotes.add(quote);
	}

	public Quote getRandomQuote() {
		if (quotes.isEmpty()) {
			return null;
		}

		return calculateRandomQuote();
	}

	public void addCategory(Category category) {
		categories.add(category);
	}

	public Iterator<Category> getCategoriesIterator() {
		return categories.getIterator();
	}

	public Category getCategory(int id) {
		return categories.get(id);
	}

	public void addProject(Project project, Category category) {
		projects.add(project);
		projectsInCategory.add(project, category);
	}

	public Iterator<Project> getProjectsIterator(Category category) {
		return projectsInCategory.getProjects(category).getIterator();
	}

	public Project getProject(int id, Category category) {
		return projectsInCategory.getProjects(category).get(id);
	}

	public Project getProjectItem(int id) {
		throw new IndexOutOfBoundsException();
	}

	private Quote calculateRandomQuote() {
		int index = new Random().nextInt(quotes.size());
		Iterator<Quote> iterator = quotes.getIterator();
		while (index-- > 0) {
			iterator.next();
		}
		return iterator.next();
	}

}
