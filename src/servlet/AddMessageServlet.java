package servlet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class AddMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        System.out.println("do add");
        String messageStr=request.getParameter("message");
        String id=request.getParameter("id");
        String emailStr=request.getParameter("email");
        id=id.substring(0,id.length()-1);

        String backStr="";
        String backTimeStr="";
        Date date=new Date();
        String timeStr= DateUtil.formatDate(date,"yyyy-MM-dd HH:mm:ss");
        try {
            String xmlPath = this.getServletContext().getRealPath("/WEB-INF/message/message" + id + ".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            Element root = document.getDocumentElement();
            Element record=document.createElement("record");
            Element email= document.createElement("email");
            email.appendChild(document.createTextNode(emailStr));
            Element message= document.createElement("message");
            message.appendChild(document.createTextNode(messageStr));
            Element time= document.createElement("time");
            time.appendChild(document.createTextNode(timeStr));
            Element back= document.createElement("back");
            back.appendChild(document.createTextNode(backStr));
            Element backTime= document.createElement("backTime");
            backTime.appendChild(document.createTextNode(backTimeStr));
            record.appendChild(email);
            record.appendChild(message);
            record.appendChild(time);
            record.appendChild(back);
            record.appendChild(backTime);
            root.appendChild(record);
            TransformerFactory transFactory= TransformerFactory.newInstance();
            Transformer transformer=transFactory.newTransformer();
            DOMSource domSource=new DOMSource(document);
            xmlPath="D:/mess_board/web/WEB-INF/message/message"+id+".xml";
            File file=new File(xmlPath);
            FileOutputStream out=new FileOutputStream(file);
            StreamResult xmlResult=new StreamResult(out);
            transformer.transform(domSource,xmlResult);
        }

        catch (Exception ex){
            ex.printStackTrace();
        }

        response.sendRedirect("message.jsp?id="+id);
        //request.getRequestDispatcher("message.jsp?id="+id).forward(request,response);
    }
}
