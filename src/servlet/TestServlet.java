package servlet;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import util.ResponseUtil;

public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String xmlPath=this.getServletContext().getRealPath("/WEB-INF/boards.xml");
        File xml=new File(xmlPath);
        if(xml.exists()){
            System.out.println("exit");
        }
        String test=request.getParameter("test");

        try{
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("hello",test);
            ResponseUtil.write(response, jsonObject);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
