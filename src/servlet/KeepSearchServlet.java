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


public class KeepSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        System.out.println("do search");
        String search=request.getParameter("search");
        String id=request.getParameter("id");

        id=id.substring(0,id.length()-1);


        try {
            String xmlPath = this.getServletContext().getRealPath("/WEB-INF/message/message" + id + ".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            Element root = document.getDocumentElement();
            NodeList nodes = root.getElementsByTagName("search");

            Node node=nodes.item(0);//只有一个，取第一个
            node.setTextContent(search);

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
    }
}
