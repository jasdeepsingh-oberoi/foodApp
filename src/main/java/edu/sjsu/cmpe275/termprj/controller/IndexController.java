package edu.sjsu.cmpe275.termprj.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model; 
@Controller
public class IndexController {
 
      @RequestMapping(value ="/", method = RequestMethod.GET)
        public String getIndexPage() {
            //return "UserManagement";
    	  return "welcome";
        }
      
      @RequestMapping(value ="/verification", method = RequestMethod.GET)
      public ModelAndView getVerificationPage(Model model) {
          //return "UserManagement";
    	  return new ModelAndView( "verificationPage" ); 
      }
      
      @RequestMapping(value ="/dashboard", method = RequestMethod.GET)
      public ModelAndView getMenuPage(Model model) {
          //return "UserManagement";
    	  return new ModelAndView( "dashboard" ); 
      }
      
      @RequestMapping(value ="/adminDashboard", method = RequestMethod.GET)
      public ModelAndView getAdminPage(Model model) {
          //return "UserManagement";
    	  return new ModelAndView( "adminDashboard" ); 
      }
}


