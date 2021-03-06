#version 400 core

in vec2 pass_texCoords;

out vec4 out_Color;

uniform sampler2D sampler;

void main(void)
{
	vec4 color = texture(sampler, pass_texCoords);

	if(color.a < 0.5)
	{
		discard;
	}

	out_Color = color;
}
