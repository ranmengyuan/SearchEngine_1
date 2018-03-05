package searchEngine;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Map;
  
public class Demo_web {  
	static String sql = null;  
    static DBHelper db1 = null;  
    static ResultSet ret = null;
    static java.sql.Statement stmt = null;
    
    public Demo_web() {  
        
    }  
    public String Look(String Input){//�鿴���ݿڷ����������ѯ�Ĵ���ͬ�Ĵʺ�Ƶ�ʴ���һ��ͼ�У�������
    	sql = "select Title from Table_web where URL=?";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����
        String title=null;  
        try { 
        	db1.pst.setString(1, Input);
            ret = db1.pst.executeQuery();//ִ����䣬�õ������ 
            while (ret.next()) {   
                String ufname = ret.getString("Title");   
                	title=ufname;          
            }//��ʾ����               
            ret.close(); 
            db1.close();//�ر�����  
        } catch (SQLException e) {  
        	System.out.println("���ݿ�����ʧ��");  
        }  
        return title;
    }
    public int Size(){//ͳ�����ݿ��е����ݸ���
    	int legth=0;
    	sql = "select *from Table_web";//SQL���  
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
    public  void Inter(Map<String,String> Title){//�����뷽�������ݼӵ����ݿ���
    	  	sql = "select *from Table_web";//SQL���  
	        db1 = new DBHelper(sql);//����DBHelper����
	        try {  
		        	stmt=db1.conn.createStatement();
		        	 for(String key:Title.keySet()){
		        		 stmt.executeUpdate("INSERT INTO Table_web VALUES('"+key+"','"+Title.get(key)+"')");  //���һ����¼
		        	 }
		            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
		            stmt.close();
		            db1.close();//�ر�����  
		     } catch (SQLException e) {  
		            e.printStackTrace();  
		     }  
	 
    }
    public  void Delete(){//ɾ�����ݿ��е���������
    	  
		 sql = "select *from Table_web";//SQL���  
	        db1 = new DBHelper(sql);//����DBHelper����  
	  
	        try {  
	        	 stmt=db1.conn.createStatement();
	        	 stmt.executeUpdate("DELETE FROM Table_web WHERE 1=1"); 
	            ret = db1.pst.executeQuery();//ִ����䣬�õ������                 
	            ret.close(); 
	            stmt.close();
	            db1.close();//�ر�����  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	 
    }
  
}  




