/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eVoting.controller;

import eVoting.dao.UserDao;
import eVoting.dto.UserDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aman Kumar Singh
 */
public class ShowRemoveUserControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null){
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try{
            String uid=(String)request.getParameter("data");
            System.out.println(uid);
            if(uid!=null && uid.equalsIgnoreCase("getId")) {
            	ArrayList<String> userId=UserDao.getAllUserId();
            	request.setAttribute("data", "id");
            	request.setAttribute("userId", userId);
            	System.out.println("In");
            }else {
            	request.setAttribute("data", "user");
            	UserDetails user=UserDao.getUserById(uid);
            	request.setAttribute("user", user);
            }
            rd=request.getRequestDispatcher("showRemoveUser.jsp");
        }catch(Exception ex){
        	request.setAttribute("Exception", ex);
        	rd=request.getRequestDispatcher("showexception.jsp");
            System.out.println("Exception in RemoveCandidateControllerServlet:"+ex.getMessage());
            ex.printStackTrace();
        }finally{
            if(rd!=null){
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
