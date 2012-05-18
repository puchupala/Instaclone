package instaclone.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.users.User;

@Entity
public class InstaUser {
	
	@Id
	private String userId;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<InstaImage> images;
	
	private User user;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<InstaImage> getImages() {
		return images;
	}
	public void setImages(List<InstaImage> images) {
		this.images = images;
	}

}
