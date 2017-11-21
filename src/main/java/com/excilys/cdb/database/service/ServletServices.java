package com.excilys.cdb.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.database.core.Company;
import com.excilys.cdb.database.core.Computer;
import com.excilys.cdb.database.core.Page;
import com.excilys.cdb.database.dao.CompanyDAO;
import com.excilys.cdb.database.dao.ComputerDAO;
import com.excilys.cdb.database.mapperdao.CompanyMapper;
import com.excilys.cdb.database.mapperdao.ComputerMapper;

@Service 
public class ServletServices {
	@Autowired
	private ComputerDAO computerDao;
	@Autowired CompanyDAO companyDao;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	public CompanyService companyService;


	public  int changePageFormat (String computerPerPageReciever, String operation,String pageChange, String restart, Page page) {

		int sizeTable= computerDao.getSizeComputer();
		if(!computerPerPageReciever.equals("")) {
			page.setComputerPerPage( Long.valueOf(computerPerPageReciever));
			page.setOffsetPage(0L);
		} else if (!operation.equals("") && 
				page.getOffsetPage()+Long.valueOf(operation)>=0 && 
				((page.getOffsetPage()+Long.valueOf(operation))*page.getComputerPerPage())<sizeTable){
			page.setOffsetPage((Long.valueOf(operation)+page.getOffsetPage()));
		} else if (!pageChange.equals("") && Long.valueOf(pageChange)*page.getComputerPerPage()<sizeTable){
			page.setOffsetPage(Long.valueOf(pageChange));
		}else if(!restart.equals("")) {
			page.setComputerPerPage(50L);
			page.setOffsetPage(0L);
		}
		if(((page.getOffsetPage()+1)*page.getComputerPerPage())<sizeTable){
			page.setNextPageOK(1);
			if(((page.getOffsetPage()+2)*page.getComputerPerPage())<sizeTable) {
				page.setNextPageOK(2);

			}
		}else {
			page.setNextPageOK(-1);
		}
		return sizeTable;
	}


	public  List<Computer>  listPage(Page page) {
		Long offset = page.getOffsetPage()*page.getComputerPerPage();
		Long limit =page.getComputerPerPage();
		List<Computer> listComputer = computerDao.getComputerPagination(offset, limit);
		return listComputer;
	}

}
