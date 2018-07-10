$(function(){
	var loadNotify = function(){
		$.post("/notify").done(function(res){
			if(res > 0){
				$("#unreadcount").text(res);
				 layer.tips('<a href="/commentary">有'+ res +'条新评论需要您审核</a>', '#unreadcount', {
			         tips: [3, '#e4e4e4']
			        });
			       
			}
		}).fail(function(){
			layer.msg("系统异常");
		});
	}
	
	setInterval(function(){
		loadNotify();
	},3*60*1000);
	
	loadNotify();
	
})