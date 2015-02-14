package mainkick;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;

import org.junit.Test;

public class Test1Mock {
	
	@Test
    public void simpleMocking2() throws FileNotFoundException{
		Categories categories = mock(Categories.class);
		Projects projects = new Projects();
		categories.showAllProjectInCategory(2, projects);
		verify(categories).showAllProjectInCategory(2, projects);
	}
}
