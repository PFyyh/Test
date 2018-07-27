layui.use([ 'form', 'jquery', 'layer' ], function() {
	var form = layui.form;
	var $ = layui.jquery;
	var layer = layui.layer;
	// 自定义验证规则
	form.verify({
		title: [/^[\u0391-\uFFE5]+$/, '请输入学院名称,只允许汉字'],
	// phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！'],
	// email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/,
	// '请输入正确的邮箱']
	});

	form.on("submit(addLink)", function(data) {
		if ($("#facultyId").val() == -1) {
			var url = "../../Faculty.do?method=addFaculty";
			var datapost = {
				"facultyName" : $("#facultyName").val()
			};
			var msg = "学院添加成功";
		} else {
			var url = "../../Faculty.do?method=updateFaculty";
			var datapost = {
				"facultyName" : $("#facultyName").val(),
				"facultyId" : $("#facultyId").val()
			};
			var msg = "学院修改成功";
		}

		$.ajax({
			url : url,
			type : "post",
			data : datapost,
			dataType:"json",
			success : function(result) {

				if (result) {
									top.layer.msg(msg);
								}else {
									top.layer.msg("操作失败");
								}
								// 当你在iframe页面关闭自身时
								var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
								// 刷新父页面
								window.parent.location.reload();
								parent.layer.close(index); // 再执行关闭


			},
			error : function(result) {
				top.layer.msg("请求异常，等会儿再试？");
				// 当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				// 刷新父页面
				window.parent.location.reload();
				parent.layer.close(index); // 再执行关闭
			}
		});
		return false;
	})

})