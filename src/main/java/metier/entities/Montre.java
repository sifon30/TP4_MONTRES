package metier.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "MONTRES")
public class Montre implements Serializable {
	@Id
	@Column (name="ID_MONTRE")

	private Long idMontre;
	@Id
	@Column (name="NOM_MONTRE")
	private String nomMontre;
	private double prix;


	public Montre() {
		super();
		}
		public Montre(String nomMontre, double prix) {
		super();
		this.nomMontre = nomMontre;
		this.prix = prix;
		}
		public Long getIdMontre() {
		return idMontre;
		}
		public void setIdMontre(Long idMontre) {
		this.idMontre = idMontre;
		}
		public String getNomMontre() {
		return nomMontre;
		}
		public void setNomMontre(String nomMontre) {
		this.nomMontre = nomMontre;
		}
		public double getPrix() {
		return prix;
		}
		public void setPrix(double prix) {
		this.prix = prix;
		}
		@Override
		public String toString() {
		return "Montre [idMontre=" + idMontre + ", nomMontre=" + 
		nomMontre + ", prix=" + prix + "]";
		}
		}

