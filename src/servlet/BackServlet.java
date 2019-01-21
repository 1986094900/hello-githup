package servlet;

import org.w3c.dom.*;
import util.DateUtil;

import javax.xml.parsers.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Date;

public class BackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        System.out.println("do back");
        Date date=new Date();
        String dateStr= DateUtil.formatDate(date,"yyyy-MM-dd HH:mm:ss");
        String message=request.getParameter("message");
        String back=request.getParameter("back");
        String id=request.getParameter("id");
        message=message.substring(0,message.length()-1);//去除'/'号
        id=id.substring(0,id.length()-1);
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
                        for(int k=0;k<childSize;++k){
                            Node n=childNodes.item(k);
                            if(n.getNodeName().equals("back")){//添加/修改回复
                                n.setTextContent(back);
                            }
                            if(n.getNodeName().equals("backTime")){//添加/修改回复时间
                                n.setTextContent(dateStr);
                            }
                        }
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
