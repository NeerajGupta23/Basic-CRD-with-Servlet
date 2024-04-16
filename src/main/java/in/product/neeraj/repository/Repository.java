package in.product.neeraj.repository;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import in.product.neeraj.dto.Product;
import in.product.neeraj.util.Utility;

public class Repository {

	private String SQL_INSERT_QUERY = "insert into product(pname, pcost) values('%s', %f)";

	public boolean insertProduct(Product product, HashMap<String, String> databaseData) {
		Connection connection = null;
		Statement statement = null;
		Boolean updated = false;

		try {
			connection = Utility.getConnection(databaseData);
			statement = connection.createStatement();

			int updatedRows = statement
					.executeUpdate(String.format(SQL_INSERT_QUERY, product.getpName(), product.getpCost()));

			if (updatedRows > 0) {
				updated = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection, statement);
		}

		return updated;
	}

	private final String SQL_DELETE_QUERY = "delete from product where pname = '%s'";

	public boolean deleteProduct(String pName, HashMap<String, String> databaseData) {
		Connection connection = null;
		Statement statement = null;
		Boolean updated = false;

		try {
			connection = Utility.getConnection(databaseData);
			statement = connection.createStatement();

			int count = statement.executeUpdate(String.format(SQL_DELETE_QUERY, pName));
			if (count > 0) {
				updated = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection, statement);
		}

		return updated;
	}

	private final String SQL_SELECT_QUERY = "select pname, pcost from product";

	public void selectProduct(HashMap<String, String> databaseData, HttpServletResponse response) {
		ResultSet resultSet = null;
		Connection connection = null;
		Statement statement = null;

		try {
			connection = Utility.getConnection(databaseData);
			statement = connection.createStatement();

			resultSet = statement.executeQuery(SQL_SELECT_QUERY);

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<h1>All Data Present in Database...</h1>");
			out.println("<br>");
			out.println("<table>");
			out.println("<tr><th>Name</th><th>Cost</th></tr>");
			try {
				while (resultSet.next()) {
					out.println("<tr>");
					out.println("<td>" + resultSet.getString(1) + "</td>");
					out.println("<td>" + resultSet.getString(2) + "</td>");
					out.println("</tr>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			out.println("</table>");
			out.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utility.closeConnection(connection, statement);
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}