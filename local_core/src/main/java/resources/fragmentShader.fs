#version 330

in  vec2 outTexCoord;
out vec4 fragColor;

uniform sampler2D textureSampler;

void main()
{
	fragColor = texture(textureSampler, outTexCoord);
}



// fragColor = vec4(gl_FragCoord.y, gl_FragCoord.y / 720, gl_FragCoord.x / 720, 1.0); - gradient

	//float norm = gl_FragCoord.y / 720;
	
	//if ((gl_FragCoord.y / 720) > 0.5) {
		//fragColor = vec4(0.0, 0.0, 1.0, 1.0);
		//}
	//else {
		//fragColor = vec4(1.0, 1.0, 0.0, 1.0);
		//}
		
	//if (norm > 0.6) {
		//fragColor = vec4(1.0, 1.0, 1.0, 1.0);
	//}
	
	//else if ((norm < 0.7) && (norm > 0.3)) {
	 	//fragColor = vec4(0.0, 0.0, 1.0, 1.0);
	 	//}
	//else {
		//fragColor = vec4(1.0, 0.0, 0.0, 1.0);
	//}