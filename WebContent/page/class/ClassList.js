layui.use(['form', 'layer', 'table','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		table = layui.table;

	//行政班级列表
	var tableIns = table.render({
		elem: '#ClassList', //指定原始表格元素选择器（推荐id选择器）
		url: '../../Class.do?method=getClassAll',
		id: "classListTable",
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'classId',
					title: '班级编号',
					align: "center",
					sort: true
				},
				{
					field: 'className',
					title: '班级名称',
					align: "center"
				},
				{
					field: 'testerName',
					title: '测试员姓名',
					align: "center"
				},
				{
					field: 'majorName',
					title: '专业名称',
					align: "center"
				},
				{
					field: 'classYear',
					title: '级',
					align: "center"
				}
			]
		]
	});

	//添加行政班级
	function addClass(edit) {
		var index = layui.layer.open({
			title: "添加行政班级",
			type: 2,
			area:['600px','450px'],
			content: "../../page/class/ClassAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					body.find("#operation").val("editClass");
					body.find("#className").val(edit[0].className);
					body.find("#classId").val( edit[0].classId).attr('readonly', true);;
					body.find("#testerSelect").val(edit[0].testerName);
					body.find("#majorSelect").val(edit[0].magorName);
					body.find("#classYear").val(edit[0].classYear);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回行政班级列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	//添加行政班级
	$(".addClass_btn").click(function() {
		addClass();
	})

	//修改行政班级
	$(".editClass_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、是，获取该行中的数据后执行 addFculty(data)
		 * 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('classListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择班级", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择行政班级进修信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addClass(data);
		}

		// var checkStatus = table.checkStatus('FcultyListTable');
		//    layer.msg(checkStatus.isAll ? '全选': '未全选')

	})

	//删除行政班级
	$(".delClass_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、否，则提示“请选择要删除的院系”。
		 * 2、是，获取行政班级编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('classListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择班级", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止恶意删除，一次只能删除一个行政班级", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此行政班级？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var classId=data[0].classId;
				//layer.alert(JSON.stringify(data));
				                 $.get("../../Class.do?method=delClass",{
				                	 classId
				                 },function(result){
				                	 if (result) {
					  						alert("删除班级成功");
					  					}else{
					  						alert("删除班级失败");
					  					} 
				                  tableIns.reload();
				                  layer.close(index);
				                 })
			});
		}
	})
})