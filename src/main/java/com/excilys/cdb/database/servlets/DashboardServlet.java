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
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import com.excils.cdb.database.config.ConfigSpring;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.service.Page;
import com.excilys.cdb.database.service.ServletServices;


@Controller
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ServletServices servletServices;
	@Autowired
	private Page page;

	public void init() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
		context.getAutowireCapableBeanFactory().autowireBean(this);
	}

	public void doGet ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		String computerPerPageReciever = request.getParameter("numberdisplay");
		String operation = request.getParameter("pageoperation");
		String pageChange = request.getParameter("page");
		String restart = request.getParameter("restart");



		int sizeTable= servletServices.changePageFormat ( computerPerPageReciever,  operation, pageChange,  restart,  page) ;
		page.pageOfComputer = servletServices.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
		request.setAttribute("listComputer", page.pageOfComputer );
		request.setAttribute( "nextPage", page.getNextPageOK());
		request.setAttribute( "size", sizeTable);
		request.setAttribute("page", page.getOffsetPage());
		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		int sizeTable = servletServices.getSizeComputer();
		if(actionType.equals("delete")) {
			String idToDelete = request.getParameter("selection");
			String computersDeleted = servletServices.deleteComputer(idToDelete);
			request.setAttribute("deleted", computersDeleted);
			page.pageOfComputer = servletServices.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
			request.setAttribute("listComputer", page.pageOfComputer );
		} else if (actionType.equals("Filter by name")) {
			String nameToFind = request.getParameter("search");
			List<Computer> computersToFind = servletServices.findComputersByName(nameToFind);
			request.setAttribute("listComputer", computersToFind );
		} else if (actionType.equals("Filter by company")) {
			String CompanyToFind = request.getParameter("search");
			List<Computer> computersToFind = servletServices.findComputersByCompany(CompanyToFind);
			request.setAttribute("listComputer", computersToFind );
		}
		sizeTable= servletServices.getSizeComputer();
		request.setAttribute( "size", sizeTable);
		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );


	}

}
