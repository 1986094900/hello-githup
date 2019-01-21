<%@ page language="java" contentType="text/html; charset=utf-8"
         import="java.io.*,java.util.*,javax.xml.parsers.*,org.w3c.dom.*"
         pageEncoding="GBK"%>
<%
    String id=request.getParameter("id");
    String xmlPath = request.getContextPath()+"/mess_board/web/WEB-INF/message/message"+id+".xml";
    DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
    DocumentBuilder builder=factory.newDocumentBuilder();
    Document document=builder.parse(new File(xmlPath));
    Element root=document.getDocumentElement();
%>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>福州大学网络中心</title>
</head>
<body>

<div style="text-align: center ; margin: 10px auto 20px">请尊重网上道德，且不要发布与本板无关的信息</div>
<div style="margin: 10px auto 20px">
    <%
        out.print("<hr/>");
        out.print("<form action='keepSearchServlet' method='get'>");
        out.print("<input type='text' name='search'/>");
        out.print("<input type='hidden' name='id' value="+id+"/>");
        out.print("<input type='submit' value='问题搜索'/><br/>");
        out.print("</form>");
    %>
</p>
</div>
<hr/>
<div>
    <%
        out.print("<form action='addMessageServlet' method='get'>");
        out.print("留言板:<textarea name='message'></textarea><br/><br/>");
        out.print("&nbsp&nbsp&nbsp邮箱:<input type='text' name='email'/>");
        out.print("<input type='hidden' name='id' value="+id+"/>");
        out.print("</br>");
        out.print("</br>");
        out.print("<input type='submit' value='发布'/><br/>");
        out.print("</form>");
    %>
</div>
<hr/>

<div style="margin-top: 50px">
    <table width="90%" cellspacing="1" cellpadding="5" border="1">
        <%
            NodeList nodes=document.getElementsByTagName("record");

            NodeList searchs=document.getElementsByTagName("search");
            Node search=searchs.item(0);//只有一个search,取第一个
            String sear=search.getTextContent();//获取搜索字符串
            String email="",message="",time="",back="",backTime="";
            for(int i=nodes.getLength()-1;i>0 ;--i) {//倒序访问，实现按发表日期逆序排序
                Node node=nodes.item(i);

        %>
        <tr>
            <%
                NodeList childNodes=node.getChildNodes();
                int childSize=childNodes.getLength();
                for(int j=0;j<childSize;++j){
                    Node childNode=childNodes.item(j);
                    if(childNode.getNodeName().equals("email"))
                        email=childNode.getTextContent();
                    else if(childNode.getNodeName().equals("message"))
                        message=childNode.getTextContent();
                    else if(childNode.getNodeName().equals("time"))
                        time=childNode.getTextContent();
                    else if(childNode.getNodeName().equals("back"))
                        back=childNode.getTextContent();
                    else if(childNode.getNodeName().equals("backTime"))
                        backTime=childNode.getTextContent();
                }
                if(message.contains(sear)){
                    out.print("<br/>");
                    out.print("该留言发表于:"+time);
                    out.print("<br/>");
                    out.print("<br/>");
                    out.print("用户发表:"+message);
                    out.print("<br/>");
                    out.print("<br/>");
                    if(backTime.equals("")){
                        backTime="等待管理员回复......";
                    }
                    out.print("管理员回复于:"+backTime);
                    out.print("<br/>");
                    out.print("<br/>");
                    out.print(back);
                    out.print("<br/>");
                    out.print("<br/>");
                    out.print("<a href='deleteServlet?id="+id+"&message="+message+"'>"+"删除"+"</a>");
                    out.print("<br/>");
                    out.print("<br/>");

                    out.print("<form action='backServlet' method='get'>");
                    out.print("<textarea name='back'></textarea><br/>");
                    out.print("<input type='hidden' name='id' value="+id+"/>");
                    out.print("<input type='hidden' name='message' value="+message+"/>");
                    out.print("<input type='submit' value='回复'/>");
                    out.print(" </form>");
                    out.print("<hr/>");
                }
            %>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
