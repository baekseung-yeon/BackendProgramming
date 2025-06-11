package cs.dit.service;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs.dit.member.MemberDTO;

public class MemberListService extends MemberService {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<MemberDTO> dtos = dao.list();
        request.setAttribute("dtos", dtos);
    }
}