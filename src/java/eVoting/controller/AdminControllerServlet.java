package eVoting.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminControllerServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        RequestDispatcher rd=null;
        HttpSession sess = request.getSession();
        try
        {
            String userid =(String)sess.getAttribute("userid");
            String usertype=(String)sess.getAttribute("usertype");
            if(userid==null || usertype.equalsIgnoreCase("voter"))
            {
                sess.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            if(usertype.equalsIgnoreCase("admin"))
                rd=request.getRequestDispatcher("AdminOptions.jsp");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            rd=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception", ex);
        }
        finally
        {
            rd.forward(request, response);
        }
    }
}
