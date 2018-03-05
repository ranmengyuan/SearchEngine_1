package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import searchEngine.Demo_web;
import searchEngine.Search;

@SuppressWarnings("serial")
public class Manage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Manage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)//���յ��������Ľ��
			throws ServletException, IOException {
        String keyword =request.getParameter("keyword");
        keyword= new String(keyword.getBytes("iso-8859-1"),"UTF-8");//ע���ֵĸ�ʽ����������
        Search search=new Search();
        String keywords=Retail(keyword);
    	Map<String,Integer> Title=new HashMap<String,Integer>(search.formTitle(keywords));//�ѵ�����ҳ��Ŀ��������������ͬ�Ľ��
		Map<String,Integer> content=new HashMap<String,Integer>(search.formConcept(keywords));//�ѵ�����ҳ����������������ͬ�Ľ��
        ArrayList<String> url=new ArrayList<String>();
        ArrayList<String> title=new ArrayList<String>();
        for(String key:Title.keySet()){//��Ҫ����Ҫ�����ַ�������ǵı���浽�������ݿ�
        	url.add(key);
        	Demo_web demoWeb=new Demo_web();
        	title.add(demoWeb.Look(key));
        }
        for(String key1:content.keySet()){
        	if(Replace(url,key1)==0){//�ж��ѵ��Ľ���Ƿ����ظ�
	        	url.add(key1);
	        	Demo_web demoWeb=new Demo_web();
	        	title.add(demoWeb.Look(key1));
        	}
        }
        
        request.getSession().setAttribute("url", url);//�ض��򣬽����ݴ���JSP��
        request.getSession().setAttribute("title", title);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	public int Replace(ArrayList<String> array,String url){//�ж��ظ�
		int flag=0;
		for(int i=0;i<array.size();i++){
			if(url.compareTo(array.get(i))==0){
				flag=1;
				break;
			}
		}
		return flag;
	}
	public String Retail(String keyword){
		String retail;
		retail=keyword.replaceAll(" ", "");
		return retail;
	}

}
