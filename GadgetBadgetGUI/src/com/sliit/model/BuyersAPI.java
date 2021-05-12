package com.sliit.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuyersAPI
 */
@WebServlet("/BuyersAPI")
public class BuyersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyersAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post method worked after ajax call");
		Buyer buyerObj = new Buyer();
		String output = buyerObj.insertBuyer(request.getParameter("txtName"), request.getParameter("rdoGender"),
				request.getParameter("txtContact"), request.getParameter("txtEmail"),
				request.getParameter("txtAddress"));
		System.out.println(output);
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Buyer buyerObj = new Buyer();

		Map paras = getParasMap(request); 
		String output = buyerObj.updateBuyer(paras.get("hidBuyerIDSave").toString(), 
				 paras.get("txtName").toString(), 
				 paras.get("rdoGender").toString(), 
				paras.get("txtContact").toString(), 
				paras.get("txtEmail").toString(), 
				paras.get("txtAddress").toString());
		
		response.getWriter().write(output);

		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Buyer buyerObj = new Buyer();

		 Map paras = getParasMap(request);
		 String output = buyerObj.deleteBuyer(paras.get("buyerID").toString()); //change the ID if not works
			response.getWriter().write(output);


	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}
		return map; 

	}

}
