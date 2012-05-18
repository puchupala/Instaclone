package instaclone.action;

import instaclone.EMF;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteAction extends ActionSupport {
	
	private String key;

	public String execute () {
		EntityManager em = EMF.get().createEntityManager();
		
		Query q = em.createQuery("delete from InstaImage i where i.key = :key");
		q.setParameter("key", getKey());
		q.executeUpdate();
		
		return SUCCESS;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
