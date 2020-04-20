package com.jamc.springboot.backend.apirest.auth;

public class JwtConfig {
	
	//FIRMA DE CREDENCIAL TIPO RSA

	public static final String LLAVE_SECRETA = "some.secret.key.12345";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEA0lRJC80x+5gbY6rZfcQpdnYqoMwvP6f/Ybw3/kZASEuA4Kax\r\n" + 
			"6wuOQN5m+6Lu5ZQxz1GupDp/yyAoWr2cDNcQnW3kuObjI5L8G05oyn3kUho8ZJ1l\r\n" + 
			"4DWRexVlX2COXkr1Ah/VX+kHcMZ+Ky95I7ezoPRDl+bPxYTpfGlxa9kl2f0DZMB9\r\n" + 
			"DFCdql4jwJSqYxLEmngzXtCZiKdpP4zscalfqVwEyGQizrGV1HgSt7escklK001n\r\n" + 
			"wajsnQlW68iYERQkTQvVW6ip7mHQq6LrkqabIiDZjhMr9P6dzhpo8vqxfGl3r/RC\r\n" + 
			"578tb9AQrn3tksVM5cYhFe4GEp9IU9sDOiFKewIDAQABAoIBAHvMrp4qrmPnuR9m\r\n" + 
			"HdRAFSOsFBVHefwe91vgvJiHYyjqgDY7B/hQee/GuJke4zzwiIz+CCyUXkoCaJ5Y\r\n" + 
			"grSwFhCD8Z/ADywORSQRd+7QQQbueGPEnYVmInZvNpb4L3Ri7GnOsPpeIEoKG5Nx\r\n" + 
			"wvz6ENoJTIvpiJw7Sm83ZVJUo0n16HXi3rV2NtwuuBNxAsGPQBfj8i/h/YO3i3s/\r\n" + 
			"1JcJ0L8Y7Wt4dQwvRHjEwY/vq6AVGfNXqRiZWliITXFn8oZnWdWgzdDH/S1TDReL\r\n" + 
			"Cvobej5q+fJ5uPdTbfPnMfRSQzEQOw2y+UgN88S50y1cDUaLbUi5X2cnMII1uz+E\r\n" + 
			"yypt2tkCgYEA9ETkMIEibvhV8j+23N4BRBUG+3NZxL29aTPCKbHW0y7odfd0VNB1\r\n" + 
			"kdST0cdO/bn08Ns3w6gFBEBR7uimwywoIEamfbFf2rVCgjxbhMkPxHd4ncmpjcfn\r\n" + 
			"ndXEnGFsE19s/76nKIxsBgSSvRaicHZ04kWVmubcT4hJA1YBEAzbWdcCgYEA3G4g\r\n" + 
			"2WthEX/KWn0mQgnPX1TiH5XM0RQDjJEpgpANAPfQyh+LxTqQZmd6X72jhG2BchpR\r\n" + 
			"WkZd5M5JsXCGPAkBRvktsyGP595k7SQKeBS6/BwqQisbVOqVaJq1IChUG4aznPRS\r\n" + 
			"ES9dFU9sIJI3A5f/xZcWLvAeaSgz3rCCnd7sZ/0CgYA7r/HKYGOXlhr6PNNkGONa\r\n" + 
			"TYENRFx1FKAJ6tnQ8mG57MZkvPiyGNYBsl4Ebp1ApoXRrN9tccr4BPeAV10YZ9vO\r\n" + 
			"NzVLlWIEwTHlPPZn9fFiYocYl0RVHrnb+XY3eB6+hRzPSTjzUYy707Y/eh5rOKbK\r\n" + 
			"+5HY7fx7eqPMquFLmLiotQKBgQC5zbKu0g5uO7OL9vXqsG42Q0yfpxuQGTisoifF\r\n" + 
			"QDtV144M8e5U/3p8dzj1vto4ZD26umhcmffHSANiKCFeNZD4DE3zMpUks5eLNNgG\r\n" + 
			"RCMhI3STddaCC4KJO+70/kl3MmHwKRmMFsp1jg7snZL12ofkO+idBBX+Ob8PNOXE\r\n" + 
			"wQWw2QKBgQDyEnuYQtQ4We3bD0Rh5xgKpYzllm6UYwI1S0WyOPd9EzyY4hpNjqyS\r\n" + 
			"7ue7RGtFPIkeCt4xn6V6VCE0t4Khyle7gInqa/cr6vAFxja+/xOtTRgG3jO5s47N\r\n" + 
			"zc3AX+k/Z7mQTJgGaIoI+1vLcr00+tAlrtADLhj5aW8WBeE03BWOpg==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0lRJC80x+5gbY6rZfcQp\r\n" + 
			"dnYqoMwvP6f/Ybw3/kZASEuA4Kax6wuOQN5m+6Lu5ZQxz1GupDp/yyAoWr2cDNcQ\r\n" + 
			"nW3kuObjI5L8G05oyn3kUho8ZJ1l4DWRexVlX2COXkr1Ah/VX+kHcMZ+Ky95I7ez\r\n" + 
			"oPRDl+bPxYTpfGlxa9kl2f0DZMB9DFCdql4jwJSqYxLEmngzXtCZiKdpP4zscalf\r\n" + 
			"qVwEyGQizrGV1HgSt7escklK001nwajsnQlW68iYERQkTQvVW6ip7mHQq6Lrkqab\r\n" + 
			"IiDZjhMr9P6dzhpo8vqxfGl3r/RC578tb9AQrn3tksVM5cYhFe4GEp9IU9sDOiFK\r\n" + 
			"ewIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
