package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IMontreDao;
import dao.MontreDaoImpl;
import metier.entities.Montre;
@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IMontreDao metier;
@Override
public void init() throws ServletException {
metier = new MontreDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("montres.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
MontreModele model= new MontreModele();
model.setMotCle(motCle);
List<Montre> mon = metier.montresParMC(motCle);
model.setmontres(mon);
request.setAttribute("model", model);
request.getRequestDispatcher("montres.jsp").forward(request,response);
}

else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieMontre.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
double prix = Double.parseDouble(request.getParameter("prix"));
Montre m = metier.save(new Montre(nom,prix));
request.setAttribute("montre", m);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteMontre(id);
 response.sendRedirect("chercher.do?motCle=");
}

else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
Montre m = metier.getMontre(id);
 request.setAttribute("montre", m);
request.getRequestDispatcher("editerMontre.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
double prix = 
Double.parseDouble(request.getParameter("prix"));
Montre m = new Montre();
m.setIdMontre(id);
m.setNomMontre(nom);
m.setPrix(prix);
metier.updateMontre(m);
request.setAttribute("montre", m);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request, 
 HttpServletResponse response) throws 
ServletException, IOException {
doGet(request,response);
}

}

