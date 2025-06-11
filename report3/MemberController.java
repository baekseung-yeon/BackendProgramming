package cs.dit.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.service.MemberDeleteService;
import cs.dit.service.MemberGetService;
import cs.dit.service.MemberInsertService;
import cs.dit.service.MemberListService;
import cs.dit.service.MemberService;
import cs.dit.service.MemberUpdateService;

//@WebServlet("*.do")
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("utf-8");
        
        String uri = request.getRequestURI();
        System.out.println(uri);
        
        String com = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".do"));
        System.out.println(com);
        String viewPage = null;
        
        if (com != null && com.equals("list")) {
            MemberService service = new MemberListService();
            service.execute(request, response);
            viewPage = "/WEB-INF/views/list.jsp";
            
        } else if (com != null && com.equals("insertForm")) {
            viewPage = "/WEB-INF/views/insertForm.jsp";
            System.out.println(viewPage);
            
        } else if (com != null && com.equals("insert")) {
            MemberService service = new MemberInsertService();
            service.execute(request, response);
            viewPage = "list.do";
            
        } else if (com != null && com.equals("updateForm")) {
            MemberService service = new MemberGetService();
            service.execute(request, response);
            viewPage = "/WEB-INF/views/updateForm.jsp";
            
        } else if (com != null && com.equals("update")) {
            MemberService service = new MemberUpdateService();
            service.execute(request, response);
            viewPage = "list.do";
            
        } else if (com != null && com.equals("delete")) {
            MemberService service = new MemberDeleteService();
            service.execute(request, response);
            viewPage = "list.do";
            
        } else if (com != null && com.equals("index")) {
            viewPage = "/WEB-INF/views/index.jsp";
        } else {
            // 기본 페이지 설정 (매칭되는 명령이 없을 때)
            viewPage = "/WEB-INF/views/index.jsp";
        }
        
        // view page로 이동
        if (viewPage != null) {
            RequestDispatcher rd = request.getRequestDispatcher(viewPage);
            rd.forward(request, response);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}