package kickstarter.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kickstarter.exception.NoSuchDataException;
import kickstarter.model.dao.DAO;
import kickstarter.model.engine.Category;
import kickstarter.model.engine.Project;

public class ProjectsModel implements Model {
	private DAO dao;
	private int categoryId;
	private List<Object> parameters;

	@Override
	public void init(DAO dao, List<Object> parameters) {
		this.dao = dao;
		this.parameters = new ArrayList<Object>(parameters);
		this.categoryId = ((Category) parameters.get(0)).getId();
	}

	@Override
	public List<String> getData() throws SQLException {
		List<String> result = new ArrayList<>();

		for (Project project : dao.getProjects(categoryId)) {
			result.add(getDescription(project));
		}

		return result;
	}

	@Override
	public boolean showOnly() {
		return false;
	}

	@Override
	public List<Object> getParameters(int item, String input) throws NoSuchDataException, SQLException {
		List<Object> result = new ArrayList<>(parameters);

		if (item == 0) {
			result.remove(0);
		} else {
			int id = item;
			Project project = dao.getProject(id, categoryId);
			result.add(0, project);
		}

		return result;
	}

	private String getDescription(Project project) {
		StringBuilder result = new StringBuilder();

		int item = project.getId();
		result.append(item);
		result.append(" - ");

		result.append(project.getName());

		result.append(", description=");
		result.append(project.getDescription());

		result.append(", totalAmount=");
		result.append(project.getTotalAmount());

		result.append(", collectAmount=");
		result.append(project.getCollectAmount());

		result.append(", daysLeft=");
		result.append(project.getDaysLeft());

		return result.toString();
	}
}
