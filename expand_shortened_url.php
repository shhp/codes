<?php
function expand_url($url){
    $knowurl = 'http://knowurl.com/y.php';
	$data = array('url' => $url);

	// use key 'http' even if you send the request to https://...
	$options = array('http' => array('method'  => 'POST','content' => http_build_query($data),'header'=>"Content-Type: application/x-www-form-urlencoded\r\n"));
	$context  = stream_context_create($options);
	$result = file_get_contents($knowurl, false, $context);

	//var_dump($result);
	preg_match("/http:(.+)<br\/>/",$result,$match);
	$originURL = str_replace("<br/>","",$match[0]);
	$originURL = preg_replace("/\?(.+)/","",$originURL);
	return $originURL;
}
