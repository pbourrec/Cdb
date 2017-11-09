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

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompanies= CompanyDAO.getCompany();
		request.setAttribute( "listcompanies", listCompanies);
		
		String computerId = request.getParameter("computerid");
		
		Computer computerToEdit = ComputerDAO.queryOne(computerId !=null ? Long.valueOf(computerId) : 0L);
		request.setAttribute( "computer",computerToEdit);
		System.out.println(computerToEdit.toString());
		this.getServletContext().getRequestDispatcher( "/editComputer.jsp" ).forward( request, response );
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String computerId = request.getParameter("computerid");
		String computerName = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		System.out.println("la date intro est  :  "+ introduced);
		String discontinued = request.getParameter("discontinued");
		System.out.println("la date fin est  :  " + discontinued);
		String companyId = request.getParameter("companyId");
		
		Computer computerToAdd = new Computer();
		computerToAdd = ComputerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		System.out.println(computerToAdd.toString());
		ComputerDAO.update(computerToAdd, Long.valueOf(computerId));
		response.sendRedirect(this.getServletContext().getContextPath() + "/dashboard");
	}

}
