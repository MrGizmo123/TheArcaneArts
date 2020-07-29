#version 400 core

in vec3 pos;
in vec2 texCoords;

out vec2 passTextureCoords;

uniform mat4 transMatrix;

void main(void){

	gl_Position = transMatrix * vec4(pos.x,pos.yz, 1);

	passTextureCoords = texCoords;

}
