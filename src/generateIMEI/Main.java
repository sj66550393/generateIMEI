package generateIMEI;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
//		beachIMEI();
//		System.out.println(formatMeid("A10013E710794"));
		generateMEID();
	}
	
    /** 
     * ��������IMEI 
     * @param begin  
     * @param end 
     * @return 
     */  
    static void beachIMEI(){
    	int tac = 863418;
    	int fac = 19;
    	int snr = 573913;
    	for(int i=0;i<100;i++) {
    		int tac1 = i+2 + tac + i*76;
    		int fac1 = i/2 + fac;
    		int snr1 = i+4 + snr;
    		String code = tac1 + "" + fac1 + "" + snr1 + "";
    		code =code+ genCode(code); 
    		System.out.println(code);
    	}
    }
    
    public static void generateMEID(){
    	int first = 00;
    	int first1 = 001;
    	int second = 617242;
    	for(int i=0;i<100;i++){
        int start1 = first + i/3 + 2;
        int start2 = first1 + i/2 + 2;
        int end = second + i + 13;
    	String code = "A" + start1 + "" + start2 + "AE" + end;
    	code = formatMeid(code);
    	System.out.println(code);
    	}
    }
      
    /** 
     * IMEI У���� 
     * @param code 
     * @return 
     */  
    public static String genCode(String code){  
        int total=0,sum1=0,sum2 =0;  
        int temp=0;  
        char [] chs = code.toCharArray();  
        for (int i = 0; i < chs.length; i++) {             
            int num = chs[i] - '0';     // ascii to num  
            //System.out.println(num);       
            if (i%2==0) {  
                sum1 = sum1 + num;  
            }else{  
               
                temp=num * 2 ;  
                if (temp < 10) {  
                    sum2=sum2+temp;  
                }else{  
                    sum2 = sum2 + temp + 1 -10;  
                }  
            }  
        }  
        total = sum1+sum2;  
        if (total % 10 ==0) {  
            return "0";  
        }else{  
            return (10 - (total %10))+"";  
        }  
          
    }  
    
    
    public static String formatMeid(String meid) {  
        int dxml = meid.length();  
        if (dxml != 14 && dxml != 16) {  
            return meid;  
        }  
        String meidRes = "";  
        if (dxml == 14) {  
            meidRes =  meid + getmeid15(meid);  
        }  
        if (dxml == 16) {  
            meidRes = meid.substring(2) + getmeid15(meid.substring(2));  
        }  
        return meidRes;  
    }  
    
    private static String getmeid15(String meid) {  
        if (meid.length() == 14) {  
            String myStr[] = { "a", "b", "c", "d", "e", "f" };  
            int sum = 0;  
            for (int i = 0; i < meid.length(); i++) {  
                String param = meid.substring(i, i + 1);  
                for (int j = 0; j < myStr.length; j++) {  
                    if (param.equalsIgnoreCase(myStr[j])) {  
                        param = "1" + String.valueOf(j);  
                    }  
                }  
                if (i % 2 == 0) {  
                    sum = sum + Integer.parseInt(param);  
                } else {  
                    sum = sum + 2 * Integer.parseInt(param) % 16;  
                    sum = sum + 2 * Integer.parseInt(param) / 16;  
                }  
            }  
            if (sum % 16 == 0) {  
                return "0";  
            } else {  
                int result = 16 - sum % 16;  
                if (result > 9) {  
                    result += 65 - 10;  
                }   
                return result + "";  
            }  
        } else {  
            return "";  
        }  
    } 
  
}
