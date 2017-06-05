<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	var series = ${data};
	var xAxisData = [];
	var data0 = series[0];
	var xLength = data0.data.length;
	for (var i = 0; i < xLength; i++) {
		if (xLength < 28) {
			xAxisData.push(parseInt(i + 1) + "月");
		} else if (xLength >= 28) {
			xAxisData.push(parseInt(i + 1) + "日");
		}
	}
	option = {
		xAxis : {
			type : 'category',
			interval : 1,
			data : xAxisData,
			boundaryGap:true,
			axisTick:{
				alignWithLabel:true,
				interval:0
			},
			axisLabel:{
				interval:0
			}
		},
		yAxis : [ {
			type : 'value'
		} ],
		series : series
	};
	$(function() {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
		myChart.on('click', function (params) {
		    // 控制台打印数据的名称
		    console.log(params.name);
		    window.location.href = contextPath + "/rec/detail.do"
		});
	});
</script>
</head>
<body>
	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 100%; height: 400px;"></div>

</body>
</html>