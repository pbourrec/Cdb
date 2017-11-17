package com.excilys.cdb.database.mvcservlets;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excils.cdb.database.config.ConfigSpring;
import com.excilys.cdb.database.datatype.ComputerDTO;
import com.excilys.cdb.database.service.Page;
import com.excilys.cdb.database.service.ServletServices;


@Controller
@RequestMapping("/dashboard")
public class DashboardServlet {

	private  Logger logger = LoggerFactory.getLogger(DashboardServlet.class);
	@Autowired
	private ServletServices servletServices;
	@Autowired
	private Page page;

	@GetMapping
	public ModelAndView doGet ( @RequestParam Map<String, String> parameters){
		logger.info("entrée dans la méthode doGet de DashboardServlet");
		ModelAndView modelAndView = new ModelAndView("/dashboard");
		
		String computerPerPageReciever = parameters.get("numberdisplay")!=null ? parameters.get("numberdisplay") : "";
		String operation = parameters.get("pageoperation")!=null ? parameters.get("pageoperation") : "";
		String pageChange = parameters.get("page")!=null ? parameters.get("page") : "";
		String restart =parameters.get("restart")!=null ? parameters.get("restart") : ""; 
		logger.info( computerPerPageReciever+"," +  operation+"," + pageChange+"," +  restart+"," );


		int sizeTable= servletServices.changePageFormat ( computerPerPageReciever,  operation, pageChange,  restart,  page) ;
		page.pageOfComputer = servletServices.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
		modelAndView.addObject("listComputer", page.pageOfComputer );
		modelAndView.addObject( "nextPage", page.getNextPageOK());
		modelAndView.addObject( "size", sizeTable);
		modelAndView.addObject("page", page.getOffsetPage());
		return modelAndView;
	}

	@PostMapping
	protected ModelAndView doPost( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/dashboard");

		String actionType = parameters.get("actionType");
		int sizeTable = servletServices.getSizeComputer();
		logger.info("entrée dans la méthode doGet de DashboardServlet");

		if(actionType.equals("delete")) {
			String idToDelete = parameters.get("selection");
			logger.info("Id to delete  " + idToDelete+", parameters.get()" + parameters.get("selection"));

			String computersDeleted = servletServices.deleteComputer(idToDelete);
			modelAndView.addObject("deleted", computersDeleted);
			page.pageOfComputer = servletServices.listPage(page.getOffsetPage()*page.getComputerPerPage(),page.getComputerPerPage());
			modelAndView.addObject("listComputer", page.pageOfComputer );
		} else if (actionType.equals("Filter by name")) {
			String nameToFind = parameters.get("search");
			List<ComputerDTO> computersToFind = servletServices.findComputersByName(nameToFind);
			modelAndView.addObject("listComputer", computersToFind );
		} else if (actionType.equals("Filter by company")) {
			String CompanyToFind = parameters.get("search");
			List<ComputerDTO> computersToFind = servletServices.findComputersByCompany(CompanyToFind);
			modelAndView.addObject("listComputer", computersToFind );
		}
		sizeTable= servletServices.getSizeComputer();
		modelAndView.addObject( "size", sizeTable);
		return modelAndView;

	}

}
