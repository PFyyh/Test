layui.use([ 'form', 'jquery' ], function() {
	var form = layui.form;
	var $ = layui.jquery;

	/* 异步获取学院数据填充到学院Select */
	$(function() {
		var url = '../../Faculty.do?method=getFacultyAll';
		$.getJSON(url, function(result) {
			console.log(result);
			var faculty = result.data;
			console.log("faculty");
			console.log(faculty);
			var facultySelect = $("#facultySelect");
			console.log(facultySelect);
			$.each(faculty, function(index, data) {
				console.log(data);
				var deptOption = $("<option value='" + data.facultyId + "'>"
						+ data.facultyName + "</option>");
				console.log(deptOption);
				facultySelect.append(deptOption);
			});
			form.render();
		});
	});

	// 自定义验证规则
	form.verify({
		majorName : [ /^[\u0391-\uFFE5]+$/, '请输入专业名称,只允许汉字' ]
	});

	form.on("submit(addLink)", function(data) {
		if ($("#operation").val() == "添加") {
			var url = "../../Major.do?method=addMajor";
			var data = {
				"majorName" : $("#majorName").val(),
				"facultyId" : $("#facultySelect").val()
			};
		} else {
			var url = "../../Major.do?method=updateMajor";
			var data = {
				"majorId" : $("#majorId").val(),
				"majorName" : $("#majorName").val(),
				"facultyId" : $("#facultySelect").val()
			};
		}
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {

				if (result) {
					top.layer.msg($("#operation").val() + "操作成功");
				} else {
					top.layer.msg($("#operation").val() + "操作失败");
				}
				// 当你在iframe页面关闭自身时
				var index = parent.layer.getFrameIndex(window.name); // 先得到当前iframe层的索引
				// 刷新父页面
				window.parent.location.reload();
				parent.layer.close(index); // 再执行关闭

			},
			error : function(result) {
				top.layer.msg($("#operation").val() + "请求异常，等会儿再试？");
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