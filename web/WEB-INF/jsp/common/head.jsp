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
<script src="<%=contextPath %>/js/lib/jquery-1.10.2.min.js"></script>
<script src="<%=contextPath %>/js/lib/echarts.simple.min.js"></script>    
