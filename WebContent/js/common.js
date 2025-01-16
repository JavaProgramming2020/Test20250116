function on_the_progress() {
	alert("준비중입니다");
}
function logout() {
	alert("로그아웃되었습니다.");
	location.href = "화면구현_main.html";
}
function getUrlParams() {     
    var params = {};  
    
    window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, 
    	function(str, key, value) { 
        	params[key] = value; 
        }
    );     
    
    return params; 
}
