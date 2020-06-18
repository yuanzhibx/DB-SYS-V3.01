(function(){
	var jsFramework=function(){}
	//动态为jsFramework添加一个getJSON函数
	//prototype 动态添加函数时使用
	jsFramework.prototype.getJSON=function(url,params,callback){
		  //1.构建XHR对象
		  var xhr=new XMLHttpRequest();
		  //2.注册事件监听
		  xhr.onreadystatechange=function(){
				//readyState=4表示服务端响应结束
				//status=200表示服务端响应ok
				if(xhr.readyState==4&&xhr.status==200){
					var jsonObj=JSON.parse(xhr.responseText);
					callback(jsonObj);
				}
		  }//callback
		//3.建立连接
		 let getUrl=url+"?"+params;
		 xhr.open("GET",getUrl,true);
		 //4.发送请求
		 xhr.send(null);//get请求send方法不传数据
	}
	var obj=new jsFramework();
	//将局部变量赋值一个全局变量
	window.$=obj;
})()

