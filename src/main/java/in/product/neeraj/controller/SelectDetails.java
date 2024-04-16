package in.product.neeraj.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.product.neeraj.factory.RepoFactory;
import in.product.neeraj.repository.Repository;

@WebServlet(value = "/read", initParams = { @WebInitParam(name = "url", value = "jdbc:mysql:///practice"),
		@WebInitParam(name = "user", value = "root"), @WebInitParam(name = "password", value = "bit230803"),
		@WebInitParam(name = "driverName", value = "com.mysql.cj.jdbc.Driver") }, loadOnStartup = 2)
public class SelectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, String> databaseData = new HashMap<>();
		copyInitParamToHashMap(databaseData);

		Repository repository = RepoFactory.getRepository();
		repository.selectProduct(databaseData, response);
	}

	private void copyInitParamToHashMap(HashMap<String, String> databaseData) {
		Enumeration<String> parameters = getInitParameterNames();
		while (parameters.hasMoreElements()) {
			String key = parameters.nextElement();
			String value = getInitParameter(key);

			databaseData.put(key, value);
		}
	}
}
