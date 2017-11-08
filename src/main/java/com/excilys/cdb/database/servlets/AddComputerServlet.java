package com.excilys.cdb.database.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompanies= ServletServices.getListCompany();
		request.setAttribute( "listcompanies", listCompanies);

		this.getServletContext().getRequestDispatcher( "/addComputer.jsp" ).forward( request, response );
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");
		if (computerName.equals("")) {
			request.setAttribute("errorName", errorName);
			List<Company> listCompanies= ServletServices.getListCompany();
			request.setAttribute( "listcompanies", listCompanies);

			this.getServletContext().getRequestDispatcher( "/addComputer.jsp" ).forward( request, response );
		}else {
			ServletServices.addComputer(computerName, introduced, discontinued, companyId);
			response.sendRedirect(this.getServletContext().getContextPath() + "/dashboard");
		}
	}
}
