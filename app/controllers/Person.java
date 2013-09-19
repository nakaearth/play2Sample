	package controllers;

import java.util.List;

import models.Parent;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model.Finder;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.person_result_list;
import views.html.regist_result;

public class Person extends Controller {

	public static Result personList() {
	     // 現在のParentを全検索して表示
        Finder<Long, Parent> finder = new Finder<Long, Parent>(Long.class,
                Parent.class);
        List<Parent> parents = finder.all();
 
        return ok(person_result_list.render(parents));
    }
	
	public static Result regist() {
	    String[] params = { "name" };
	    DynamicForm input = Form.form();
	    input = input.bindFromRequest(params);
	    String name = input.data().get("name");
	    // 入力チェック
	    if (name == null || name.trim().equals("")) {
	        return badRequest("名前を入力してください。");
	    }
	    Parent parent1 = new Parent();
		parent1.name = name;
		parent1.save();
		
	    return ok(regist_result.render(name));
	}
}
