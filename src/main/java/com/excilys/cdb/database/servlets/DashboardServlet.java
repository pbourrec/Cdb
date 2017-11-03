package com.excilys.cdb.database.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.database.controller.ControlFormat;
import com.excilys.cdb.database.controller.DataControl;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.dao.DatabaseConn;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.mapper.ComputerMapper;
import com.excilys.cdb.database.service.Page;

public class DashboardServlet extends HttpServlet {
	static String buttonValue; 
	static Long page;
	static String operation;
	@Override
	public void doGet ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		buttonValue = request.getParameter("numberdisplay");
		operation = request.getParameter("page");
		
		List<Computer> listComputer= null;
		int sizeTable= ComputerDAO.databaseGetSizeComputer();
		page = (operation !=null ? Long.valueOf(operation)+page : 0);
		listComputer= Page.listPage(operation !=null && Long.valueOf(operation)>1 ? Long.valueOf(operation) : page!=0? page*50 : 0L, 50L );
		request.setAttribute("listComputer", listComputer);
		request.setAttribute( "size", sizeTable);

		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sizeTable= ComputerDAO.databaseGetSizeComputer();

		List<Computer> listComputer= Page.listPage(0L,ControlFormat.stringTolong(buttonValue));	


		request.setAttribute("listComputer", listComputer);
		request.setAttribute( "size", sizeTable);

		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );

	}
}
