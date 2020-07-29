#version 400 core

in vec2 pass_texCoords;
in vec3 diffuse;

out vec4 out_Color;

uniform sampler2D tex;

void main(void){

	out_Color = texture(tex, pass_texCoords) * vec4(diffuse, 1.0);

}
