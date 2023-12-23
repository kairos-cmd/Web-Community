$(function(){
	$("#publishBtn").click(publish);
});

function publish() {
	$("#publishModal").modal("hide");
	//发送AJAX请求之前，将csrf令牌设置到请求的消息头中
//	var token = $("meta[name='_csrf']").attr("content");
//	var header = $("meta[name='_csrf_header']").attr("content");
//	$(document).ajaxSend(function(e, xhr, options){
//        xhr.setRequestHeader(header, token);
//	});

    //获取标题和内容
    var title = $("#recipient-name").val();
    var content = $("#message-text").val();
    //发送异步的请求(POST)
    $.post(
        CONTEXT_PATH + "/discuss/add",
        //需要键值对作为参数，所以是大括号不是小括号
        {"title":title,"content":content},
        function(data){
            data = $.parseJSON(data);
            //在提示框中显示返回的消息
            $("#hintBody").text(data.msg);
            //显示提示框，两秒后自动隐藏提示框
            $("#hintModal").modal("show");
            setTimeout(function(){
            	$("#hintModal").modal("hide");
            	//刷新页面
            	if(data.code == 0){
            	    window.location.reload();
            	}
            }, 2000);
        }
    );

}