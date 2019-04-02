<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="html/bootstrap.min.css">
    <!-- <script type="text/javascript" src="./jquery.min.js"></script> -->
</head>
<style>
    .two{
        width: 60%;
        height: 50%;
        /* background-color: rgb(228, 194, 228); */
        float: left;
        margin-top: 2%;
        margin-left: 20%;
    }
</style>
<script type="text/javascript">
    window.alert("英文单词统计");
</script>
<%
    TreeMap<String,Integer> map1=(TreeMap)request.getAttribute("map1");    
    ArrayList gaopin =(ArrayList)request.getAttribute("gaopin");
    List list=(List)request.getAttribute("list");
    
 	
%>
  <body>
    <!--2.将js代码嵌入在html标签内部-->
	<div class="one" onclick="alert('点击弹出');"></div>
	<div class="two">
        <form method="get">
			<fieldset>
				<legend>英文单词统计</legend>
				<div class="form-group">
					<label>1.请输入英文文本路径</label>
                    <input type="file" class="form-control" value="请输入英文文本" id="file">
                    <input type="button" id="butt1" value="上传" onclick="one()" /><br />
                    <label>程序统计所需要的时间</label>
                    <input type="text" class="form-control" placeholder="" >
                    
				</div>
				<div class="form-group">
					<label>2.指定单词统计</label>
                    <input type="text" class="form-control" placeholder="请输入指定单词" id="word">
                    <input type="submit" id="butt2" value="统计" onclick="two()" />                     
                    <label>程序统计所需要的时间</label>
                    <input type="text" class="form-control" placeholder="" value="${excTime }">				</div>
				<div class="form-group">
                    <label>3.高频词统计</label>
                    <input type="text" class="form-control" placeholder="请输入高频词">
                    <input type="submit" id="butt2" value="统计" onclick="two()" />                     
                    <label>程序统计所需要的时间</label>
                    <input type="text" class="form-control" placeholder="" value="${excTime2 }">
                </div>
                <div class="form-group">
                    <label>4.行数及字符数统计</label>
                    <input type="text" class="form-control" placeholder="">
                    <input type="submit" id="butt2" value="统计" onclick="two()" />                     
                    <label>程序统计所需要的时间</label>
                    <input type="text" class="form-control" placeholder="" value="${excTime3 }">
                </div>                
				<div class="form-group">
                    <label>5.存放到result.txt中</label>
                    <input type="submit" id="butt2" value="存放" onclick="two()" /><br>                     
                    <label>程序统计所需要的时间</label>
                    <input type="text" class="form-control" placeholder="" value="${excTime1 }">
                </div>  
			</fieldset>
		</form>
    </div> 
		    <!-- 高频词显示 -->
			<table border = 1px align = "center" id="tb1" >
			<tr>
			<td>单词</td>
			<td>数量</td>
			</tr>
			<c:forEach items="${gaopin}" var="gaopin" varStatus="st">
			<tr  >
			<td>${gaopin.key}</td>
			<td>${gaopin.value}</td>
			</tr>
			</c:forEach>
			</table>
	
	<table>
	<!--行数以及字符数的统计  -->
	  <tr>
	     <td>行数</td><td>字符数</td><td>单词数</td>
	  </tr>
	  <c:forEach items="${list}" var="list" varStatus="st">
	 <tr>
	   <td>${list}</td>
	 </tr>
	 </c:forEach>
	</table>
 
    <!-- 指定单词的查找 -->
    <table border = 1px align = "center" id="tab">
	<tr>
	<td>单词</td>
	<td>数量</td>
	</tr>
	<c:forEach items="${map1}" var="map1" varStatus="st">
	
	<tr  >
	<td>${map1.key}</td>
	<td>${map1.value}</td>
	</tr>
	</c:forEach>
	</table>
    <script type="text/javascript">
		$(function(){
			$("input[type=text]").val("请输入文本");
		})
		
		/* 获取文件名的函数 */
	function one() {
		var fileName = document.getElementById("file").value;

		location.href = "../WordCountServlet?fileName=" + fileName + "&id=0";
	}
	function two() {

		var word = document.getElementById("word").value;

		location.href = "../WordCountServlet?word=" + word + "&id=1";
	}
	function three() {
		var wordnum = document.getElementById("wordnum").value;

		location.href = "../WordCountServlet?wordnum=" + wordnum + "&id=2";

	}
	function four() {
		var wordlines = document.getElementById("wordlines").value;
		location.href = "../WordCountServlet?wordlines=" + wordlines + "&id=3";
		
	}
	function five() {
		var result = document.getElementById("result").value;
		location.href = "../WordCountServlet?result=" + result + "&id=4";
	}
    </script>
  </body>
</html>
