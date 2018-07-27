layui.use(['form', 'layer', 'laydate', 'table','upload'], function() {
	 var upload = layui.upload;
	   
	  // 执行实例
	  var uploadInst = upload.render({
	    elem: '#uploadPE' // 绑定元素
	    ,url: '../../PEClass.do?method=upload' ,
					accept : 'file',
					done : function(res) {
						if (res.code == 0) {
							alert("批量导入成功");
						}else{
							alert("该表内容部分信息上传失败，导致所有失败");
						}
					},
					error : function() {
						alert("请求失败");
					}
				});
			
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laydate = layui.laydate,
		table = layui.table;

	// 学院列表
	var tableIns = table.render({
		elem: '#PEClassList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../PEClass.do?method=getPEClassAll',
		id: "PEclassListTable",
		page:true,
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'classId',
					title: '体育班级编号',
					align: "center",
					sort: true
				},
				{
					field: 'className',
					title: '体育班级名称',
					align: "center"
				},
				{
					field: 'testerName',
					title: '教师姓名',
					align: "center"
				},
				{
					field: 'classYear',
					title: '创建时间',
					align: "center"
				}
			]
		]
	});

	// 添加学院
	function addPEClass(edit) {
		var index = layui.layer.open({
			title: "添加体育班级",
			type: 2,
			content: "../../page/PEClass/PEClassAdd.html",
			area:['500px','360px'],
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					// layer.alert(edit[0].facultyname);
					// body.contents().find("#facultyname").val(edit[0].facultyname);
					body.find("#operation").val("editPEClass");
					body.find("#peclassId").val(edit[0].peclassId);
					body.find("#peclassName").val(edit[0].peclassName);
					body.find("#teacherSelect").val( edit[0].teacherName);
					body.find("#peclassTime").val(edit[0].peclassTime);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回体育班级列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	// 添加学院
	$(".addPEClass_btn").click(function() {
		addPEClass();
	})

	// 修改学院
	$(".editPEClass_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addFculty(data) 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('PEclassListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择班级", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择学院进修信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addPEClass(data);
		}

		// var checkStatus = table.checkStatus('PEclassListTable');
		// layer.msg(checkStatus.isAll ? '全选': '未全选')

	})

	// 删除学院
	$(".delPEClass_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、否，则提示“请选择要删除的院系”。 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('PEclassListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择班级", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止恶意删除，一次只能删除一个班级", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此班级？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var peclassId=data[0].peclassId; // 将需要删除的学院参数传入
				// layer.alert(JSON.stringify(data));
				                 $.get("../../PEClass.do?method=delPEClass",{
				                	 peclassId
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