var rid = '',
	deng = '';
var init = {
	onload:function(openid){

		// this.redpacket(openid);
		//this.redpaylist();
	},
	tikcash:function(openid){
      if(author == 1){
		_Ajax('/payment/cashback',{},'get',function(obj){
			if (obj.code == 'susseccful'){
				window.location.href= obj.url;
			}else{
				model(obj.msg);
			}
		})
      }else{
        $('#noauthor').show();
      }
	}
}
var time=0; //初始化监听时间
function model(text){
	$('#msgreback').show().find('.bg h4').html(text);
}


function _Ajax(url,data,method,success,error) {
	 $.ajax({  
        url:url,  
        dataType:'json',
        data:data,
        type:method,
        success:function(obj){
            success(obj);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            error();
        },
    }); 
}