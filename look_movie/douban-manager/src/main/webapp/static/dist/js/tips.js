$(function(){
	var tips = function(){
		$.post("/reply/manage", function(res){
			if(res.state == "success"){
				$("#unreadcount").text(res.obj);
				layer.tips('<a href="/reply/manage">有' + res.obj + '条新评论需要您审核</a>', '#unreadcount', {
					tips : [3, '#e4e4e4' ]
				});
			}
		});
	};
	
	setInterval(function(){
		tips();
	}, 30 * 1000);
	
	tips();
})