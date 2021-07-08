package eVoting.controller;

import eVoting.dao.CandidateDao;
import eVoting.dto.AddCandidateDto;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class UpdateCandidateControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String filename=null;
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try
        {
            DiskFileItemFactory df=new DiskFileItemFactory();
            ServletFileUpload sfu=new ServletFileUpload(df);
            ServletRequestContext src=new ServletRequestContext(request);
            List<FileItem> multiList=sfu.parseRequest(src);
            InputStream inp=null;
            ArrayList<String> objValues=new ArrayList<>();
            for(FileItem fit:multiList)
            {
                if(fit.isFormField())
                {
                    String fname=fit.getFieldName();
                    String value=fit.getString();
                    System.out.println(fname+" : "+value);
                    objValues.add(value);
                }
                else 
                {
                    inp=fit.getInputStream();
                    String key=fit.getFieldName();
                    filename=fit.getName();
                    System.out.println(key+" : "+filename);
                }
            }
            if(filename.equalsIgnoreCase(""))
            {
                inp=CandidateDao.getSymbolById(objValues.get(0));
                
            }
            AddCandidateDto candidate=new AddCandidateDto(objValues.get(1),objValues.get(0),objValues.get(4),objValues.get(3),inp);
            boolean result=CandidateDao.updateCandidate(candidate);
            System.out.println("result: "+result);
            if(result)
                rd=request.getRequestDispatcher("success.jsp");
            else
                rd=request.getRequestDispatcher("failure.jsp");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in AddNewCandidateServlet:"+ex.getMessage());
            rd=request.getRequestDispatcher("showexception.jsp");
            ex.printStackTrace();
        }
        finally
        {
            if(rd!=null)
            {
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
