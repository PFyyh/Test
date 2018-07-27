layui.use(['form', 'layer', 'table'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		table = layui.table;

	//学院列表
	var tableIns = table.render({
		elem: '#FacultyList', //指定原始表格元素选择器（推荐id选择器）
		url: '../../Faculty.do?method=getFacultyAll',
		id: "FacultyListTable",
		page:false,
		area:['380px','300px'],
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'facultyId',
					title: '学院编号',
					align: "center",
					sort: true
				},
				{
					field: 'facultyName',
					title: '学院名称',
					align: "center"
				}
			]
		]
	});

	//添加学院
	function addFaculty(edit) {
		var index = layui.layer.open({
			title: "维护学院信息",
			type: 2,
			content: "../../page/faculty/FacultyAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					//layer.alert(edit[0].facultyName);
					// body.contents().find("#facultyName").val(edit[0].facultyName);
					body.find("#facultyName").val(edit[0].facultyName);
					body.find("#facultyId").attr("value", edit[0].facultyId);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回学院列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	//添加学院
	$(".addFaculty_btn").click(function() {
		addFaculty();
	})

	//修改学院
	$(".editFaculty_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、是，获取该行中的数据后执行 addFaculty(data)
		 * 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('FacultyListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择学院", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择学院进修信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addFaculty(data);
		}

		// var checkStatus = table.checkStatus('FacultyListTable');
		//    layer.msg(checkStatus.isAll ? '全选': '未全选')

	})

	//删除学院
	$(".delFaculty_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、否，则提示“请选择要删除的院系”。
		 * 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('FacultyListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择学院", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止意外删除，一次只能删除一个学院", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此学院？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var facultyId=data[0].facultyId;
				 $.getJSON("../../Faculty.do?method=delFaculty",{
					 facultyId
                 },function(result){
                	 if (result) {
  	 					top.layer.msg("删除 学院成功");
  	 				}else {
  	 					top.layer.msg("删除学院失败");
  	 				}
  	            	 tableIns.reload();
  	            	 layer.close(index);
                 })
			});
		}
	})
})