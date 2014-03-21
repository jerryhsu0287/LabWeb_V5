package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// new a Map obj for error msg
		Map<String, String> errors = new HashMap<String, String>();
		// get data
		String tempId = request.getParameter("id");
		String name = request.getParameter("name");
		String tempPrice = request.getParameter("price");
		String make = request.getParameter("make");
		String expire = request.getParameter("expire");
		String prodaction = request.getParameter("prodaction");

		// validate data
		if (prodaction != null) {
			if (prodaction.equals("Insert") || prodaction.equals("Update")
					|| prodaction.equals("Delete")) {
				if (tempId.trim().length() == 0) {
					errors.put("id", "please enter ID for " + prodaction);
				}
			}
		}
		// convert data

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
