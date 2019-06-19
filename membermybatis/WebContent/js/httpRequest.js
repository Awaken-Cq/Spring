function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e1){
			try{
				return new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e2){
				return null;
			}
		}
	}else if(window.XMLHttpRequest){
		//서버와 통신을 할 수 있는 js 객체
		return new XMLHttpRequest();
	}else{
		return null;
	}
}

var httpRequest = null;

function sendRequest(url, params, callback, method){
	httpRequest = getXMLHttpRequest();
	
	var httpMethod = method ? method : 'GET';
	if(httpMethod != 'GET' && httpMethod != 'POST'){
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params;
	var httpUrl = url;
	if(httpMethod == 'GET' && httpParams != null){
		httpUrl = httpUrl + "?" + httpParams;
	}
	
	//alert("method == " + httpMethod + "\turl == " + httpUrl + "\tparam == " + httpParams);
//	open의 이미 only 연결하라. true = 비동기, false = 동기
	httpRequest.open(httpMethod, httpUrl, true);
//	~할 것이다
//	반드시 해야하는 3줄↓ // onreadystatechange가 뭔지 확인하기 1,2,3은 데이터가 넘어오는 중인 상태 4는 넘어온 상태
//	동기는 모든게 완료되야 callback함수가 호출되지만 비동기방식은 onready~가 변화될때마다 호출. + 할것이다.
	httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback;
//	보내라
	//alert(httpMethod == 'POST' ? httpParams : null);
	httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}