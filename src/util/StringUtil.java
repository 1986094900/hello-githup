package util;

public class StringUtil {

	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(str!=null&&(!("".equals(str.trim())))){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isNumric(String str){
		if(StringUtil.isNotEmpty(str)){
			for(int i=0;i<str.length();++i){
				if(str.charAt(i)<48||str.charAt(i)>57)
					return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
