layui.use([ 'form', 'jquery', 'laydate' ], function() {
	var laydate = layui.laydate;
	// 年选择器
	laydate.render({
		elem : '#classYear',
		type : 'year'
	});
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

	/* 异步测试员信息数据填充到测试员Select */
	$(function() {
		var url = '../../Tester.do?method=selectTesterAll';
		// var url = '../../json/tester.json';
		$.getJSON(url, function(result) {
			console.log(result);
			var testers = result.data;
			console.log("testers");
			console.log(testers);
			var testerSelect = $("#testerSelect");
			console.log(testerSelect);
			$.each(testers, function(index, data) {
				console.log(data);
				var deptOption = $("<option value='" + data.userId + "'>"
						+ data.userName + "</option>");
				console.log(deptOption);
				testerSelect.append(deptOption);
			});
			form.render();
		});
	});

	/* 异步专业信息数据填充到专业Select */
	$(function() {
		var url = '../../Major.do?method=selectMajorAll';
		$.getJSON(url, function(result) {
			console.log(result);
			var majors = result.data;
			console.log("majors");
			console.log(majors);
			var majorSelect = $("#majorSelect");
			console.log(testerSelect);
			$.each(majors, function(index, data) {
				console.log(data);
				var deptOption = $("<option value='" + data.majorId + "'>"
						+ data.majorName + "</option>");
				console.log(deptOption);
				majorSelect.append(deptOption);
			});
			form.render();
		});
	});

	form.on("submit(addLink)", function(data) {
		// alert($("#operation").val());
		// alert($("#testerSelect").val());
		// alert($("#majorSelect").val());
		var data = {
			"classId" : $("#classId").val(),
			"className" : $("#className").val(),
			"testerId" : $("#testerSelect").val(),
			"majorId" : $("#majorSelect").val(),
			"classYear" : $("#classYear").val()
		};

		if ($("#operation").val() == "add") {
			var url = "../../Class.do?method=addClass";
			var msg = "班级添加成功！"
		} else {
			var url = "../../Class.do?method=updateClass";
			var msg = "班级修改成功！"
		}
		$.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			success : function(result) {
				if (result) {
					alert("操作班级成功");
				} else {
					alert("操作班级失败");
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