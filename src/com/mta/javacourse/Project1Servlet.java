package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;
import java.util.Date;

@SuppressWarnings("serial")
public class Project1Servlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		// Ex 2
		int num1=7, num2=3, num3=4;
		int result = (num2+num3)*num1;
		resp.getWriter().println("EX2 : The result of "+num1+"*("+num2+"+"+num3+")="+result+"<br>");
		// Ex 3
		double pi = Math.PI;
		double radius = 50;
		double degrees = 30;
		double hypotenuse = 50;
		double radian = Math.toRadians(degrees);
		double res = Math.sin(radian)*hypotenuse;
		resp.getWriter().println("EX3.1 : Area of circle with radius " + radius +" is: " + pi*radius*radius +" squarecm. "+"<br>");
		resp.getWriter().println("EX3.2 : Length of opposite where angle B is 30 degrees and Hypotenuse is 50 is: " + res + "cm"+"<br>");
		resp.getWriter().println("EX3.3 : Power of 20 with exp of 13 is " + Math.pow(20, 13)+"<br><br>");
		// Ex 4
		resp.getWriter().println("<b>Exercise 4 in Stock Details</b><br>");
		
	}

}
