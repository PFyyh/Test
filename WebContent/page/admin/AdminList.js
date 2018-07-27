layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;

	// 管理员信息列表
	var tableIns = table.render({
		elem: '#adminList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../Admin.do?method=getAdminAll',
		id: "AdminListTable",
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'userId',
					title: '管理员编号',
					align: "center",
					sort: true
				},
				{
					field: 'userName',
					title: '管理员姓名',
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

	// 添加管理员
	function addAdmin(edit) {
		var index = layui.layer.open({
			title: "管理员信息维护",
			type: 2,
			area:['400px','400px'],
			content: "../../page/admin/AdminAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					// layer.alert(edit[0].facultyname);
					// body.contents().find("#facultyname").val(edit[0].facultyname);
					body.find("#operation").val("修改");
					body.find("#adminName").val(edit[0].userName);
					body.find("#adminId").val(edit[0].userId).attr('readonly', true);
					body.find("#adminTel").val(edit[0].userTel);
					body.find("#adminEmail").val(edit[0].userEmail);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回管理员信息列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	// 添加管理员信息列表
	$(".addAdmin_btn").click(function() {
		addAdmin();
	})

	// 修改管理员信息列表
	$(".editAdmin_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addFculty(data) 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('AdminListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择管理员", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择管理员信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addAdmin(data);
		}

		// var checkStatus = table.checkStatus('FcultyListTable');
		// layer.msg(checkStatus.isAll ? '全选': '未全选')

	})
	// 删除管理员
	$(".delAdmin_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、否，则提示“请选择要删除的院系”。 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('AdminListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择管理员", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止恶意删除，一次只能删除一个管理员", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此管理员？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var adminId=data[0].userId;
	             $.getJSON("../../Admin.do?method=delAdmin",{
	            	 adminId
	             },function(result){
	            	 if (result) {
	 					top.layer.msg("删除管理员成功");
	 				}else {
	 					top.layer.msg("删除管理员失败");
	 				}
	            	 tableIns.reload();
	            	 layer.close(index);
	             })
			});
		}
	})
})