package kickstarter.pages.viewContent;

import java.util.List;

import kickstarter.repository.facade.RepositoryException;
import kickstarter.repository.facade.entity.Project;
import kickstarter.repository.facade.entity.ProjectComment;

public class DetailedProject extends PageView {

	public String getHeader() throws RepositoryException {
		int projectID = getImodel().getModelValues().getIntSelectedProject();
		Project project = getRepository().getProjectById(projectID);
		StringBuilder header = new StringBuilder();
		header.append("\n________________________");
		header.append("\n|Detailed project info |");
		header.append("\n|______________________|");
		header.append("\nname     :<");
		header.append(project.getName());
		header.append(">");
		header.append("\nID       :<");
		header.append(project.getID());
		header.append(">");
		header.append("\ndescription:<");
		header.append(project.getDescription());
		header.append(">");
		header.append("\ngoal     :<");
		header.append(project.getGoal());
		header.append(">");
		header.append("\npledged  :<");
		header.append(project.getPledged());
		header.append(">");
		header.append("\ndays to go :<");
		header.append(project.getDaysToGo());
		header.append(">");
		header.append("\nhistory :<");
		header.append(project.getHistory());
		header.append(">");
		header.append("\nlink to video :<");
		header.append(project.getLinkToVideo());
		header.append(">");
		header.append("\ncomments :");
		header.append("\n");

		int lengthComments = getRepository().getCommentLength(projectID);

		List<ProjectComment> commentsOfProject = getRepository()
				.getCommentsByProjectID(projectID);

		if (commentsOfProject != null) {
			for (int index = 0; index < lengthComments; index++) {

				header.append("user ID:<");
				header.append(commentsOfProject.get(index).getUserID());
				header.append(">  comment ID:<");
				header.append(index);
				header.append("> <");
				header.append(commentsOfProject.get(index).getComment());
				header.append(">\n");

			}
		}

		header.append("\n------------------------");
		header.append("\nOptions: <p> - previous page; <i>- invest to project ; <c>- comment ; <d>- donate");
		return header.toString();
	}
}