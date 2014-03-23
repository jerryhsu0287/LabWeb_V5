package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService ps = new ProductService();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// new a Map obj for error msg
		Map<String, String> errors = new HashMap<String, String>();
		// set the error msg into request attribute
		request.setAttribute("errors", errors);
		// get data
		String tempId = request.getParameter("id");
		String name = request.getParameter("name");
		String tempPrice = request.getParameter("price");
		String tempMake = request.getParameter("make");
		String tempExpire = request.getParameter("expire");
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
		// id
		int id = 0;
		if (tempId != null && tempId.trim().length() != 0) {
			id = ProductBean.convertInt(tempId);
			if (id == -1000) {
				errors.put("id", "id must be an interger");
			}
		}

		// price
		double price = 0;

		if (tempPrice != null && tempPrice.trim().length() != 0) {
			price = ProductBean.convertDouble(tempPrice);
			if (price == -1000) {
				errors.put("price", "price must be a number");
			}
		}

		// make
		java.util.Date make = null;

		if (tempMake != null && tempMake.trim().length() != 0) {
			make = ProductBean.convertDate(tempMake);
			if (make.equals(new java.util.Date(0))) {
				errors.put("make", "make must be in the form yyyy-MM-dd");
			}
		}

		// expire
		int expire = 0;

		if (tempExpire != null && tempId.trim().length() != 0) {
			expire = ProductBean.convertInt(tempExpire);
			if (id == -1000) {
				errors.put("expire", "expire must be an interger");
			}
		}

		// if there's any error, back to the product page
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
		}

		// start calling model and redirect to the corresponding page

		ProductBean bean = new ProductBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setMake(make);
		bean.setExpire(expire);

		if (prodaction != null && prodaction.equals("Select")) {
			List<ProductBean> result = ps.select(bean);
			request.setAttribute("select", result);
			request.getRequestDispatcher("/pages/display.jsp").forward(request,
					response);
		} else if (prodaction != null && prodaction.equals("Insert")) {
			if (ps.select(bean) != null) {
				errors.put("result", "duplicated ID for insert");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
				return;
			}

			ProductBean rBean = ps.insert(bean);
			if (rBean != null) {
				errors.put("result", "Insert Successed");
				request.setAttribute("result", rBean);
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			} else {
				errors.put("result", "Insert Failed");
			}
		} else if (prodaction != null && prodaction.equals("Update")) {
			
			if(ps.select(bean) == null)
			{
				errors.put("result", "Invalid ID for update");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
				return;
			}
			ProductBean rBean = ps.update(bean);
			errors.put("result", "Update Successed");
			request.setAttribute("result", rBean);
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
		} else if (prodaction != null && prodaction.equals("Delete")) {
			boolean dResult = ps.delete(bean);
			if (dResult) {
				request.setAttribute("delete", "Delete Successed");
			} else {
				request.setAttribute("delete", "Delete Failed");
			}
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
