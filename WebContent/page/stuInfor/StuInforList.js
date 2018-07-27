layui.use(['form', 'layer', 'laydate', 'table', 'jquery','upload'], function() {
	
	 var upload = layui.upload;
	   
	  // 执行实例
	  upload.render({ //允许上传的文件后缀
			elem: '#uploadStudent',
			url: '../../StuInfor.do?method=uploadStudent',
			accept: 'file' //普通文件
				,
			exts: 'xlsx|xls' //只允许上传xlsx文件
				,
			done: function(res) {
				if (res.code == 0) {
					alert("批量导入成功");
				}else{
					alert("该表内容部分信息上传失败，导致所有失败");
				}
			},error : function() {
				alert("请求失败");
			}
		});
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		table = layui.table;

	// 学生列表
	var tableIns = table.render({
		elem: '#StuInforList', // 指定原始表格元素选择器（推荐id选择器）
		url: '../../StuInfor.do?method=getStuInforAll',
		id: "StuInforListTable",
		page:true,
		cols: [
			[
				{
					type: "checkbox",
					fixed: "left",
				},
				{
					field: 'userId',
					title: '编号',
					align: "center",
					sort: true
				},
				{
					field: 'classId',
					title: '班级',
					align: "center"
				},
				{
					field: 'userName',
					title: '姓名',
					align: "center",
					sort: true
				},
				{
					field: 'stuBirthday',
					title: '出生日期',
					align: "center"
				},
				{
					field: 'personId',
					title: '身份证号',
					align: "center"
				},
				{
					field: 'stuHome',
					title: '籍贯',
					align: "center",
					sort: true
				},
				{
					field: 'stuSex',
					title: '性别',
					align: "center",
					templet: '#sexTpl'
				},
				{
					field: 'stuNation',
					title: '民族',
					align: "center",
					sort: true
				},
				{
					field: 'stuOrigin',
					title: '生源地',
					align: "center"
				},
				{
					field: 'userTel',
					title: '电话',
					align: "center",
					sort: true
				},
				{
					field: 'userEmail',
					title: '邮箱',
					align: "center"
				},
				
			]
		]
	});
	

	// 添加学生
	function addStuInfor(edit) {
		var index = layui.layer.open({
			title: "学生信息维护",
			type: 2,
			area:['600px','500px'],
			content: "../../page/stuInfor/StuInforAdd.html",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					body.find("#operation").val("修改");
					body.find("#stuNo").val( edit[0].userId).attr('readonly', true);
					body.find("#classId").val( edit[0].classId);
					body.find("#stuName").val( edit[0].userName);
					body.find("#stuBirthday").val( edit[0].stuBirthday);
					body.find("#stuPwd").val(edit[0].userPwd).attr("disabled", true);
					body.find("#personId").val( edit[0].personId);
					body.find("#stuHome").val( edit[0].stuHome);
					body.find("#stuSex[value='" +  edit[0].stuSex + "']").prop("checked", "checked");
					body.find("#stuNation").val( edit[0].stuNation);
					body.find("#stuOrigin").val( edit[0].stuOrigin);
					body.find("#stuTel").val( edit[0].userTel);
					body.find("#hiddenClassId").val(edit[0].classId);
					body.find("#stuEmail").val( edit[0].userEmail);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回学生信息列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			}
		})

	}
	// 添加学生
	$(".addStuInfor_btn").click(function() {
		addStuInfor();
	})

	// 修改学生
	$(".editStuInfor_btn").click(function() {
		/*
		 * 实现逻辑 1、判断是否选择某一行， 2、是，获取该行中的数据后执行 addFculty(data)
		 * 3、否，则提示“请选择要修改的院系”。StuInforListTable
		 */
		var checkStatus = table.checkStatus('StuInforListTable'),
			data = checkStatus.data;
		if(data.length < 1) {
			layer.alert("请选择学生", {
				icon: 5
			});
		} else if(data.length > 1) {
			layer.alert("一次只能选择学生进修信息修改", {
				icon: 5
			});
		} else {
			// layer.alert(JSON.stringify(data));
			addStuInfor(data);
		}
	})

})