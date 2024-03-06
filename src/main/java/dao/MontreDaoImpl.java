package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Montre;
public class MontreDaoImpl implements IMontreDao {
	
@Override
public Montre save(Montre m) {
Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("INSERT INTO  MONTRES(NOM_MONTRE,PRIX) VALUES(?,?)");
ps.setString(1, m.getNomMontre());
ps.setDouble(2, m.getPrix());
ps.executeUpdate();
PreparedStatement ps2= conn.prepareStatement
("SELECT MAX(ID_MONTRE) as MAX_ID FROM  MONTRES");
ResultSet rs =ps2.executeQuery();
if (rs.next()) {
m.setIdMontre(rs.getLong("MAX_ID"));
}
ps.close();
ps2.close();
} catch (SQLException e) {
e.printStackTrace();
}
return m;
}
@Override
public List<Montre> montresParMC(String mc) {
 List<Montre> mon= new ArrayList<Montre>();
 Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("select * from  Montres where NOM_MONTRE LIKE ?");
ps.setString(1, "%"+mc+"%");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
	Montre m = new Montre();
m.setIdMontre(rs.getLong("ID_MONTRE"));
m.setNomMontre(rs.getString("NOM_MONTRE"));
m.setPrix(rs.getDouble("PRIX"));
mon.add(m);
}
} catch (SQLException e) {
e.printStackTrace();
}
return mon;
}


@Override
public Montre getMontre(Long id) {
 
 Connection conn=SingletonConnection.getConnection();
 Montre m = new Montre();
 try {
PreparedStatement ps= conn.prepareStatement("select * from  MONTRES where ID_MONTRE = ?");
ps.setLong(1, id);
ResultSet rs = ps.executeQuery();
if (rs.next()) {
m.setIdMontre(rs.getLong("ID_MONTRE"));
m.setNomMontre(rs.getString("NOM_MONTRE"));
m.setPrix(rs.getDouble("PRIX"));
}
} catch (SQLException e) {
e.printStackTrace();
}
return m;
}
@Override
public Montre updateMontre(Montre m) {
Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("UPDATE MONTRES  SET NOM_MONTRE=?,PRIX=? WHERE ID_MONTRE=?");
ps.setString(1, m.getNomMontre());
ps.setDouble(2, m.getPrix());

ps.setLong(3, m.getIdMontre());
ps.executeUpdate();
ps.close();
} catch (SQLException e) {
e.printStackTrace();
}
return m;
}
@Override
public void deleteMontre(Long id) {
Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("DELETE FROM  MONTRES WHERE ID_MONTRE = ?");
ps.setLong(1, id);
ps.executeUpdate();
ps.close();
} catch (SQLException e) {
e.printStackTrace();
}
}
}
