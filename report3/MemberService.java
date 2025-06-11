package cs.dit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cs.dit.member.MemberDAO;

public abstract class MemberService {
    protected MemberDAO dao = new MemberDAO();
    
    public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}