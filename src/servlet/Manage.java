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
	public void doPost(HttpServletRequest request, HttpServletResponse response)//接收到输入框传入的结果
			throws ServletException, IOException {
        String keyword =request.getParameter("keyword");
        keyword= new String(keyword.getBytes("iso-8859-1"),"UTF-8");//注意字的格式，避免乱码
        Search search=new Search();
        String keywords=Retail(keyword);
    	Map<String,Integer> Title=new HashMap<String,Integer>(search.formTitle(keywords));//搜到的网页题目与搜索的内容相同的结果
		Map<String,Integer> content=new HashMap<String,Integer>(search.formConcept(keywords));//搜到的网页内容与搜索内容相同的结果
        ArrayList<String> url=new ArrayList<String>();
        ArrayList<String> title=new ArrayList<String>();
        for(String key:Title.keySet()){//将要符合要求的网址，与他们的标题存到两个数据库
        	url.add(key);
        	Demo_web demoWeb=new Demo_web();
        	title.add(demoWeb.Look(key));
        }
        for(String key1:content.keySet()){
        	if(Replace(url,key1)==0){//判断搜到的结果是否有重复
	        	url.add(key1);
	        	Demo_web demoWeb=new Demo_web();
	        	title.add(demoWeb.Look(key1));
        	}
        }
        
        request.getSession().setAttribute("url", url);//重定向，将数据传到JSP中
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
	public int Replace(ArrayList<String> array,String url){//判断重复
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
