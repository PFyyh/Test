layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;

	// 自定义验证规则
	form.verify({
		title : function(value) {
			if (value.length <= 0) {
				return '请输入班级名称';
			}
		},
	// phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！'],
	// email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/,
	// '请输入正确的邮箱']
	});

	/* 异步将老师信息数据填充到老师Select */
	$(function() {
		 var url = '../../Teacher.do?method=selectTeacherAll';
		//var url = '../../json/teacher.json';
		$.getJSON(url, function(result) {
			console.log(result);
			var teachers = result.data;
			console.log("teacher");
			console.log(teachers);
			var teacherSelect = $("#teacherSelect");
			console.log(teacherSelect);
			$.each(teachers, function(index, data) {
				console.log(data);
				var deptOption = $("<option value='" + data.userId + "'>"
						+ data.userName + "</option>");
				console.log(deptOption);
				teacherSelect.append(deptOption);
			});
			form.render();
		});
	});

	form.on("submit(addLink)", function(data) {
		var data = {
			"peclassId" : $("#peclassId").val(),
			"peclassName" : $("#peclassName").val(),
			"teacherId" : $("#teacherSelect").val(),
			"majorId" : $("#majorSelect").val(),
			"peclassTime" : $("#peclassTime").val()
		};
		if ($("#operation").val() == "add") {
			var url = "../../PEClass.do?method=addPEClass";
			var msg = "体育班级添加成功";
		} else {
			var url = "../../PEClass.do?method=updatePEClass";
			var msg = "体育班级修改成功";
		}
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				 if (result) {
						alert("操作成功");
					}else{
						alert("操作失败");
					} 
				// 当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				parent.layer.close(index); // 再执行关闭
				// 刷新父页面
				window.parent.location.reload();
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 状态码
				console.log(XMLHttpRequest.status);
				// 状态
				console.log(XMLHttpRequest.readyState);
				// 错误信息
				console.log(textStatus);
			}
		});
		return false;
	})

})