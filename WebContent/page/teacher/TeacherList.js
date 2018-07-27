layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;

	// 教师信息列表
	var tableIns = table.render({
		elem: '#teacherList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../Teacher.do?method=getTeacherAll',
		id: "TeacherListTable",
		page:true,
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'userId',
					title: '职工编号',
					align: "center",
					sort: true
				},
				{
					field: 'userName',
					title: '职工姓名',
					align: "center"
				},
				{
					field: 'userTel',
					title: '联系电话',
					align: "center"
				},
				{
					field: 'userEmail',
					title: '邮箱',
					align: "center"
				}
			]
		]
	});

	// 添加教师
	function addTeacher(edit) {
		var index = layui.layer.open({
			title: "教师信息维护",
			type: 2,
			area:['400px','350px'],
			content: "../../page/teacher/TeacherAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					body.find("#operation").val("修改");
					body.find("#teacherName").val(edit[0].userName);
					body.find("#teacherId").val(edit[0].userId).attr('readonly', true);
					body.find("#teacherTel").val(edit[0].userTel);
					body.find("#teacherEmail").val(edit[0].userEmail);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回教师信息列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	// 添加教师信息列表
	$(".addTeacher_btn").click(function() {
		addTeacher();
	})

	// 修改教师信息列表
	$(".editTeacher_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addTeacher(data)
		 * 3、否，则提示“请选择要修改的老师”。
		 */
		var checkStatus = table.checkStatus('TeacherListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择老师", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择老师信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addTeacher(data);
		}

		// var checkStatus = table.checkStatus('FcultyListTable');
		// layer.msg(checkStatus.isAll ? '全选': '未全选')

	})
	// 删除老师
	$(".delTeacher_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、否，则提示“请选择要删除的院系”。 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('TeacherListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择老师", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止恶意删除，一次只能删除一个老师", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此老师？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var teacherId=data[0].userId;
                 $.getJSON("../../Teacher.do?method=delTeacher",{
                	 teacherId // 将需要删除的老师参数传入
                 },function(result){
                	 if (result) {
                		 top.layer.msg("删除教师成功");
                		 }else {
                			 top.layer.msg("删除教师失败");
                			 }
                	 tableIns.reload();
                	 layer.close(index);

                 })
			});
		}
	})
})