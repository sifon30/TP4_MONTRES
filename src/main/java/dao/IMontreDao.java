package dao;

import java.util.List;

import metier.entities.Montre;
public interface IMontreDao {
public Montre save(Montre m);
public List<Montre> montresParMC(String mc);
public Montre getMontre(Long id);
public Montre updateMontre(Montre m);
public void deleteMontre(Long id);
}