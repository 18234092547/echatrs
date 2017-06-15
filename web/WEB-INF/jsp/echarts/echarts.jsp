<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
select {
	margin: 0;
}
#loading{
	background-color: #374140;
	opacity:0.8;
	height: 100%;
	width: 100%;
	position: fixed;
	z-index: 9999;
	margin-top: 0px;
	top: 0px;
}
#loading-center{
	width: 100%;
	height: 100%;
	position: relative;
}
#loading-center-absolute {
	position: absolute;
	left: 50%;
	top: 50%;
	height: 150px;
	width: 150px;
	margin-top: -75px;
	margin-left: -75px;
}
#loading-center-absolute-text{
	position: absolute;
	left: 50%;
	top: 50%;
	height: 150px;
	width: 300px;
	margin-top: -150px;
	margin-left: -150px;
	color:#ffffff;
}
.object{
	width: 20px;
	height: 20px;
	background-color: #FFF;
	float: left;
	margin-right: 20px;
	margin-top: 65px;
	-moz-border-radius: 50% 50% 50% 50%;
	-webkit-border-radius: 50% 50% 50% 50%;
	border-radius: 50% 50% 50% 50%;
}
#object_one {	
	-webkit-animation: object_one 1.5s infinite;
	animation: object_one 1.5s infinite;
	}
#object_two {
	-webkit-animation: object_two 1.5s infinite;
	animation: object_two 1.5s infinite;
	-webkit-animation-delay: 0.25s; 
    animation-delay: 0.25s;
	}
#object_three {
    -webkit-animation: object_three 1.5s infinite;
	animation: object_three 1.5s infinite;
	-webkit-animation-delay: 0.5s;
    animation-delay: 0.5s;
	
	}
@-webkit-keyframes object_one {
75% { -webkit-transform: scale(0); }
}
@keyframes object_one {
  75% { 
    transform: scale(0);
    -webkit-transform: scale(0);
  }
}
@-webkit-keyframes object_two {
  75% { -webkit-transform: scale(0); }
}
@keyframes object_two {
  75% { 
    transform: scale(0);
    -webkit-transform:  scale(0);
  }
}
@-webkit-keyframes object_three {
  75% { -webkit-transform: scale(0); }
}
@keyframes object_three {
  75% { 
    transform: scale(0);
    -webkit-transform: scale(0);
  }
}
</style>
</head>
<body>
	<div id="loading">
		<div id="loading-center">
			<div id="loading-center-absolute-text">
				<h2>数据加载中，请稍候</h2>
			</div>
			<div id="loading-center-absolute">
				<div class="object" id="object_one"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_three"></div>
			</div>
		</div>
	</div>
	<div style="width: 445px; margin: 40px auto;">
		<h1 style="text-align:center;">此处设置标题</h1>
		<select name="year" onchange="changeSelect()">
		</select> 
		<select name="month" onchange="changeSelect()">
			<option value="" selected>全年</option>
			<option value="01">1月</option>
			<option value="02">2月</option>
			<option value="03">3月</option>
			<option value="04">4月</option>
			<option value="05">5月</option>
			<option value="06">6月</option>
			<option value="07">7月</option>
			<option value="08">8月</option>
			<option value="09">9月</option>
			<option value="10">10月</option>
			<option value="11">11月</option>
			<option value="12">12月</option>
		</select>
	</div>
	<div id="myModal" class="modal fade modal-lg" tabindex="-1"
		role="dialog" style="width: 1100px; margin-left: -550px;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<!-- 设置标题，下面中设置表头 -->
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>name</th>
								<th>type</th>
								<th>sign</th>
								<th>mode</th>
								<th>c1</th>
								<th>c2</th>
								<th>c3</th>
								<th>recTime</th>
								<th>spe</th>
								<th>featureDep</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<div id="paper-container" class="pagination pagination-centered">
						<ul >
							
						</ul>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 80%; height: 600px; margin: 0 auto;"></div>
	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));
		var paperContainer = $("#paper-container");
		var queryDate = "";
		var currentPage = 1;
		var options = {
			    alignment:"center",//居中显示
			    currentPage: currentPage,//当前页数
			    totalPages: 1,//总页数 注意不是总条数
			    onPageClicked:function(e,originalEvent,type,page){
			    	if (page == currentPage) {
		                return "javascript:void(0)";
		            } else {
		            	currentPage = page;
		            	getTableItem();
		            }
                }
			}
		paperContainer.bootstrapPaginator(options);
		myChart.on('click', function(params) {
			currentPage = 1;
			if (!monthSelect.val()) {
				var month = params.name.replace("月", "");
				if (month < 10) {
					month = "0" + month;
				}
				monthSelect.val(month);
				changeSelect();
			} else {
				var day = params.name.replace("日", "");
				if (day < 10) {
					day = "0" + day;
				}
				queryDate = yearSelect.val() + "-" + monthSelect.val() + "-" + day;
				getTableItem();
			}
		});
		
		function getTableItem(){
			$('#loading').show();
			$.post(contextPath + "/rec/detail.do", {
				currentPage:currentPage,
				date : queryDate
			}, function(data) {
				var list = data.list;
				var totalPages = data.totalPages;
				paperContainer.bootstrapPaginator({
					totalPages:totalPages
				});
				$(".table tbody").empty();
				for (var i = 0; i < list.length; i++) {
					var item = list[i];
					// 设置内容
					$("<tr>"
						+ "<td>"+ item.name+ "</td>"
						+ "<td>"+ item.type+ "</td>"
						+ "<td>"+ item.sign+ "</td>"
						+ "<td>"+ item.mode+ "</td>"
						+ "<td>"+ item.c1+ "</td>"
						+ "<td>"+ item.c2+ "</td>"
						+ "<td>"+ item.c3+ "</td>"
						+ "<td>"+ new Date(item.recTime).Format("yyyy-MM-dd hh:mm:ss")+ "</td>" 
						+ "<td>" + item.spe + "</td>"
						+ "<td>" + item.featureDep + "</td>"
					+"</tr>").appendTo(".table tbody");
				}
				$('#myModal').modal();
				$('#loading').hide();
			});
		}
		var yearSelect = $("select[name='year']");
		var monthSelect = $("select[name='month']");
		initYearSelect();
		changeSelect();
		function initYearSelect() {
			var nowDate = new Date();
			var nowYear = nowDate.getFullYear();
			for (var i = 5; i >= 0; i--) {
				var y = nowYear + i;
				yearSelect
						.append("<option value=" + y + ">" + y + "年</option>");
			}
			for (var i = 1; i < 6; i++) {
				var y = nowYear - i;
				yearSelect
						.append("<option value=" + y + ">" + y + "年</option>");
			}
			yearSelect.val(nowYear);
		}
		function changeSelect() {
			$('#loading').show();
			var year = yearSelect.val();
			var month = monthSelect.val();
			var date;
			if (month) {
				date = year + "-" + month;
			} else {
				date = year;
			}
			$.ajax({
				type : "POST",
				url : contextPath + "/rec/statis.do",
				data : {
					date : date
				},
				dataType : "json",
				async : true,
				success : function(data) {
					$('#loading').hide();
					myChart.hideLoading();
					var chartDataSum = data.chartDataSum;
					var chartDataSpeSum = data.chartDataSpeSum;
					var series = [];
					var xAxisData = [];
					var data0 = data.list[0];
					var data1 = data.list[1];
					data0.itemStyle = {
						normal : {
							color : "#3598DB"
						}
					}
					data1.itemStyle = {
						normal : {
							color : "#E84C3D"
						}
					}
					series.push(data0);
					series.push(data1);
					var xLength = data0.data.length;
					for (var i = 0; i < xLength; i++) {
						if (xLength < 28) {
							xAxisData.push(parseInt(i + 1) + "月");
						} else if (xLength >= 28) {
							xAxisData.push(parseInt(i + 1) + "日");
						}
					}

					var option = {
						// 在这里设置统计图的标题，修改text的值即可
						title : {
							text : '总数:' + chartDataSum + '  spe总数:' + chartDataSpeSum,
							x : "left",
							left : 20
										
							
						},
						legend : {
							data : [ data0.name, data1.name ],
							x : "center"
						},
						toolbox : {
							feature : {
								magicType : {
									type : [ 'line', 'bar' ]
								}
							},
							right : 20
						},
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
						series : series
					};
					myChart.setOption(option);
				}
			});
		}
	</script>
</body>
</html>