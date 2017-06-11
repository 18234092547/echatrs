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
</style>
</head>
<body>
	<div style="width: 445px; margin: 40px auto;">
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
	<div id="myModal" class="modal fade modal-lg" tabindex="-1" role="dialog" style="width:1100px;margin-left:-550px;">
	  <div class="modal-dialog"  role="document" >
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <!-- 设置标题，下面thread中设置表头 -->
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
	        <table class="table table-striped">
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
				<tbody></tbody>
			</table>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
	<div id="main" style="width: 80%; height: 600px;margin:0 auto;"></div>
	<script type="text/javascript">
	var myChart = echarts.init(document.getElementById('main'));
	var yearSelect = $("select[name='year']");
	var monthSelect = $("select[name='month']");
	initYearSelect();
	changeSelect();
	function initYearSelect(){
		var nowDate = new Date();
		var nowYear = nowDate.getFullYear();
		for(var i = 5; i >= 0; i--){
			var y = nowYear + i;
			yearSelect.append("<option value=" + y + ">" + y + "年</option>");
		}
		for(var i = 1; i < 6; i++){
			var y = nowYear - i;
			yearSelect.append("<option value=" + y + ">" + y + "年</option>");
		}
		yearSelect.val(nowYear);
	}
	function changeSelect() {
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
					// 在这里设置统计图的标题，修改text的值即可
					title : {
						text : '设置标题',
						x:"left",
						left:20
					},
					legend: {
				        data:[data[0].name,data[1].name],
				        x:"center"
				    },
				    toolbox:{
				    	feature:{
				    		magicType:{
				    			 type: ['line', 'bar', 'stack']
				    		}
				    	},
				    	right:20
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
					series : data
				};
				myChart.setOption(option);
				myChart.on('click', function(params) {
					if(!monthSelect.val()){
						var month = params.name.replace("月","");
						if(month < 10){
							month = "0" + month;
						}
						monthSelect.val(month);
						changeSelect();
					}else{
						var day = params.name.replace("日","");
						if(day < 10){
							day = "0" + day;
						}
						$.post(contextPath + "/rec/detail.do",{date:yearSelect.val() + "-" + monthSelect.val() + "-" + day},function(list){
							$(".table tbody").empty();
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
							$('#myModal').modal();
						});
					
					}
				});

			}
		});
	}
	</script>
</body>
</html>