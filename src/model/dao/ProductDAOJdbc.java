package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOJdbc implements ProductDAO {
	private static final String URL = "jdbc:sqlserver://localhost:1433;database=JDBC";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";

	private static final String SELECT_BY_ID = "select * from product where id = ?";
	@Override
	public ProductBean select(int id) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, id);
			rset = stmt.executeQuery();
			if(rset.next()) {
				result = new ProductBean();
				result.setId(rset.getInt("id"));
				result.setName(rset.getString("name"));
				result.setPrice(rset.getDouble("price"));
				result.setMake(rset.getDate("make"));
				result.setExpire(rset.getInt("expire"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String SELECT_ALL = "select * from product";
	@Override
	public List<ProductBean> select() {
		List<ProductBean> result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();

			result = new ArrayList<ProductBean>();
			while(rset.next()) {
				ProductBean temp = new ProductBean();
				temp.setId(rset.getInt("id"));
				temp.setName(rset.getString("name"));
				temp.setPrice(rset.getDouble("price"));
				temp.setMake(rset.getDate("make"));
				temp.setExpire(rset.getInt("expire"));
				
				result.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String DELETE = "delete from product where id=?";
	@Override
	public boolean delete(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, id);
			int i = stmt.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	private static final String UPDATE =
			"update product set name=?, price=?, make=?, expire=? where id=?";
	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(UPDATE);
			
			stmt.setString(1, name);
			stmt.setDouble(2, price);
			if(make!=null) {
				long temp = make.getTime();
				stmt.setDate(3, new java.sql.Date(temp));
			} else {
				stmt.setDate(3, null);
			}
			stmt.setInt(4, expire);
			stmt.setInt(5, id);
			
			int i = stmt.executeUpdate();
			if(i==1) {
				result = new ProductBean();
				result.setId(id);
				result.setName(name);
				result.setPrice(price);
				result.setMake(make);
				result.setExpire(expire);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String INSERT = 
			"insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?) ";
	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.prepareStatement(INSERT);
			
			stmt.setInt(1, bean.getId());
			stmt.setString(2, bean.getName());
			stmt.setDouble(3, bean.getPrice());
			java.util.Date make = bean.getMake();
			if(make!=null) {
				long temp = make.getTime();
				stmt.setDate(4, new java.sql.Date(temp));
			} else {
				stmt.setDate(4, null);
			}
			stmt.setInt(5, bean.getExpire());
			
			int i = stmt.executeUpdate();
			if(i==1) {
				result = this.select(bean.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
