layui.use(['form', 'layer', 'table', 'upload'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		upload = layui.upload,
		table = layui.table;

	//测试记录列表
	var tableIns = table.render({
		elem: '#RecordList', //指定原始表格元素选择器（推荐id选择器）
		url: '../../Record.do?method=getRecordAll',
		id: "RecordListTable",
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'stuNo',
					title: '学号',
					align: "center",
					sort: true
				},
				{
					field: 'userName',
					title: '学生姓名',
					align: "center"
				},
				{
					field: 'year',
					title: '年份',
					align: "center"
				},
				{
					field: 'height',
					title: '身高',
					align: "center"
				},
				{
					field: 'weight',
					title: '体重',
					align: "center"
				},
				{
					field: 'vitalcapacity',
					title: '肺活量',
					align: "center"
				},
				{
					field: 'beginToBend',
					title: '坐位体前屈',
					width: 120,
					align: "center"
				},
				{
					field: 'standingBroadJump',
					title: '立定跳远',
					align: "center"
				},
				{
					field: 'situp',
					title: '躯体运动',
					align: "center"
				},
				{
					field: 'fivetyRun',
					title: '50米跑',
					align: "center"
				},
				{
					field: 'thousandRun',
					title: '耐力跑',
					align: "center"
				}
			]
		],
		page: true,
		height: 430,
		limit: 10,
		limits: [5, 10, 15, 20, 25]
	});
	 //搜索
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("MajorListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });
	upload.render({ //允许上传的文件后缀
		elem: '.uploadRecord',
		url: '../../Record.do?method=uploadRecordAll',
		accept: 'file' //普通文件
			,
		exts: 'xlsx|xls' //只允许上传xlsx文件
			,
		done: function(res) {
			if (res.code == 0) {
				alert("批量导入成功");
			}else{
				alert("部分参数上传成功，详情看日志！");
			}
		},error : function() {
			alert("请求失败");
		}
	});

	//添加专业
	function addRecord(edit) {

		/* 弹出添加界面 */
		var index = layui.layer.open({
			title: "添加体测记录",
			type: 2,
			area: ['620px', '520px'],
			content: "../../page/record/RecordAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					//layer.alert(edit[0].facultyname);
					// body.contents().find("#facultyname").val(edit[0].facultyname);
					body.find("#operation").val("editRecord");
					body.find("#stuNo").val(edit[0].stuNo);
					body.find("#stuName").val(edit[0].userName);
					body.find("#year").val(edit[0].year);
					body.find("#height").val(edit[0].height);
					body.find("#weight").val(edit[0].weight);
					body.find("#vitalCapacity").val(edit[0].vitalcapacity);
					body.find("#beginToBend").val(edit[0].beginToBend);
					body.find("#standingBroadJump").val(edit[0].standingBroadJump);
					body.find("#sitUp").val(edit[0].situp);
					body.find("#fivetyRun").val(edit[0].fivetyRun);
					body.find("#thousandRun").val(edit[0].thousandRun);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回专业列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)

			}
		})
		/* 渲染表单 */
		form.render();
		//		var index = layui.layer.open({
		//			title: "添加专业",
		//			type: 2,
		//			content: "MajorAdd.html",
		//			success: function(layero, index) {
		//				var body = layui.layer.getChildFrame('body', index);
		//				if(edit) {
		//					//layer.alert(edit[0].facultyname);
		//					// body.contents().find("#facultyname").val(edit[0].facultyname);
		//					body.find("#majorName").val(edit[0].majorName);
		//					body.find("#majorId").attr("value", edit[0].majorId);
		//					form.render();
		//				}
		//				setTimeout(function() {
		//					layui.layer.tips('点击此处返回专业列表', '.layui-layer-setwin .layui-layer-close', {
		//						tips: 3
		//					});
		//				}, 500)
		//			}
		//		})

	}
	//添加专业
	$(".addRecord_btn").click(function() {
		addRecord();
	})

	//修改专业
	$(".editRecord_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、是，获取该行中的数据后执行 addFculty(data)
		 * 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('RecordListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择测试记录", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择一条测试记录信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addRecord(data);
		}

		// var checkStatus = table.checkStatus('RecordListTable');
		//    layer.msg(checkStatus.isAll ? '全选': '未全选')

	})

	//删除专业
	$(".delRecord_btn").click(function() {
		/*
		 * 实现逻辑
		 * 1、判断是否选择某一行，
		 * 2、否，则提示“请选择要删除的院系”。
		 * 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('RecordListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择一条测试记录", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止意外删除，一次只能删除一条测试记录", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此测试记录？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var recorddata = {
						"stuNo":data[0].stuNo,
						"year":data[0].year
					};
				//layer.alert(JSON.stringify(data));
				$.ajax({
					url : "../../Record.do?method=delRecord",
					type : "post",
					data : recorddata,
					dataType : "json",
					success : function(result) {
						 if (result) {
		  						alert("删除体测记录成功");
		  					}else{
		  						alert("删除体测记录失败");
		  					}
						 tableIns.reload();
		                  layer.close(index);
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
			});
		}
	})
})