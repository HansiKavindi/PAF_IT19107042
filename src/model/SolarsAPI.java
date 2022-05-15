package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class SolarsAPI
 */
@WebServlet("/SolarsAPI")
public class SolarsAPI  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	solars solarObj = null;
    
    public SolarsAPI() {
        super();
        
    }
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("come to post");
		solars Solars = new solars();
		String output = Solars.insertSolars(request.getParameter("accountNum"),
				 request.getParameter("fullName"),
				request.getParameter("userNic"),
				request.getParameter("address"),
				request.getParameter("contactNum"),
				request.getParameter("email"),
				request.getParameter("bankAccNum"),
				request.getParameter("bankBranch"));
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("came here to update");
		solars Solars = new solars();
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = Solars.updateSolar(paras.get("hidSolarIDSave").toString(),
		 paras.get("accountNum").toString(),
		paras.get("fullName").toString(),
		paras.get("userNic").toString(),
		paras.get("address").toString(),
		paras.get("contactNum").toString(),
		paras.get("email").toString(),
		paras.get("bankAccNum").toString(),
		paras.get("bankBranch").toString()
		);
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		solars Solars = new solars();
		Map paras = getParasMap(request);
		 String output = Solars.deleteSolar(paras.get("solarID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
			Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				 }
	 	}
		catch (Exception e)
			 {
			 	}
		return map;
				}

}
