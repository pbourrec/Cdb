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
	static Long computerPerPage=50L; 
	static Long offsetPage=0L;
	static Page page;
	@Override
	public void doGet ( HttpServletRequest request, HttpServletResponse response )	throws ServletException, IOException {
		int sizeTable= ComputerDAO.databaseGetSizeComputer();

		String computerPerPageReciever = request.getParameter("numberdisplay");
		String operation = request.getParameter("pageoperation");
		String pageChange = request.getParameter("page");
		String restart = request.getParameter("restart");
		int nextPageOK = 0;
		if(computerPerPageReciever!=null) {
			computerPerPage = Long.valueOf(computerPerPageReciever);
			offsetPage=0L;
		} else if (operation!=null && offsetPage+Long.valueOf(operation)>=0 && ((offsetPage+Long.valueOf(operation))*computerPerPage)<sizeTable){
			offsetPage = (Long.valueOf(operation)+offsetPage);
		} else if (pageChange!=null && Long.valueOf(pageChange)*computerPerPage<sizeTable){
			offsetPage=Long.valueOf(pageChange);
		}else if(restart!=null) {
			computerPerPage=50L;
			offsetPage=0L;
		}
		if(((offsetPage+1)*computerPerPage)<sizeTable){
			nextPageOK=1;
			if(((offsetPage+2)*computerPerPage)<sizeTable) {
				nextPageOK=2;

			}
		}else {
			nextPageOK=-1;
		}


		page.pageOfComputer = Page.listPage(offsetPage*computerPerPage,computerPerPage);
		request.setAttribute("listComputer", page.pageOfComputer );

		request.setAttribute( "nextPage", nextPageOK);
		request.setAttribute( "size", sizeTable);
		request.setAttribute("page", offsetPage);
		this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionType = request.getParameter("actionType");
		int sizeTable= ComputerDAO.databaseGetSizeComputer();
		String operation;
		
		if(actionType.equals("delete")) {
			String idToDelete = request.getParameter("selection");
			String[] listIdToDelete = idToDelete.split(",");
			String computersDeleted ="Les ordinateurs suivants on étés supprimés : \n";
			for(int i=0; i< listIdToDelete.length; i++) {
				ComputerDAO.databaseDelete(Long.valueOf(listIdToDelete[i]));
				computersDeleted+=" " +listIdToDelete[i] + ",";
			}
			computersDeleted = computersDeleted.substring(0, computersDeleted.length() - 1);
			request.setAttribute("deleted", computersDeleted);

			page.pageOfComputer = Page.listPage(offsetPage*computerPerPage,computerPerPage);
			request.setAttribute("listComputer", page.pageOfComputer );

			sizeTable= ComputerDAO.databaseGetSizeComputer();
			request.setAttribute( "size", sizeTable);

			this.getServletContext().getRequestDispatcher( "/dashboard.jsp" ).forward( request, response );
		} 



	}
}
