package fr.eni.servlets;

import java.io.IOException;
import java.util.List;

import fr.eni.beans.Voiture;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SupprimerServlet")
public class SupprimerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1-Lecture de l'identifiant à supprimer
		String immatriculation = request.getParameter("immatriculation");
		
		// 2-Supprission de la voiture
		Voiture voitureASupprimer = new Voiture();
		voitureASupprimer.setImmatriculation(immatriculation);
		List<Voiture> voitures = (List<Voiture>) this.getServletContext().getAttribute("voitures");
		voitures.remove(voitures.indexOf(voitureASupprimer));
		
		// 3-Transfert de la requette à la servlet ListerServlet pour l'affichage
		RequestDispatcher rd = request.getRequestDispatcher("/ListerServlet");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
