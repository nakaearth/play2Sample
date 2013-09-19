	package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.top;
import views.html.name_result;

public class Uranai extends Controller {

	public static Result showTop() {
        return ok(top.render());
    }
	
	public static Result showNameResult() {
	    String[] params = { "name" };
	    DynamicForm input = Form.form();
	    input = input.bindFromRequest(params);
	    String name = input.data().get("name");
	    // 入力チェック
	    if (name == null || name.trim().equals("")) {
	        return badRequest("名前を入力してください。");
	    }
	    return ok(name_result.render(name));
	}
}
