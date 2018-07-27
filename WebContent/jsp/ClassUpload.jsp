<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>upload模块快速使用</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/layui/css/layui.css">
</head>
<body>

	<button type="button" class="layui-btn" id="test1">
		<i class="layui-icon">&#xe67c;</i>上传图片
	</button>

	<script src="<%=request.getContextPath()%>/layui/layui.js"></script>
	<script>
layui.use('upload', function(){
  var upload = layui.upload;
   
  //执行实例
  var uploadInst = upload.render({
    elem: '#test1' //绑定元素
    ,url: '<%=request.getContextPath()%>/Class.do?method=upload' ,
				accept : 'file',
				done : function(res) {
					if (res.code == 0) {
						console.log("hello");
					}
				},
				error : function() {
						cosonle.log("请求异常");
				}
			});
		});
	</script>
</body>
</html>