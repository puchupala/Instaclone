package instaclone.action;

import instaclone.EMF;
import instaclone.model.InstaImage;
import instaclone.model.InstaUser;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	
	private InstaUser user;
	private List<InstaImage> images;
	private String loginUrl;
	private String logoutUrl;
	private String submitUrl;
	private String debug;

	public String execute() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		UserService userService = UserServiceFactory.getUserService();
		User currentUser = userService.getCurrentUser();
		
		if (currentUser == null) {
			setLoginUrl(userService.createLoginURL(request.getRequestURI()));
			return LOGIN;
	    }
		
		EntityManager em = EMF.get().createEntityManager();
		Query q = em.createQuery("select u from InstaUser u where u.userId = :userId");
		q.setParameter("userId", currentUser.getUserId());
		
		InstaUser user;
		try {
			user = (InstaUser)q.getSingleResult();
		} catch (Exception e) {
			user = new InstaUser();
			user.setUserId(currentUser.getUserId());
			user.setUser(currentUser);
			em.persist(user);
		}
		
		setUser(user);
		setImages(user.getImages());
		setLogoutUrl(userService.createLogoutURL(request.getRequestURI()));
		setSubmitUrl(BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/s/upload"));
		
		// Need this line for some reason...
		try {
			setDebug(user.getImages().get(0).getTitle());
		} catch (Exception e) {
			//
		}
		
		em.close();
		return SUCCESS;
	}

	public InstaUser getUser() {
		return user;
	}

	public void setUser(InstaUser user) {
		this.user = user;
	}

	public List<InstaImage> getImages() {
		return images;
	}

	public void setImages(List<InstaImage> images) {
		this.images = images;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}
	
}
