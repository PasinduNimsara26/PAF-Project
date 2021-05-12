package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Byer;

/**
 * Servlet implementation class BuyerAPI
 */
@WebServlet("/BuyerAPI")
public class BuyerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	Byer itemObj = new Byer();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

    

	
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    	
    	
    	//NOT USE 
    	
	}



    
    // Insert Operation
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = itemObj.insertbuyer(request.getParameter("FullName"),
	 			request.getParameter("PhoneNumber"),
	 			request.getParameter("Email"),
	 			request.getParameter("Address"),
	 			request.getParameter("Birthdate"));
		
	 			response.getWriter().write(output);
	 			
		
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
 		
 		
 		
		 Map paras = getParasMap(request);
		 
		 String output = itemObj.updatebuyer(paras.get("hidBuyerIDSave").toString(),
		 paras.get("FullName").toString(),
		 paras.get("PhoneNumber").toString(),
		 paras.get("Email").toString(),
		 paras.get("Address").toString(),
		 paras.get("Birthdate").toString());
		 
		 response.getWriter().write(output);
		 }
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
			 Map paras = getParasMap(request);
			 
			 String output = itemObj.deletebuyer(paras.get("ID").toString());
			response.getWriter().write(output);
			}
	 
	 

	private static Map getParasMap(HttpServletRequest request){
			
		Map<String, String> map = new HashMap<String, String>();
			
			try{
							
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ?
						scanner.useDelimiter("\\A").next() : "";
				scanner.close();
				String[] params = queryString.split("&");
				for (String param : params){ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}catch (Exception e){
		 
			
			}
			
			return map;
		}
	 

}
