<%  
    String contextPath = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + contextPath + "/";  
    pageContext.setAttribute("basePath",basePath);    
%>

<script type="text/javascript">
var contextPath = "<%=contextPath %>";
</script>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/lib/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=contextPath %>/js/lib/bootstrap/css/bootstrap-theme.min.css"/>
<script src="<%=contextPath %>/js/lib/jquery-1.10.2.min.js"></script>
<script src="<%=contextPath %>/js/lib/echarts.common.min.js"></script>
<script src="<%=contextPath %>/js/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=contextPath %>/js/lib/bootstrap/js/bootstrap-paginator.min.js"></script>    
<script src="<%=contextPath %>/js/lib/My97DatePicker/WdatePicker.js"></script>
<script src="<%=contextPath %>/js/lib/DateFormate.js"></script>  
