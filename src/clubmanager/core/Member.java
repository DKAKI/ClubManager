package clubmanager.core;

/**
 * @author NAHED
 *
 */
public class Member {
public int idMember;
public String login;
public String password;
public String firstName;
public String lastName;
public String email;




	/**
 * @param idMember
 * @param login
 * @param password
 * @param firstName
 * @param lastName
 * @param email
 */
public Member(int idMember, String login, String password, String firstName, String lastName, String email) {
	this.idMember = idMember;
	this.login = login;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
}
public Member(String login, String password, String firstName, String lastName,String email) {

	this(0, login, password, firstName, lastName,email);
}

	/**
 * @return the idMember
 */
public int getIdMember() {
	return idMember;
}


/**
 * @param idMember the idMember to set
 */
public void setIdMember(int idMember) {
	this.idMember = idMember;
}


/**
 * @return the login
 */
public String getLogin() {
	return login;
}




/**
 * @param login the login to set
 */
public void setLogin(String login) {
	this.login = login;
}




/**
 * @return the password
 */
public String getPassword() {
	return password;
}




/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}




/**
 * @return the firstName
 */
public String getFirstName() {
	return firstName;
}




/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
}




/**
 * @return the lastName
 */
public String getLastName() {
	return lastName;
}




/**
 * @param lastName the lastName to set
 */
public void setLastName(String lastName) {
	this.lastName = lastName;
}




/**
 * @return the email
 */
public String getEmail() {
	return email;
}




/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}




	public Member() {
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return String
				.format("Member [id_Member=%s, login=%s, password=%s, firstName=%s, lastName=%s, email=%s, salary=%s]",
						idMember, login, password, firstName ,lastName , email);
		//nouvelle notation de toString cmmt ça marche ??%s for the variable
	}
	
	
		}

