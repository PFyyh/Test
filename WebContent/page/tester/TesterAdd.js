layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;

	//管理员验证规则
	form.verify({
		testerId:  [/^[a-zA-Z][a-zA-Z0-9]{4,15}$/, '字母开头，允许5-16字节，允许字母数字'],
		testerName: [/^[\u0391-\uFFE5]+$/, '请输入管理员姓名,只允许汉字'],
		phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！'],
		email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '请输入正确的邮箱']
	});


	form.on("submit(addLink)", function(data) {
		// 弹出loading
		// var index = top.layer.msg('数据提交中，请稍候', {
		// icon: 16,
		// time: false,
		// shade: 0.8
		// });
		var data = {
			"testerName" : $("#testerName").val(),
			"testerId" : $("#testerId").val(),
			"testerTel" : $("#testerTel").val(),
			"testerEmail" : $("#testerEmail").val()
		};
		if ($("#operation").val() == "添加") {
			var msg = $("#operation").val() + "成功";
			var url = "../../Tester.do?method=addTester";
		} else {
			var msg = $("#operation").val() + "成功";
			var url = "../../Tester.do?method=updateTester";
		}
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result) {
					top.layer.msg($("#operation").val()+msg);
				}else {
					top.layer.msg($("#operation").val()+"操作失败");
				}
				// 当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				// 刷新父页面
				window.parent.location.reload();
				parent.layer.close(index); // 再执行关闭
			},
			error : function(result) {
				top.layer.msg($("#operation").val()+"请求异常，等会儿再试？");
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