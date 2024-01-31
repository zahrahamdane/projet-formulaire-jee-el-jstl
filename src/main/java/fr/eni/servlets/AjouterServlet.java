package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.beans.Voiture;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AjouterServlet")
public class AjouterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private List<Voiture> voitures;

	@Override
	public void init() throws ServletException {
		// this.voitures = new ArrayList<>();
		this.getServletContext().setAttribute("voitures", Collections.synchronizedList(new ArrayList<>()));
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajouterVoiture.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Voiture nouvelleVoiture = Validation.validerSaisie(request);
		
		if(nouvelleVoiture != null) {
			List<Voiture> voitures = (List<Voiture>) this.getServletContext().getAttribute("voitures");
			voitures.add(nouvelleVoiture);
			response.sendRedirect(request.getContextPath()+"/ListerServlet");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajouterVoiture.jsp");
			rd.forward(request, response);
		}
	}

}
