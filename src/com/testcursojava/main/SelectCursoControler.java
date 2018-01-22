package com.testcursojava.main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Centro;
import model.cursos.Curso;

/**
 * Servlet implementation class SelectCursoControler
 */
@WebServlet("/SelectCursoControler")
public class SelectCursoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCursoControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Curso c = Centro.getCurso(request.getParameter("p"));
		request.getSession().setAttribute("curso", c);
		request.setAttribute("idcurso", c.getId());
	}

}
