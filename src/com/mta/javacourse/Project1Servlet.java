package com.mta.javacourse;
import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Project1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		// Ex 1
		int num1=7, num2=3, num3=4;
		int result = (num2+num3)*num1;
		resp.getWriter().println("EX1 : The result of "+num1+"*("+num2+"+"+num3+")="+result);
		// Ex 2
		double pi = Math.PI;
		double radius = 50;
		double degrees = 30;
		double hypotenuse = 50;
		double radian = Math.toRadians(degrees);
		double res = Math.sin(radian)*hypotenuse;
		resp.getWriter().println("EX2.1 : Area of circle with radius " + radius +" is: " + pi*radius*radius +" squarecm. ");
		resp.getWriter().println("EX2.2 : Length of opposite where angle B is 30 degrees and Hypotenuse is 50 is: " + res + "cm");
		resp.getWriter().println("EX2.3 : Power of 20 with exp of 13 is " + Math.pow(20, 13));
	}
}
