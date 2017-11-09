package com.excilys.cdb.database.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.service.Page;
import com.excilys.cdb.database.service.ServletServices;

public class DashboardServlet extends HttpServlet {

	static Page page = new Page();
	@SuppressWarnings("static-access")
	@Override
	public void doGet ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		String computerPerPageReciever = request.getParameter("numberdisplay");
		String operation = request.getParameter("pageoperation");
		String pageChange = request.getParameter("page");
		String restart = request.getParameter("restart");



		int sizeTable= ServletServices.changePageFormat ( computerPerPageReciever,  operation, pageChange,  restart,  page) ;
		page.pageOfComputer = Page.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
		request.setAttribute("listComputer", page.pageOfComputer );
		request.setAttribute( "nextPage", page.getNextPageOK());
		request.setAttribute( "size", sizeTable);
		request.setAttribute("page", page.getOffsetPage());
		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
	}


	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		int sizeTable= ComputerDAO.getSizeComputer();
		String operation;
		if(actionType.equals("delete")) {
			String idToDelete = request.getParameter("selection");
			String computersDeleted = ServletServices.servletDeleteComputer(idToDelete);
			request.setAttribute("deleted", computersDeleted);
			page.pageOfComputer = Page.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
			request.setAttribute("listComputer", page.pageOfComputer );
		} else if (actionType.equals("Filter by name")) {
			String nameToFind = request.getParameter("search");
			List<Computer> computersToFind = ServletServices.findComputersByName(nameToFind);
			request.setAttribute("listComputer", computersToFind );
		} else if (actionType.equals("Filter by company")) {
			String CompanyToFind = request.getParameter("search");
			List<Computer> computersToFind = ServletServices.findComputersByCompany(CompanyToFind);
			request.setAttribute("listComputer", computersToFind );
		}
		sizeTable= ComputerDAO.getSizeComputer();
		request.setAttribute( "size", sizeTable);
		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );


	}

}
