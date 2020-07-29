#version 400 core

in vec2 passTextureCoords;

out vec4 out_Color;

uniform sampler2D sampler;

void main(void)
{
	vec4 color = texture(sampler, passTextureCoords);

	if(color.a < 0.5)
	{
		discard;
	}

	out_Color = vec4(0,1,0,1);
}
