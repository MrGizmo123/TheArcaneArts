#version 400 core

in vec2 passTextureCoords;

out vec4 out_Color;

uniform sampler2D sampler;
uniform float aspectRatio;
uniform bool isSelected;

const float r = 0.1f;

float dist(vec2 a, vec2 b)
{
	float a1 = a.x - b.x;
	float b1 = a.y - b.y;

	return sqrt((a1 * a1) + (b1 * b1));
}

void main(void){

	float x = passTextureCoords.x;
	float y = passTextureCoords.y * aspectRatio;

	/*if(isSelected)
	{
		bool x1 = x < r;
		bool x2 = x > 1 - r;
		bool y1 = y < r;
		bool y2 = y > aspectRatio - r;
	
		vec2 ref = vec2(0,0);
		if((x1 || x2) || (y1 || y2))
		{
			out_Color = vec4(1,1,1,1);
			return;
		}
	}*/

	vec4 color = texture(sampler, passTextureCoords);

	if(color.a < 0.5)
	{
		discard;
	}

	out_Color = color;

}
