//获取系统时间
var newDate = '';
getLangDate();
//值小于10时，在前面补0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //表示当前系统时间的Date对象
    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
    var month = dateObj.getMonth()+1; //当前系统时间的月份值
    var date = dateObj.getDate(); //当前系统时间的月份中的日
    var day = dateObj.getDay(); //当前系统时间中的星期值
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    var hour = dateObj.getHours(); //当前系统时间的小时值
    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = timeValue+"好！ 欢迎使用体质健康数据管理系统。当前时间为： "+newDate+"　"+week;
    setTimeout("getLangDate()",1000);
}

layui.use(['form','element','layer','jquery','table', 'upload'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element,
        upload = layui.upload,
		table = layui.table,
        $ = layui.jquery;
        
    //系统基本参数
    
       $(function(){
    	   $.ajax({
               url : "../json/systemParameter.json",
               type : "get",
               dataType : "json",
               success : function(data){
                   fillParameter(data);
               }
           })
       });
    //填充数据方法
    function fillParameter(data){
        //判断字段数据是否存在
        function nullData(data){
            if(data == '' || data == "undefined"){
                return "未定义";
            }else{
                return data;
            }
        }
        $(".version").text(nullData(data.version));      //当前版本
        $(".author").text(nullData(data.author));        //开发作者
        $(".description").text(nullData(data.description));    //网站说明
        $(".server").text(nullData(data.server));        //服务器环境
        $(".dataBase").text(nullData(data.dataBase));    //数据库版本
    }
    
    
    $(function() {
		var url = '../json/Class.json';
		$.get(url, function(result) {
			console.log(result);
			var Class = result.data;
			var ClassSelect = $(".classId");
			console.log(ClassSelect);
			$.each(Class, function(index, data) {
				console.log(data);
				var deptOption = $("<option value='" + data.classId + "'>" + data.className + "</option>");
				console.log(deptOption);
				ClassSelect.append(deptOption);
			});
			form.render();
		});
	});
		
    
    
    
    //开始测试按钮按钮
    form.on("submit(StartTest)",function(data){
       layer.alert(JSON.stringify(data.field));
       
       /* 弹出添加界面 */
		var index = layui.layer.open({
			title: "添加专业",
			type: 2,
			area: ['500px', '260px'],
			content: "../page/record/startTest.jsp",
			success: function(layero, index) {
				
				 /* 异步获取班级学生数据填充到弹出框表格中 */
	$(function() {
		var url = '../json/StuInforList.json';
		$.get(url, function(result) {
			console.log(result);
		});
	});
				
				var body = layui.layer.getChildFrame('body', index);
				if(edit) {
					//layer.alert(edit[0].facultyname);
					// body.contents().find("#facultyname").val(edit[0].facultyname);
					body.find("#majorName").val(edit[0].majorName);
					body.find("#majorId").attr("value", edit[0].majorId);
					form.render();
				}
				setTimeout(function() {
					layui.layer.tips('点击此处返回专业列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)

			}
		})
		layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
        return false;
    })
})
