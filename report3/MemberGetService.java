package cs.dit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs.dit.member.MemberDTO;

public class MemberGetService extends MemberService {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        MemberDTO dto = dao.get(id);
        request.setAttribute("dto", dto);
    }
}