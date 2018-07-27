layui.config({
	base : "../../js/"
}).extend({
	"address" : "address"
})
layui
		.use(
				[ 'form', 'jquery', 'laydate', 'address' ],
				function() {

					var laydate = layui.laydate;
					var address = layui.address;

					var form = layui.form;
					var $ = layui.jquery;
					address.provinces();

					// 常规用法
					laydate.render({
						elem : '#stuBirthday'
					});

					// 自定义验证规则
					form
							.verify({
								stuNo : [ /^\d{10}$/, '请输入10位学号' ],
								stuName : [ /^[\u0391-\uFFE5]+$/,
										'请输入学生姓名,只允许汉字' ],
								personId : [
										/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/,
										'请输入18位身份证号码' ],
										
								nation: function(value) {
											if( 1<=value&&value<=57) {
											}else{
												return '请输入民族代码，具体请参考民族代码表';
											}
										},
										
										
//								nation : [ /^\d{1,2}$/, '请参考民族代码表' ],
								phone : [ /^1[3|4|5|7|8]\d{9}$/,
										'手机必须11位，只能是数字！' ],
								email : [
										/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/,
										'请输入正确的邮箱' ]
							});
					$(function() {
						var url = '../../Class.do?method=selectAll';
						$.getJSON(url, function(result) {
							console.log(result);
							var clazzs = result.data;
							console.log("StuInforSelect");
							console.log(StuInforSelect);
							var StuInforSelect = $("#StuInforSelect");
							console.log(StuInforSelect);
							$.each(clazzs, function(index, data) {
								console.log(data);
								var deptOption = $("<option value='"
										+ data.classId + "'>" + data.classId
										+ "</option>");
								console.log(deptOption);
								StuInforSelect.append(deptOption);
							});
							form.render();
						});
					});
					form
							.on(
									"submit(addLink)",
									function(data) {
										var data = {
											"stuNo" : $("#stuNo").val(),
											"classId" : $("#StuInforSelect")
													.val(),
											"stuName" : $("#stuName").val(),
											"stuBirthday" : $("#stuBirthday")
													.val(),
											"stuPwd" : $("#stuPwd").val(),
											"personId" : $("#personId").val(),
											"stuHome" : data.field.area,
											"stuSex" : data.field.sex,
											"stuNation" : $("#stuNation").val(),
											"stuOrigin" : data.field.area,
											"stuTel" : $("#stuTel").val(),
											"stuEmail" : $("#stuEmail").val(),
										};
										if ($("#operation").val() == "添加") {
											var url = "../../StuInfor.do?method=addStuInfor";
										} else {
											var url = "../../StuInfor.do?method=updateStuInfor";
										}
										$
												.ajax({
													url : url,
													type : "post",
													data : data,
													dataType : "json",
													success : function(result) {
														if (result) {
															top.layer
																	.msg($(
																			"#operation")
																			.val()
																			+ "操作成功");
														} else {
															top.layer
																	.msg($(
																			"#operation")
																			.val()
																			+ "操作失败");
														}
														// 当你在iframe页面关闭自身时
														var index = parent.layer
																.getFrameIndex(window.name); // 先得到当前iframe层的索引
														// 刷新父页面
														window.parent.location
																.reload();
														parent.layer
																.close(index); // 再执行关闭
													},
													error : function(result) {
														top.layer
																.msg($(
																		"#operation")
																		.val()
																		+ "请求异常，等会儿再试？");
														// 当你在iframe页面关闭自身时
														var index = parent.layer
																.getFrameIndex(window.name); // 先得到当前iframe层的索引
														// 刷新父页面
														window.parent.location
																.reload();
														parent.layer
																.close(index); // 再执行关闭
													}
												});
										return false;
									})

				})