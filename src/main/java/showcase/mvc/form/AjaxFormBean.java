package showcase.mvc.form;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class AjaxFormBean {

	@NotEmpty
	private String username;

	@Valid
	private Password password;

	private boolean rememberMe;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AjaxFormBean {");
		sb.append("username='").append(username).append('\'');
		sb.append(", password=").append(password);
		sb.append(", rememberMe=").append(rememberMe);
		sb.append('}');
		return sb.toString();
	}

	public static class Password {

		@NotEmpty
		private String value;

		public Password(String value) {
			this.value = StringUtils.hasText(value) ? DigestUtils.md5DigestAsHex(value.getBytes()) : null;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}

	}

}
