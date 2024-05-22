package examen.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import examen.model.Entidad;


public class SuperControladorJPA {

	private static EntityManager em = null;
	private String nombreTabla = "";
	private Class tipoEntidad;
	
	/**
	 * 
	 * @param nombreTabla
	 */
	public SuperControladorJPA (String nombreTabla, Class tipoEntidad) {
		this.nombreTabla = nombreTabla;
		this.tipoEntidad = tipoEntidad;
	}
	
	
	/**
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager () {
		if (em == null) {
			em = Persistence.createEntityManagerFactory("20240521-Examen-Final-JPA")
				.createEntityManager();
		}
		return em;
	}
	
	
	/**
	 * 
	 * @param <T>
	 * @param id
	 * @return
	 */
	public <T extends Entidad> T findById (int id) {
		try {
			return (T) getEntityManager().find(tipoEntidad, id);
		}
		catch (NoResultException nex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	public List<? extends Entidad> findAll () {
		return (List<Entidad>) 
		getEntityManager()
		.createNativeQuery("SELECT * FROM " + this.nombreTabla, this.tipoEntidad)
		.getResultList();
	}
	
	
	/**
	 * 
	 */
	public <T extends Entidad> T findFirst () {
		try {
			return (T) getEntityManager()
					.createNativeQuery("SELECT * FROM " + this.nombreTabla + " order by id limit 1", this.tipoEntidad)
					.getSingleResult();
		}
		catch (NoResultException nex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	public <T extends Entidad> T findLast () {
		try {
			return (T) getEntityManager()
			.createNativeQuery("SELECT * FROM " + this.nombreTabla + " order by id desc limit 1", this.tipoEntidad)
			.getSingleResult();
		}
		catch (NoResultException nex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	public <T extends Entidad> T findNext (int currentId) {
		try {
			return (T) getEntityManager()
			.createNativeQuery("SELECT * FROM " + this.nombreTabla + " where id > " + currentId + " order by id limit 1", this.tipoEntidad)
			.getSingleResult();
		}
		catch (NoResultException nex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 */
	public <T extends Entidad> T findPrevious (int currentId) {
		try {
			return (T) getEntityManager()
			.createNativeQuery("SELECT * FROM " + this.nombreTabla + " where id < " + currentId + " order by id desc limit 1", this.tipoEntidad)
			.getSingleResult();
		}
		catch (NoResultException nex) {
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param tableColumn
	 * @param tableColumnOrder
	 * @param id
	 * @return
	 */
	public List<? extends Entidad> findByOrderBy (
			String tableColumn, String tableColumnOrder, int id) {
		
		String sql = "SELECT * FROM " + this.nombreTabla + " WHERE " 
				+ tableColumn + " = " + id + " ORDER BY " + tableColumnOrder;
		Query query = getEntityManager().createNativeQuery(sql, this.tipoEntidad);
        
        return query.getResultList();
	}
	
	
	/**
	 * 
	 * @param <T>
	 * @param tableColumn
	 * @param likeText
	 * @return
	 */
	public List<? extends Entidad> findByLikeOperator (String tableColumn, 
			String likeText, boolean caseSensitive) {
		
		String sql = "SELECT * FROM " + this.nombreTabla + " WHERE " + 
				((!caseSensitive)? "UPPER(" + tableColumn + ")" : tableColumn) + " LIKE ?";
		Query query = getEntityManager().createNativeQuery(sql, this.tipoEntidad);
        query.setParameter(1, "%" + 
        		((!caseSensitive)? likeText.toUpperCase() : likeText ) 
        		+ "%");
        
        return query.getResultList();
	}

	/**
	 * 
	 * @param tableColumn
	 * @param likeText
	 * @return
	 */
	public List<? extends Entidad> findByLikeOperator (String tableColumn, String likeText) {
		return findByLikeOperator(tableColumn, likeText, true);
	}

	
	/**
	 * 
	 */
	public void update (Entidad e) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}



	/**
	 * 
	 */
	public void persist (Entidad e) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}


	/**
	 * 
	 */
	public void save (Entidad e) {
		if (e.getId() == 0) {
			persist(e);
		}
		else {
			update(e);
		}
	}


	/**
	 * 
	 */
	public void remove (Entidad e) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
	}



}
