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
		//����ҳ������
		fileName = request.getParameter("fileName");//�ļ���
		String  words = request.getParameter("word");//ָ������
		String wordnum = request.getParameter("wordnum");//��Ƶ��
		String wordlines = request.getParameter("wordlines");//����
		String result = request.getParameter("result");//���
		id=request.getParameter("id");//���ܱ�ŵĻ�ȡ
		//�ļ��ϴ�
		if(id.equals("0")){
			System.out.println("ddddd");
			WordCount wordCount = new WordCount();//���ܱ��� ��������
			map=wordCount.up(fileName);
			System.out.println(map);
			out.print("<script>alert('�ϴ��ɹ�����');location.href='index.jsp'</script>");	
			
		}else if(id.equals("1")){
			//ָ�����ʵĲ���
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			TreeMap<String,Integer> map1 = new TreeMap<String, Integer>(); 
			WordCount wordCount = new WordCount();
		    map1= wordCount.bijiao(map, words);
		    //����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime=(float)((endTime-startTime)/1000)*1000;
			//��jspҳ�洫����
			request.setAttribute("map1", map1);
			request.setAttribute("excTime", excTime);
			System.out.println("ִ��ʱ�䣺"+excTime+"ms"); 
            request.getRequestDispatcher("index.jsp").forward(request, response);//�ض���
		}else if(id.equals("2")){
			//��Ƶ�ʵ�ͳ��
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			int k=Integer.parseInt(wordnum);
			ArrayList gaopin=wordCount.gaopin(map, k);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime2=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("gaopin", gaopin);
			request.setAttribute("excTime2", excTime2);
	        request.getRequestDispatcher("html/WordCount.jsp").forward(request, response);
			
		}else if(id.equals("3")){
			//����ͳ��
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount wordCount = new WordCount();
			List list=wordCount.statistics(fileName);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime3=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime3", excTime3);
			request.setAttribute("list", list);
			request.getRequestDispatcher("html/index.jsp").forward(request, response);
		}else if(id.equals("4")){
			//д���ļ�
			//��ʼʱ��
			long startTime=System.currentTimeMillis();
			WordCount cunfang = new WordCount();
			cunfang.cunfang(map);
			//����ʱ��
			long endTime=System.currentTimeMillis();
			float excTime1=(float)((endTime-startTime)/1000)*1000;
			request.setAttribute("excTime1", excTime1);
			out.print("<script>alert('�洢�ɹ�����');location.href='html/WordCount.jsp'</script>");				   
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
