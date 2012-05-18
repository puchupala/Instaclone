package instaclone.action;

import instaclone.EMF;
import instaclone.model.InstaImage;
import instaclone.model.InstaUser;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UploadAction extends ActionSupport {
	
	private String title;
	private Integer rating;
	private String image;

	public String execute () {
		
		EntityManager em = EMF.get().createEntityManager();
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(ServletActionContext.getRequest());
		List<BlobKey> blobKeys = blobs.get("image");
		
		User currentUser = UserServiceFactory.getUserService().getCurrentUser();
		Query q = em.createQuery("select u from InstaUser u where u.userId = :userId");
		q.setParameter("userId", currentUser.getUserId());
		InstaUser user = (InstaUser)q.getSingleResult();

		InstaImage image = new InstaImage();
		image.setTitle(getTitle());
		image.setRating(getRating());
		
		if (blobKeys != null && blobKeys.size() > 0) {
			image.setImageKey(blobKeys.get(0).getKeyString());
		}
		
		user.getImages().add(image);
		
		em.getTransaction().begin();
		try {
			em.persist(user);
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
		
		return SUCCESS;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
