package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private BoardDAO dao;
    private static final int PAGE_SIZE = 5; // 한 페이지당 게시글 수
    private static final int NUM_OF_PAGE = 5; // 페이지네이션에 표시할 페이지 수
    
    public BoardController() {
        super();
        dao = new BoardDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length());
        
        System.out.println("Command: " + command);
        
        if (command.equals("/list.do")) {
            boardList(request, response);
        } else if (command.equals("/detail.do")) {
            boardDetail(request, response);
        } else if (command.equals("/writeForm.do")) {
            writeForm(request, response);
        } else if (command.equals("/write.do")) {
            writeBoard(request, response);
        } else if (command.equals("/updateForm.do")) {
            updateForm(request, response);
        } else if (command.equals("/update.do")) {
            updateBoard(request, response);
        } else if (command.equals("/delete.do")) {
            deleteBoard(request, response);
        }
    }
    
    // 게시글 목록 조회
    private void boardList(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String pageParam = request.getParameter("p");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        
        // 전체 게시글 수 조회
        int totalCount = dao.getTotalCount();
        
        // 페이지네이션 계산
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);
        int startNum = ((currentPage - 1) / NUM_OF_PAGE) * NUM_OF_PAGE + 1;
        int lastNum = totalPages;
        
        // 게시글 목록 조회
        ArrayList<BoardDTO> dtos = dao.getBoardList(currentPage, PAGE_SIZE);
        
        // request에 데이터 저장
        request.setAttribute("dtos", dtos);
        request.setAttribute("p", currentPage);
        request.setAttribute("startNum", startNum);
        request.setAttribute("lastNum", lastNum);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("numOfPage", NUM_OF_PAGE);
        
        // JSP로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
    
    // 게시글 상세 조회
    private void boardDetail(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int bcode = Integer.parseInt(request.getParameter("bcode"));
        BoardDTO dto = dao.getBoardDetail(bcode);
        
        request.setAttribute("dto", dto);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");
        dispatcher.forward(request, response);
    }
    
    // 글쓰기 폼
    private void writeForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.jsp");
        dispatcher.forward(request, response);
    }
    
    // 게시글 작성
    private void writeBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String writer = request.getParameter("writer");
        
        BoardDTO dto = new BoardDTO();
        dto.setSubject(subject);
        dto.setContent(content);
        dto.setWriter(writer);
        
        int result = dao.insertBoard(dto);
        
        if (result > 0) {
            response.sendRedirect("list.do");
        } else {
            request.setAttribute("message", "게시글 작성에 실패했습니다.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 수정 폼
    private void updateForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int bcode = Integer.parseInt(request.getParameter("bcode"));
        BoardDTO dto = dao.getBoardDetail(bcode);
        
        request.setAttribute("dto", dto);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("updateForm.jsp");
        dispatcher.forward(request, response);
    }
    
    // 게시글 수정
    private void updateBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int bcode = Integer.parseInt(request.getParameter("bcode"));
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        
        BoardDTO dto = new BoardDTO();
        dto.setBcode(bcode);
        dto.setSubject(subject);
        dto.setContent(content);
        
        int result = dao.updateBoard(dto);
        
        if (result > 0) {
            response.sendRedirect("detail.do?bcode=" + bcode);
        } else {
            request.setAttribute("message", "게시글 수정에 실패했습니다.");
            request.setAttribute("dto", dto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("updateForm.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // 게시글 삭제
    private void deleteBoard(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        int bcode = Integer.parseInt(request.getParameter("bcode"));
        int result = dao.deleteBoard(bcode);
        
        if (result > 0) {
            response.sendRedirect("list.do");
        } else {
            request.setAttribute("message", "게시글 삭제에 실패했습니다.");
            response.sendRedirect("detail.do?bcode=" + bcode);
        }
    }
}
