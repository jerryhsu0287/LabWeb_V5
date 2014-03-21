package model;

import java.util.Arrays;

import model.dao.CustomerDAOMysql;

public class CustomerService {
	// private CustomerDAO dao = new CustomerDAOJdbc();
	private CustomerDAO dao = new CustomerDAOMysql();

	public static void main(String[] args) {
		CustomerService service = new CustomerService();
		service.changePassword("Alex", "AAA", "A");
	}

	public boolean changePassword(String username, String oldPassword,
			String newPassword) {
		CustomerBean bean = this.login(username, oldPassword);
		if (bean != null) {
			byte[] temp = newPassword.getBytes();
			return dao.update(temp, bean.getEmail(), bean.getBirth(), username);
		}
		return false;
	}

	public CustomerBean login(String username, String password) {
		CustomerBean bean = dao.select(username);
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] temp = bean.getPassword(); // database
				byte[] pass = password.getBytes(); // user input
				if (Arrays.equals(temp, pass)) {
					return bean;
				}
			}
		}
		return null;
	}
}
