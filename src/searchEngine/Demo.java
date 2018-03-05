package searchEngine;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
  
public class Demo {  
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;
    static java.sql.Statement stmt = null;
    
    public Demo() {  
        
    }  
    public	Map<String,Integer> Look(String Input){//�鿴���ݿ�ķ���,�����ѯ�Ĵ���ͬ�Ĵʺ�Ƶ�ʴ���һ��ͼ�У�������
    	sql = "select URL,Rate from Table_region where Concept=?";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����  
        String uid;
        int rate;
        Map<String,Integer> suit=new HashMap<String,Integer>();
        try {  
        	db1.pst.setString(1, Input);
            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
            while (ret.next()) {  
                 uid = ret.getString("URL");     
                 rate=ret.getInt("Rate"); 
                	 suit.put(uid, rate);
//                	 System.out.println(uid+ufname+rate);
            }//����������������ͬ�Ĺؼ��ֳ��ֵ���ҳ��Ƶ�ʷ���һ��ͼ��              
            ret.close(); 
            db1.close();//�ر�����  
        } catch (SQLException e) {  
        	System.out.println("���ݿ�����ʧ��");   
        }  
        return suit;
    }
    public int Size(){//ͳ�����ݿ������ݸ���
    	int legth=0;
    	sql = "select *from Table_region";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����  
  
        try {  
            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
            while (ret.next()) { 
            	legth++;
            }              
            ret.close(); 
            db1.close();//�ر�����  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    	return legth;
    }
    public  void Inter(ArrayList<String> URL,ArrayList<String>Concept,ArrayList<Integer>Rate){//�����뷽�������ݴ������ݿ�
    	  	sql = "select *from Table_region";//SQL���  
	        db1 = new DBHelper(sql);//����DBHelper����
	        int i=0;
	        try {  
		        	stmt=db1.conn.createStatement();
		        	 for(i=0;i<URL.size();i++){
		        		 if(i<5||(i<Rate.size()-1&&Rate.get(i)==Rate.get(4)))
		        		 stmt.executeUpdate("INSERT INTO Table_region VALUES('"+URL.get(i)+"','"+Concept.get(i)+"','"+Rate.get(i)+"')");  //���һ����¼
		        	 }
		            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
		            stmt.close();
		            db1.close();//�ر�����  
		     } catch (SQLException e) {  
		            e.printStackTrace();  
		     }  
	 
    }
    public  void Delete(){//�����ݿ��е���������ɾ��
    	  
		 sql = "select *from Table_region";//SQL���  
	        db1 = new DBHelper(sql);//����DBHelper����  
	        try {  
	        	 stmt=db1.conn.createStatement();
	        	 stmt.executeUpdate("DELETE FROM Table_region WHERE 1=1"); 
	            ret = db1.pst.executeQuery();//ִ����䣬�õ������                
	            ret.close(); 
	            stmt.close();
	            db1.close();//�ر�����  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	 
    }
  
}  


