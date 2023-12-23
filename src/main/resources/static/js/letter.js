$(function(){
	$("#sendBtn").click(send_letter);
	$(".close").click(delete_msg);
});

function send_letter() {
	$("#sendModal").modal("hide");
	//获取toName和content的值,.val()不是.var()
	var toName = $("#recipient-name").val();
	var content = $("#message-text").val();
	//异步发送
	$.post(
	    CONTEXT_PATH + "/letter/send",
	    //请不要忘记这里是key-value所以要用大括号
	    {"toName":toName,"content":content},
	    function(data) {
	        //转为JS对象
	        data = $.parseJSON(data);
	        if(data.code == 0) {
	            $("#hintBody").text("发送成功！");
	        }else{
	            $("#hintBody").text(data.msg);
	        }

	        $("#hintModal").modal("show");
            setTimeout(function(){
                $("#hintModal").modal("hide");
                //重载当前页面
                location.reload();
            }, 2000);
	    }
	);
}

function delete_msg() {
	// TODO 删除数据
	$(this).parents(".media").remove();
}