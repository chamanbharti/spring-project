package com.spring.angular.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//This configuration class can be treated as a replacement of web.xml as it contains all the information for the front-controller DispatherServler, assigning the mapping (url-pattern in xml).
public class WebContainerInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext context) throws ServletException {
		
		System.out.println("########### WebContainerInitializer onStartup Initilization###################");
		
		AnnotationConfigWebApplicationContext ctx=new AnnotationConfigWebApplicationContext();
		ctx.register(WebConfiguration.class);
		ctx.setServletContext(context);
		
		ServletRegistration.Dynamic servlet=context.addServlet("dispatcher", new DispatcherServlet(ctx));
		
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");
	}

}
//UPDATE: Note that now we can write the above class even more concisely [and it�s the preferred way]
/*public class WebContainerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {WebConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
}*/