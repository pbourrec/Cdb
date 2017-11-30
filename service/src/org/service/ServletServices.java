package org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import org.model.Computer;
import org.webapp.Page;
import org.persistence.CompanyRepository;
import org.persistence.ComputerRepository;
import org.mapper.CompanyMapper;
import org.mapper.ComputerMapper;

@Service 
public class ServletServices {
	@Autowired
	private ComputerRepository computerJpaDao;
	@Autowired
	private CompanyRepository companyJpaDao;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	public CompanyService companyService;


	public  int changePageFormat (String computerPerPageReciever, String operation,String pageChange, String restart, Page page) {

		int sizeTable= (int) computerJpaDao.count();
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
		List<Computer> listComputer = computerJpaDao.getComputerPaginationJpa(offset, limit);
		return listComputer;
	}

}
