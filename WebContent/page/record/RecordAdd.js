layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;

	// 自定义验证规则
	form.verify({
		title : function(value) {
			if (value.length <= 0) {
				return '请输入院系名称';
			}
		},
	// phone: [/^1[3|4|5|7|8]\d{9}$/, '手机必须11位，只能是数字！'],
	// email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/,
	// '请输入正确的邮箱']
	});

	form.on("submit(addLink)", function(data) {
		var data = {
			"stuNo" : $("#stuNo").val(),
			"stuName" : $("#stuName").val(),
			"year" : $("#year").val(),
			"height" : $("#height").val(),
			"weight" : $("#weight").val(),
			"vitalCapacity" : $("#vitalCapacity").val(),
			"beginToBend" : $("#beginToBend").val(),
			"standingBroadJump" : $("#standingBroadJump").val(),
			"sitUp" : $("#sitUp").val(),
			"fivetyRun" : $("#fivetyRun").val(),
			"thousandRun" : $("#thousandRun").val()
		};
		if ($("#operation").val() == "add") {
			var url = "../../Record.do?method=addRecord";
			var msg = "体侧记录添加成功";

		} else {
			var url = "../../Record.do?method=updateRecord";
			var msg = "体侧记录修改成功";
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
						top.layer.close(index);
						top.layer.msg(msg);
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