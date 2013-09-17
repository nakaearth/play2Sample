package controllers;

import java.util.List;

import models.Child;
import models.Parent;
import play.db.ebean.Model.Finder;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxRunnable;

public class Application extends Controller {

	public static Result index() {
		try {
			// トランザクションの実行
			Ebean.execute(new TxRunnable() {

				@Override
				public void run() {
					// 現在の全検索
					Finder<Long, Parent> finder = new Finder<Long, Parent>(
							Long.class, Parent.class);
					List<Parent> parents = finder.all();

					// 現在登録されている子をすべて削除します。
					for (Parent parent : parents) {
						for (Child child : parent.children) {
							child.delete();
						}
					}

					// 例外を発生させる。
					throw new RuntimeException();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 親1の作成
		Parent parent1 = new Parent();
		parent1.name = "渋谷太郎";

		// 子1の追加
		Child child1_1 = new Child();
		child1_1.name = "渋谷小太郎";
		parent1.children.add(child1_1);

		// 子2の追加
		Child child1_2 = new Child();
		child1_2.name = "渋谷小二郎";
		parent1.children.add(child1_2);

		// 親1の保存
		parent1.save();

		// 親2の作成
		Parent parent2 = new Parent();
		parent2.name = "恵比寿太郎";

		// 子1の追加
		Child child2_1 = new Child();
		child2_1.name = "恵比寿小太郎";
		parent2.children.add(child2_1);

		// 子2の追加
		Child child2_2 = new Child();
		child2_2.name = "恵比寿小二郎";
		parent2.children.add(child2_2);

		// 親2の保存
		parent2.save();

		// 現在のParentを全検索して表示
		Finder<Long, Parent> finder = new Finder<Long, Parent>(Long.class,
				Parent.class);
		List<Parent> parents = finder.all();

		StringBuilder msg = new StringBuilder();
		for (Parent parent : parents) {
			msg.append(parent.toString()).append("\n");
			// Child の表示
			for (Child child : parent.children) {
				msg.append("   ").append(child.toString()).append("\n");
			}
		}
		return ok(msg.toString());
	}

}
