package com.excilys.cdb.database.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.database.dao.ComputerDAO;

public class DashboardServlet extends HttpServlet {

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {

		int sizeTable= ComputerDAO.databaseGetSizeComputer();
		System.out.println("taille de table  : " + sizeTable);

		request.setAttribute( "size", sizeTable);

		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
	}
}
