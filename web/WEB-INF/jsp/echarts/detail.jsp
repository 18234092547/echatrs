<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		var list = ${list};
		for(var i = 0; i < list.length; i++){
			var item = list[i];
			// 设置内容
			$(
				"<tr>"+
					"<td>"+ item.name +"</td>"+
					"<td>"+ item.type +"</td>"+
					"<td>"+ item.sign +"</td>"+
					"<td>"+ item.mode +"</td>"+
					"<td>"+ item.c1 +"</td>"+
					"<td>"+ item.c2 +"</td>"+
					"<td>"+ item.c3 +"</td>"+
					"<td>"+ new Date(item.recTime).Format("yyyy-MM-dd hh:mm:ss") +"</td>"+
					"<td>"+ item.spe +"</td>"+
					"<td>"+ item.featureDep +"</td>"+
				+"</tr>"
			).appendTo(".table tbody");
		}
	});

</script>
</head>
<body>
	<table class="table table-striped">
	<!-- 设置标题，下面thread中设置表头 -->
		<caption>设置标题</caption>
		<thead>
			<tr>
				<th>
					name
				</th>
				<th>
					type
				</th>
				<th>
					sign
				</th>
				<th>
					mode
				</th>
				<th>
					c1
				</th>
				<th>
					c2
				</th>
				<th>
					c3
				</th>
				<th>
					recTime
				</th>
				<th>
					spe
				</th>
				<th>
					featureDep
				</th>
			</tr>
		</thead>
		<tbody></<tbody>
	</table>

</body>
</html>