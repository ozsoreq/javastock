package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Project1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		int num1=7, num2=3, num3=4;
		int result = (num2+num3)*num1;
		resp.getWriter().print("The result of "+num1+"*("+num2+"+"+num3+")="+result);
		//changing
	}
}
