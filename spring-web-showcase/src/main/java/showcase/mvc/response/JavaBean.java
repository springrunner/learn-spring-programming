package showcase.mvc.response;

import javax.validation.constraints.NotNull;

public class JavaBean {
	
	@NotNull
	private String foo;

	@NotNull
	private String fruit;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getFruit() {
		return fruit;
	}

	public void setFruit(String fruit) {
		this.fruit = fruit;
	}

	public static JavaBean of(String foo, String fruit) {
		JavaBean javaBean = new JavaBean();
		javaBean.setFoo(foo);
		javaBean.setFruit(fruit);
		return javaBean;
	}

}
