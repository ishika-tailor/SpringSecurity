package com.ishika.ProductManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

	@Autowired
	private Productservice service;
	
	@RequestMapping("/")
	public String ViewHomePage(Model m) {
		List<Product> listproduct=service.listAll();
		m.addAttribute("listproduct",listproduct);
		return "index";
	}
	@RequestMapping("/new")
	public String AddProduct(Model m) {
		Product product=new Product();
		m.addAttribute("product",product);
		return "new_product";
	}
	@RequestMapping(value="/save")
	public String SaveProduct(@ModelAttribute("product") Product product) {
		
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView EditProduct(@PathVariable(name="id") Long id) {
		
		ModelAndView mav=new ModelAndView("edit_product");
		Product product=service.get(id);
		mav.addObject("product",product);
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String DeleteProduct(@PathVariable(name="id") Long id) {
		
		service.delete(id);
		return "redirect:/";
	}
}
