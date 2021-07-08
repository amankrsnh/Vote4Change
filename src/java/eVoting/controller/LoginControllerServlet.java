package eVoting.controller;

import eVoting.dao.UserDao;
import eVoting.dto.UserDto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginControllerServlet extends HttpServlet 
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher rd=null;
        String logout=request.getParameter("logout");
        UserDto user=new UserDto(request.getParameter("userid"),request.getParameter("password"));
        if(logout!=null && logout.equalsIgnoreCase("logout")) 
        {
        	HttpSession sess=request.getSession(false);
        	if(sess!=null) {
        		sess.invalidate();
        		sess=null;
        	}
        	request.getRequestDispatcher("login.html").forward(request, response);;
        	return;
        }
        try
        {
            String result=UserDao.validateUser(user);
            request.setAttribute("result", result);
            request.setAttribute("userid", request.getParameter("userid"));
            rd=request.getRequestDispatcher("LoginResponse.jsp");
        }
        catch(Exception ex)
        {
            request.setAttribute("exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
        }
        finally
        {
            rd.forward(request,response);
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
