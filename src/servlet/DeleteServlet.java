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

public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String message=request.getParameter("message");
        String id=request.getParameter("id");
        System.out.println(message+" "+id);

        try {
            String xmlPath = this.getServletContext().getRealPath("/WEB-INF/message/message" + id + ".xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlPath));
            Element root = document.getDocumentElement();
            NodeList nodes = root.getElementsByTagName("record");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                NodeList childNodes = node.getChildNodes();
                int childSize = childNodes.getLength();
                for (int j = 0; j < childSize; ++j) {
                    String mess=childNodes.item(j).getTextContent();
                    if(mess.equals(message)){//在这个节点里
                        root.removeChild(node);
                        System.out.println("remove");
                        break;
                    }
                }
            }
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
