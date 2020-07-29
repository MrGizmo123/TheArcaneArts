#version 400 core

in vec3 pos;
in vec2 texCoords;
in vec3 normal;

out vec2 pass_texCoords;

uniform mat4 projMatrix;
uniform mat4 viewMatrix;
uniform mat4 transMatrix;

void main(void)
{
	gl_Position = projMatrix * viewMatrix * transMatrix * vec4(pos, 1);

	pass_texCoords = texCoords;
}
