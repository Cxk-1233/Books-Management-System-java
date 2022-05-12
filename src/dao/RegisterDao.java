package dao;

public class RegisterDao {
       private String username;
       private String userpassword;
       private String usertel;
       private String useraddress;
       
       public RegisterDao(){
    	   
       }

	public RegisterDao( String username, String userpasword,
			String usertel, String useraddress) {
	
		this.username = username;
		this.userpassword = userpasword;
		this.usertel = usertel;
		this.useraddress = useraddress;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUsertel() {
		return usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUseraddress() {
		return useraddress;
	}

	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
	}

	@Override
	public String toString() {
		return "RegisterDao [username=" + username + ", userpasword="
				+ userpassword + ", usertel=" + usertel + ", useraddress="
				+ useraddress + "]";
	}

	

	
	
	
       
       
}
