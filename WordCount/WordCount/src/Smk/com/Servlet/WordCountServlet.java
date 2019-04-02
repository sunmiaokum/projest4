package Smk.com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Smk.com.WordCount.WordCount;

public class WordCountServlet extends HttpServlet {
	public static String fileName;
	public static TreeMap<String,Integer> map;
	public static String[] str;
	/**
	 * Constructor of the object.
	 */
	public WordCountServlet() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//
		String id=null;
		//接受页面数据
		fileName = request.getParameter("fileName");//文件名
		String  words = request.getParameter("word");//指定单词
		String wordnum = request.getParameter("wordnum");//高频数
		String wordlines = request.getParameter("wordlines");//行数
		String result = request.getParameter("result");//存放
		id=request.getParameter("id");//功能编号的获取
		//文件上传
		if(id.equals("0")){
			System.out.println("ddddd");
			WordCount wordCount = new WordCount();//接受变量 声明对象
			map=wordCount.up(fileName);
			System.out.println(map);
			out.print("<script>alert('上传成功！！');location.href='index.jsp'</script>");	
			
		}else if(id.equals("1")){
			//指定单词的查找
			//开始时间
			long startTime=System.currentTimeMillis();
			TreeMap<String,Integer> map1 = new TreeMap<String, Integer>(); 
			WordCount wordCount = new WordCount();
		    map1= wordCount.bijiao(map, words);
		    //结束时间
			long endTime=System.currentTimeMillis();
			float excTime=(float)((endTime-startTime)/1000)*1000;
			//向jsp页面传参数
			request.setAttribute("map1", map1);
			request.setAttribute("excTime", excTime);
			System.out.println("执行时间："+excTime+"ms"); 
            request.getRequestDispatcher("index.jsp").forward(request, response);//重定向
		}else if(id.equals("2")){
			//高频词的统计
			//开始时间
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			int k=Integer.parseInt(wordnum);
			ArrayList gaopin=wordCount.gaopin(map, k);
			//结束时间
			long endTime=System.currentTimeMillis();
			float excTime2=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("gaopin", gaopin);
			request.setAttribute("excTime2", excTime2);
	        request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
			
		}else if(id.equals("3")){
			//行数统计
			//开始时间
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			List list=wordCount.statistics(fileName);
			//结束时间
			long endTime=System.currentTimeMillis();
			float excTime3=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime3", excTime3);
			request.setAttribute("list", list);
			request.getRequestDispatcher("html/index.jsp").forward(request, response);
		}else if(id.equals("4")){
			//写入文件
			//开始时间
			long startTime=System.currentTimeMillis();
			WordCount cunfang = new WordCount();
			cunfang.cunfang(map);
			//结束时间
			long endTime=System.currentTimeMillis();
			float excTime1=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime1", excTime1);
			out.print("<script>alert('存储成功！！');location.href='html/WordCount.jsp'</script>");				   
			request.getRequestDispatcher("html/index.jsp").forward(request, response);
		}		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
	public void init() throws ServletException {
		
	}

}
