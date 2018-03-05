package searchEngine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Demo_similar {static String sql = null;  
static DBHelper db1 = null;  
static ResultSet ret = null;
static java.sql.Statement stmt = null;

public Demo_similar() {  
    
}  
public int LookIndex(String Input){//�鿴���ݵ�����
	sql = "select Indexl from Table_similar where Content=?";//SQL���  
    db1 = new DBHelper(sql);//����DBHelper����  
    int uid=-1;
    try {  
    	db1.pst.setString(1, Input);
        ret = db1.pst.executeQuery();//ִ����䣬�õ������  
        while (ret.next()) {  
             uid = ret.getInt("Indexl");     

        }              
        ret.close(); 
        db1.close();//�ر�����  
    } catch (SQLException e) {  
    	System.out.println("���ݿ�����ʧ��");    
    }  
    return uid;
}
public ArrayList<String> LookSimilar(int index){
	ArrayList<String> similar=new ArrayList<String>();
	if(index!=-1){
		sql = "select Content from Table_similar where Indexl=?";//SQL���  
	    db1 = new DBHelper(sql);//����DBHelper����  
	    try {  
	    	db1.pst.setInt(1, index);
	        ret = db1.pst.executeQuery();//ִ����䣬�õ������  
	        while (ret.next()) {  
	             similar.add(ret.getString("Content"));     
	
	        }              
	        ret.close(); 
	        db1.close();//�ر�����  
	    }catch (SQLException e) {  
	    	System.out.println("���ݿ�����ʧ��");    
	    }  
	}
    return similar;
}
public int Size(){//ͳ�����ݿ��е����ݸ���
	int legth=0;
	sql = "select *from Table_similar";//SQL���  
    db1 = new DBHelper(sql);//����DBHelper����  

    try {  
        ret = db1.pst.executeQuery();//ִ����䣬�õ������  
        while (ret.next()) { 
        	legth++;
        }              
        ret.close(); 
        db1.close();//�ر�����  
    } catch (SQLException e) {  
    	System.out.println("���ݿ�����ʧ��");   
    }  
	return legth;
}
public  void Inter(ArrayList<Integer> Index,ArrayList<String>Content){//�����뷽�������ݼӵ����ݿ���
	  	sql = "select *from Table_similar";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����
        int i=0;
        try {  
	        	stmt=db1.conn.createStatement();
	        	 for(i=0;i<Index.size();i++){
	        		 stmt.executeUpdate("INSERT INTO Table_similar VALUES('"+Index.get(i)+"','"+Content.get(i)+"')");  //���һ����¼
	        	 }
	            ret = db1.pst.executeQuery();//ִ����䣬�õ������  
	            stmt.close();
	            db1.close();//�ر�����  
	     } catch (SQLException e) {  
	    	 e.printStackTrace();   
	     }  
 
}
public  void Delete(){//ɾ�����ݿ��е���������
	  
	 sql = "select *from Table_similar";//SQL���  
        db1 = new DBHelper(sql);//����DBHelper����  
  
        try {  
        	 stmt=db1.conn.createStatement();
        	 stmt.executeUpdate("DELETE FROM Table_similar WHERE 1=1"); 
            ret = db1.pst.executeQuery();//ִ����䣬�õ������                 
            ret.close(); 
            stmt.close();
            db1.close();//�ر�����  
        } catch (SQLException e) {  
        	System.out.println("���ݿ�����ʧ��");    
        }  
 
}


}

