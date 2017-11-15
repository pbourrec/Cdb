package com.excilys.cdb.database.servlets;

import java.io.IOException;
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
import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.CompanyDTO;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@Controller
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String errorName = "Vous devez saisir un nom pour l'ordinateur à créer";
	@Autowired
	private ServletServices servletService;

	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanyDTO> listCompanies= servletService.getListCompany();
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
			List<CompanyDTO> listCompanies= servletService.getListCompany();
			request.setAttribute( "listcompanies", listCompanies);

			this.getServletContext().getRequestDispatcher( "/addComputer.jsp" ).forward( request, response );
		}else {
			servletService.addComputer(computerName, introduced, discontinued, companyId);
			response.sendRedirect(this.getServletContext().getContextPath() + "/dashboard");
		}
	}
}
