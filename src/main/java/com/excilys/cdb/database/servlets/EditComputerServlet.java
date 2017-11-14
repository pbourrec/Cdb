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
import org.springframework.stereotype.Controller;

import com.excils.cdb.database.config.ConfigSpring;
import com.excilys.cdb.database.datatype.Company;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/EditComputerServlet")
@Controller
public class EditComputerServlet extends HttpServlet {
	
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private  ServletServices servletService;
	

	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}


	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompanies= servletService.getListCompany();
		request.setAttribute( "listcompanies", listCompanies);

		String computerId = request.getParameter("computerid");

		Computer computerToEdit = servletService.queryOne(computerId);
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
		computerToAdd = computerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		System.out.println(computerToAdd.toString());
		servletService.updateComputer(computerId, computerToAdd);
		response.sendRedirect(this.getServletContext().getContextPath() + "/dashboard");
	}





}
