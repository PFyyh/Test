<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>table模块快速使用</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/layui/css/layui.css">
</head>
<body>
 
<table id="demo" lay-filter="test"></table>
 
<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
<script>
layui.use('table', function(){
  var table = layui.table;
  
  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 500
    ,url: '<%=request.getContextPath()%>/Major.do?method=searchPage' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'majorId', title: '专业编号', width:80, sort: true, fixed: 'left'}
      ,{field: 'majorName', title: '专业名', width:80}
      ,{field:'facultyId',title:'所属学院编号',width:80}
      ,{field:'facultyName',title:'所属学院名称',width:80}
    ]]
    ,request: {
    	pageName: 'pageIndex',
    	limitName: 'pageSize'
    },
 
    done: function(res, curr, count){
        //如果是异步请求数据方式，res即为你接口返回的信息。
        //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
        console.log("hello");
        console.log(res);
        
        //得到当前页码
        console.log(curr); 
        
        //得到数据总量
        console.log(count);
      }
    
  });
  
});	
</script>
</body>
</html>