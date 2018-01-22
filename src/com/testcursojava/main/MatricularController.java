package com.testcursojava.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.cursos.Curso;

/**
 * Servlet implementation class MatricularController
 */
@WebServlet("/MatricularController")
public class MatricularController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatricularController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Matricular.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso c = (Curso) request.getSession().getAttribute("curso");
		if(c.matricula(request.getParameter("nm"), request.getParameter("sn"), request.getParameter("nif"), request.getParameter("fn")))
			response.sendRedirect("ListarController");
	}

}
