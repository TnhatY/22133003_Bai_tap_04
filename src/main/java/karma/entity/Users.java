package karma.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity

@Table(name = "users")

@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")

public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")

	private int id;

	@Column(name = "username", columnDefinition = "NVARCHAR(50) NULL")

	private String username;

	@Column(name = "password", columnDefinition = "NVARCHAR(20) NULL")

	private String password;

	@Column(name = "image", columnDefinition = "NVARCHAR(MAX) NULL")

	private String image;

	@Column(name = "fullname", columnDefinition = "NVARCHAR(50) NULL")

	private String fullname;

	@Column(name = "roleid" )
	private int roleid;

	@Column(name = "phone" , columnDefinition = "NVARCHAR(11) NULL")
	private String phone;

	@Column(name = "email" , columnDefinition = "NVARCHAR(100) NULL")

	private String email;

	

	public Users() {

	}

	public int getUserId() {

		return this.id;
	}

	public void setUserId(int id) {

		this.id = id;

	}

	public String getUserName() {

		return this.username;

	}

	public void setUserName(String username) {

		this.username = username;

	}

	public String getPassword() {

		return password;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public String getImage() {

		return image;

	}

	public void setImage(String image) {

		this.image = image;

	}

	public String getFullName() {

		return fullname;

	}

	public void setFullName(String fullname) {

		this.fullname = fullname;

	}

	public String getEmail() {

		return this.email;

	}

	public void setEmail(String email) {

		this.email = email;

	}
	

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Users(String username, String password, String image, String fullname, int roleid, String phone,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.image = image;
		this.fullname = fullname;
		this.roleid = roleid;
		this.phone = phone;
		this.email = email;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id == users.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
