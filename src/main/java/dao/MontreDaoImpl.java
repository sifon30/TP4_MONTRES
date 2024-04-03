package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Montre;
import util.JPAutil;

public class MontreDaoImpl implements IMontreDao {
private EntityManager entityManager=JPAutil.getEntityManager("TP5_MONTRES");
@Override
public Montre save(Montre m) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(m);
	tx.commit();
	return m;
	}
	@Override
	public List<Montre> montresParMC(String mc) {
	List<Montre> monts =
	entityManager.createQuery("select m from Montre m where m.nomMontre like :mc").setParameter("mc", "%"+mc+"%")
	.getResultList();

	return monts;
	}
	@Override
	public Montre getMontre(Long id) {
	return entityManager.find(Montre.class, id);
	}
	@Override
	public Montre updateMontre(Montre m) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(m);
	tx.commit();
	return m;
	}
	@Override
	public void deleteMontre(Long id) {
	Montre montre = entityManager.find(Montre.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(montre);
	entityManager.getTransaction().commit();
	}
	}
