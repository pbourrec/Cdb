package com.excilys.cdb.database.mvcservlets;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.database.datatype.CompanyDTO;
import com.excilys.cdb.database.datatype.Computer;
import com.excilys.cdb.database.datatype.ComputerDTO;
import com.excilys.cdb.database.mapper.ComputerMapper;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class AddComputerServlet
 */
@RequestMapping("/editComputer")
@Controller
public class EditComputerServlet{
	
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private  ServletServices servletService;

	@GetMapping
	public ModelAndView doGet ( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/editComputer");

		List<CompanyDTO> listCompanies= servletService.getListCompany();
		modelAndView.addObject( "listcompanies", listCompanies);

		String computerId = parameters.get("computerid");

		ComputerDTO computerToEdit = servletService.queryOne(computerId);
		modelAndView.addObject( "computer",computerToEdit);
		System.out.println(computerToEdit.toString());
		return modelAndView;
	}





	@PostMapping
	public ModelAndView doPost ( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/editComputer");
		
		String computerId = parameters.get("computerid");
		String computerName = parameters.get("computerName");
		String introduced = parameters.get("introduced");
		System.out.println("la date intro est  :  "+ introduced);
		String discontinued = parameters.get("discontinued");
		System.out.println("la date fin est  :  " + discontinued);
		String companyId = parameters.get("companyId");

		Computer computerToAdd = new Computer();
		computerToAdd = computerMapper.computerBuilder(computerName, companyId, introduced, discontinued);
		System.out.println(computerToAdd.toString());
		servletService.updateComputer(computerId, computerToAdd);
		return modelAndView;
	}





}
