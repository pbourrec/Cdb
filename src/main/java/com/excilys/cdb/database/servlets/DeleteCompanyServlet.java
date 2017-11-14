package com.excilys.cdb.database.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.excils.cdb.database.config.ConfigSpring;
import com.excilys.cdb.database.controller.ControlFormat;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class deleteCompany$
 */
@Controller
@WebServlet("/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {

	@Autowired
	private  ServletServices servletServices;
	@Autowired
	private  ControlFormat controlFormat;

	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	private static final long serialVersionUID = 1L;
	static Long companyId=0L;
	private static final String errorMessage ="Une erreur est survenue lors de la suppression de la companie. voir le message ci-dessous :" ;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompanies= servletServices.getListCompany();
		request.setAttribute( "listcompanies", listCompanies);

		this.getServletContext().getRequestDispatcher( "/deleteCompany.jsp" ).forward( request, response );
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String confirmation = request.getParameter("confirmation");
		if(confirmation==null) {	

			String companyIdString = request.getParameter("companyId");
			companyId=controlFormat.stringTolong(companyIdString);
			List<Computer> computersToFind = servletServices.findComputersByCompanyId(companyId);
			request.setAttribute("listComputer", computersToFind );
			List<Company> listCompanies= servletServices.getListCompany();
			request.setAttribute( "listcompanies", listCompanies);
			this.getServletContext().getRequestDispatcher( "/deleteCompany.jsp" ).forward( request, response );

		}else if(confirmation.equals("Delete!")) {
			try {
				servletServices.deleteComputerAndCompany(companyId);
				response.sendRedirect(this.getServletContext().getContextPath() + "/dashboard");

			} catch (SQLException e) {
				request.setAttribute( "errorMessage", errorMessage+"\n"+e.getMessage());
				List<Computer> computersToFind = servletServices.findComputersByCompanyId(companyId);
				request.setAttribute("listComputer", computersToFind );
				System.out.println(computersToFind.size());
				List<Company> listCompanies= servletServices.getListCompany();
				request.setAttribute( "listcompanies", listCompanies);
				this.getServletContext().getRequestDispatcher( "/deleteCompany.jsp" ).forward( request, response );
			}
		}
	}
}



