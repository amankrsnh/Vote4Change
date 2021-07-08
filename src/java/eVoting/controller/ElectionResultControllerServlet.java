/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eVoting.controller;

import eVoting.dao.CandidateDao;
import eVoting.dao.VoteDao;
import eVoting.dto.CandidateDetails;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
public class ElectionResultControllerServlet extends HttpServlet {

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
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        RequestDispatcher rd=null;
        if(userid==null){
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        try {
        	String data=request.getParameter("data");
        	if(data.equalsIgnoreCase("result")) {
        		Map <String,Integer> result = VoteDao.getResult();
        		Set s=result.entrySet();
        		Iterator it=s.iterator();
        		LinkedHashMap<CandidateDetails, Integer> resultDetails=new LinkedHashMap<>();
        		while(it.hasNext()) {
        			Map.Entry<String, Integer> e=(Map.Entry)it.next();
        			CandidateDetails cd=CandidateDao.getDetailsById(e.getKey());
        			resultDetails.put(cd, e.getValue());
        		}
        		request.setAttribute("result", resultDetails);
        		request.setAttribute("votecount", VoteDao.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult.jsp");
        	}else if(data.equalsIgnoreCase("party")) {
        		request.setAttribute("status", "party");
        		request.setAttribute("result", VoteDao.getResultByParty());
        		request.setAttribute("votecount", VoteDao.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult2.jsp");
        	}else if(data.equalsIgnoreCase("gender")){
                        System.out.println(" In gender section of servlet");
        		request.setAttribute("status", "gender");
        		request.setAttribute("result", VoteDao.getResultByGender());
        		request.setAttribute("votecount", VoteDao.getVoteCount());
        		rd=request.getRequestDispatcher("electionresult2.jsp");
        	}
        }catch(Exception ex) {
        	ex.printStackTrace();
            request.setAttribute("Exception", ex);
            rd=request.getRequestDispatcher("showexception.jsp");
        }finally {
        	if(rd!=null)
        		rd.forward(request, response);
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
