<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

	$(function() {
		var myChart = echarts.init(document.getElementById('main'));
		$("#search").click(function() {
			var year = $("[name='year']").val();
			var month = $("[name='month']").val();
			var date;
			if (month) {
				date = year + "-" + month;
			} else {
				date = year;
			}
			btnClick(date);
		});
		
		function btnClick(date) {
			console.log(date)
			$.ajax({
				type : "POST",
				url : contextPath + "/rec/statis.do",
				data : {
					date : date
				},
				dataType : "json",
				async : false,
				success : function(data) {
					var xAxisData = [];
					var data0 = data[0];
					var xLength = data0.data.length;
					for (var i = 0; i < xLength; i++) {
						if (xLength < 28) {
							xAxisData.push(parseInt(i + 1) + "月");
						} else if (xLength >= 28) {
							xAxisData.push(parseInt(i + 1) + "日");
						}
					}
					var option = {
						xAxis : {
							type : 'category',
							interval : 1,
							data : xAxisData,
							boundaryGap : true,
							axisTick : {
								alignWithLabel : true,
								interval : 0
							},
							axisLabel : {
								interval : 0
							}
						},
						yAxis : [ {
							type : 'value'
						} ],
						series : data
					};
					myChart.setOption(option);
					myChart.on('click', function (params) {
					// 控制台打印数据的名称
					    console.log(params.name);
					//    window.location.href = contextPath + "/rec/detail.do"
					});

				}
			});
		}
	});
	
	
</script>
</head>
<body>
	<div>
		<select name="year">
			<option value="2017" selected>2017年</option>
			<option value="2016">2016年</option>
			<option value="2015">2015年</option>
			<option value="2014">2014年</option>
			<option value="2013">2013年</option>
			<option value="2012">2012年</option>
			<option value="2011">2011年</option>
		</select> 
		<select name="month">
			<option value="" selected>全年</option>
			<option value="1">1月</option>
			<option value="2">2月</option>
			<option value="3">3月</option>
			<option value="4">4月</option>
			<option value="5">5月</option>
			<option value="6">6月</option>
			<option value="7">7月</option>
			<option value="8">8月</option>
			<option value="9">9月</option>
			<option value="10">10月</option>
			<option value="11">11月</option>
			<option value="12">12月</option>
		</select> 
		<a id="search">查询</a>
	</div>

	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 100%; height: 400px;"></div>

</body>
</html>