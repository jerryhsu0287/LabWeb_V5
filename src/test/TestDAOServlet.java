package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerBean;
import model.CustomerService;
import model.ProductBean;
import model.ProductService;

@WebServlet("/TestDAOServlet")
public class TestDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestDAOServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ProductBean bean = new ProductBean();
		bean.setId(1);
		List<ProductBean> result = new ProductService().select(bean);
		for(ProductBean b : result)
			out.println(b);
		out.println("testing!");
		CustomerBean cBean = new CustomerService().login("Alex", "A");
		out.println(cBean);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
