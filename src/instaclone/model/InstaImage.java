package instaclone.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class InstaImage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key key;

	@ManyToOne(fetch=FetchType.EAGER)
	private InstaUser user;
	
	private String imageKey;
	private String title;
	private Integer rating;
	
	public String getKeyString() {
		return KeyFactory.keyToString(getKey());
	}
	
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public InstaUser getUser() {
		return user;
	}
	public void setUser(InstaUser user) {
		this.user = user;
	}
	public String getImageKey() {
		return imageKey;
	}
	public void setImageKey(String imageKey) {
		this.imageKey = imageKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
}
