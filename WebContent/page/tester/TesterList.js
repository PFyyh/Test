layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		laytpl = layui.laytpl,
		table = layui.table;

	// 测试员信息列表
	var tableIns = table.render({
		elem: '#testerList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../Tester.do?method=getTesterAll',
		id: "TesterListTable",
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'userId',
					title: '测试人员编号',
					align: "center",
					sort: true
				},
				{
					field: 'userName',
					title: '测试人员姓名',
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

	// 添加测试员
	function addTester(edit) {
		var index = layui.layer.open({
			title: "测试员信息维护",
			type: 2,
			area:['500px','390px'],
			content: "../../page/tester/TesterAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					body.find("#operation").val("修改");
					body.find("#testerName").val(edit[0].userName);
					body.find("#testerId").val (edit[0].userId).attr('readonly', true);
					body.find("#testerTel").val(edit[0].userTel);
					body.find("#testerEmail").val(edit[0].userEmail);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回测试员信息列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	// 添加测试员信息列表
	$(".addTester_btn").click(function() {
		addTester();
	})

	// 修改测试员信息列表
	$(".editTester_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addTester(data) 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('TesterListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择测试人员", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择一个测试人员信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addTester(data);
		}

		// var checkStatus = table.checkStatus('FcultyListTable');
		// layer.msg(checkStatus.isAll ? '全选': '未全选')

	})
	// 删除学院
	$(".delTester_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、否，则提示“请选择要删除的院系”。 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('TesterListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择测试人员", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止恶意删除，一次只能删除一个测试人员", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此测试人员？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var teacherId=data[0].userId;
                 $.getJSON("../../Tester.do?method=delTester",{
                	 teacherId // 将需要删除的学院参数传入
                 },function(result){
                	 if (result) {
 	 					top.layer.msg("删除测试员成功");
 	 				}else {
 	 					top.layer.msg("删除测试员失败");
 	 				}
 	            	 tableIns.reload();
 	            	 layer.close(index);
                 })
			});
		}
	})
})