package mil.dtic.ured.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mil.dtic.ured.validation.UniqueLogin;

@Entity
@Table(name = "login")
public class Login {

	@Id
	@UniqueLogin
	private String login;
	private char[] password;

	private Login() {
		// no-arg Jackson constructor
	}

	public Login(String login, char[] password) {
		Objects.requireNonNull(login);
		Objects.requireNonNull(password);
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

}

