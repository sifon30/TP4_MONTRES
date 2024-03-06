package test;
import java.util.List;

import dao.MontreDaoImpl;
import metier.entities.Montre;
public class TestDao {

	public static void main(String[] args) {

		MontreDaoImpl mdao= new MontreDaoImpl();
		Montre mon= mdao.save(new Montre("casio",2800));
		System.out.println(mon);
		List<Montre> mons =mdao.montresParMC("rolex");
		for (Montre m : mons)
		System.out.println(m);
	}

}
