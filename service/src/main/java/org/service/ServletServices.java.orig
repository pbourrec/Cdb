package org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.persistence.CompanyRepository;
import org.persistence.ComputerRepository;

@Service 
public class ServletServices {
	@Autowired
	public ComputerRepository computerJpaDao;
	@Autowired
	private CompanyRepository companyJpaDao;
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

}
