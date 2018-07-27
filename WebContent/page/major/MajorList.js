layui.use(['form', 'layer', 'table', 'upload'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		upload = layui.upload,
		table = layui.table;

	// 专业列表
	var tableIns = table.render({
		elem: '#MajorList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../Major.do?method=getMajorAll',
		id: "MajorListTable",
		cols: [
			[{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'majorId',
					title: '序号',
					align: "center",
					sort: true
				},
				{
					field: 'majorName',
					title: '专业名称',
					align: "center"
				},
				{
					field: 'facultyName',
					title: '学院名称',
					align: "center"
				}
			]
		],
		page: true,
		limit: 5,
		limits: [5, 10, 15, 20, 25]
	});
	
	
	 // 搜索
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("MajorListTable",{
                page: {
                    curr: 1 // 重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  // 搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });
	
	// 批量添加专业，只允许.xls
	upload.render({ // 允许上传的文件后缀
		elem: '.uploadMajor',
		url: '/upload/',
		accept: 'file' // 普通文件
			,
		exts: 'xls' // 只允许上传.xls文件
			,
		done: function(res) {
			console.log(res)
		}
	});

	// 添加专业
	function addMajor(edit) {

		/* 弹出添加界面 */
		var index = layui.layer.open({
			title: "专业信息维护",
			type: 2,
			area: ['500px', '260px'],
			content: "../../page/major/MajorAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					body.find("#operation").val("修改");
					body.find("#majorName").val(edit[0].majorName);
					body.find("#majorId").val(edit[0].majorId);
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

	}
	// 添加专业
	$(".addMajor_btn").click(function() {
		addMajor();
	})

	// 修改专业
	$(".editMajor_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addFculty(data) 3、否，则提示“请选择要修改的院系”。
		 */
		var checkStatus = table.checkStatus('MajorListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择专业", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择专业进修信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addMajor(data);
		}

		// var checkStatus = table.checkStatus('MajorListTable');
		// layer.msg(checkStatus.isAll ? '全选': '未全选')

	})

	// 删除专业
	$(".delMajor_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、否，则提示“请选择要删除的院系”。 2、是，获取学院编号后执行如下方法
		 * 
		 */
		var checkStatus = table.checkStatus('MajorListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择专业", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("为防止意外删除，一次只能删除一个专业", {
				icon: 5
			});
		} else {
			layer.confirm('确定删除此专业？', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				var majorId=data[0].majorId;
                 $.getJSON("../../Major.do?method=delMajor",{
                	 majorId
                 },function(result){
                	 if (result) {
  	 					top.layer.msg("删除专业成功");
  	 				}else {
  	 					top.layer.msg("删除专业失败");
  	 				}
  	            	 tableIns.reload();
  	            	 layer.close(index);

                 })
			});
		}
	})
})