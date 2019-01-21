<%@ page language="java" contentType="text/html; charset=utf-8"
         import="java.io.*,java.util.*,javax.xml.parsers.*,org.w3c.dom.*"
         pageEncoding="gbk"%>

<%
    String xmlPath = request.getContextPath()+"/mess_board/web/WEB-INF/boards.xml";//xml路径
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder builder=factory.newDocumentBuilder();
    Document document=builder.parse(new File(xmlPath));
    Element root=document.getDocumentElement();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>福州大学留言板</title>
</head>
<body>
<div style="text-align: center;">福州大学留言板</div>
<div style="text-align: center; margin-top: 50px">管理员登录</div>
<div style="margin-top: 50px">
<table width="100%" border="1" style="border-color: red;border-width: 1px">
    <%
        NodeList nodes=document.getElementsByTagName("board");
        for(int i=0;i< nodes.getLength();) {
            int colCount = 0;

    %>
    <tr>
        <%
            while(i<nodes.getLength() && colCount<4)
            {
                Node item=nodes.item(i);
                colCount++;
                out.print("<td><a href='message.jsp?id="+item.getAttributes().getNamedItem("id").getTextContent()+"'>"+item.getTextContent()+"</a></td>");
                i++;
            }
            for(int j=colCount;j<4;j++)
                out.print("<td>&nbsp</td>");
        %>
    </tr>
    <%}%>
</table>
</div>
</body>
</html>