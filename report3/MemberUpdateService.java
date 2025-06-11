package cs.dit.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import cs.dit.member.MemberDTO;

public class MemberUpdateService extends MemberService {
    
    private String photo = "";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        photo = request.getParameter("photo");
        
        try {
            uploadfile(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        MemberDTO dto = new MemberDTO(id, name, pwd, photo);
        dao.update(dto);
    }
    
    private void uploadfile(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String dir = request.getServletContext().getRealPath("/photos");
        
        File f = new File(dir);
        if(!f.exists()) {
            f.mkdirs();
        }
        
        Collection<Part> parts = request.getParts();

        for(Part p : parts) {
            if(p.getHeader("Content-Disposition").contains("filename=")) {
                if(p.getSize() > 0) {
                    photo = p.getSubmittedFileName();
                    String filePath = dir + File.separator + photo;
                    p.write(filePath);
                    p.delete();
                }
            }
        }
    }
}