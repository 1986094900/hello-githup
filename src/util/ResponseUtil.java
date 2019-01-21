package util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
public class ResponseUtil {
	public static void write(HttpServletResponse response,Object jsonObject){
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out=null;
		try {
			out = response.getWriter();
			out.print(jsonObject.toString());
			out.flush();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		finally {
		    out.close();
        }
	}
}