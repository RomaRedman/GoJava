package goit.vh.kickstarter.mvc.view;

import goit.vh.kickstarter.Output;
import goit.vh.kickstarter.mvc.model.CategoryModel;

/**
 * Created with IntelliJ IDEA.
 * User: dmrachkovskyi
 * Date: 7/11/15
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryView {

    private Output output;

    public CategoryView(Output output) {
        this.output = output;
    }

    public void render(CategoryModel categoryModel) {
        output.println("You choose option " + categoryModel.getCategoryIndex() + ": " + categoryModel.getCategoryName());
    }
}