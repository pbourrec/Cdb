package com.excilys.cdb.database.mvcservlets;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.cdb.database.controller.ControlFormat;
import com.excilys.cdb.database.core.CompanyDTO;
import com.excilys.cdb.database.core.ComputerDTO;
import com.excilys.cdb.database.service.ServletServices;

/**
 * Servlet implementation class deleteCompany$
 */
@Controller
@RequestMapping("/deleteCompany")
public class DeleteCompanyServlet {

	@Autowired
	private  ServletServices servletServices;
	@Autowired
	private  ControlFormat controlFormat;

	private static final long serialVersionUID = 1L;
	static Long companyId=0L;
	private static final String errorMessage ="Une erreur est survenue lors de la suppression de la companie. voir le message ci-dessous :" ;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@GetMapping
	protected ModelAndView doGet( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/deleteCompany");		List<CompanyDTO> listCompanies= servletServices.getListCompany();
		modelAndView.addObject( "listcompanies", listCompanies);

		return modelAndView;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@PostMapping
	protected ModelAndView doPost( @RequestParam Map<String, String> parameters){
		ModelAndView modelAndView = new ModelAndView("/deleteCompany");
		String confirmation = parameters.get("confirmation");
		if(confirmation==null) {	

			String companyIdString = parameters.get("companyId");
			companyId=controlFormat.stringTolong(companyIdString);
			List<ComputerDTO> computersToFind = servletServices.findComputersByCompanyId(companyId);
			modelAndView.addObject("listComputer", computersToFind );
			List<CompanyDTO> listCompanies= servletServices.getListCompany();
			modelAndView.addObject( "listcompanies", listCompanies);
			return modelAndView;
		}else if(confirmation.equals("Delete!")) {
			try {
				servletServices.deleteComputerAndCompany(companyId);
				return modelAndView = new ModelAndView("/deleteCompany");
			} catch (SQLException e) {
				modelAndView.addObject( "errorMessage", errorMessage+"\n"+e.getMessage());
				List<ComputerDTO> computersToFind = servletServices.findComputersByCompanyId(companyId);
				modelAndView.addObject("listComputer", computersToFind );
				System.out.println(computersToFind.size());
				List<CompanyDTO> listCompanies= servletServices.getListCompany();
				modelAndView.addObject( "listcompanies", listCompanies);
				return modelAndView;
			}
		}
		return modelAndView;
	}
}



