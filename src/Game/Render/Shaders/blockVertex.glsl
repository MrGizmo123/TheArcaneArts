#version 400 core

in vec3 pos;
in vec3 normal;
in vec2 textureCoords;

in vec3 blockPos;
in float blockType;

out vec2 pass_texCoords;
out vec3 diffuse;

uniform vec3 sunDir;
uniform vec3 sunColor;

uniform mat4 projMatrix;
uniform mat4 viewMatrix;

uniform float atlasSize;

void main(void){

	vec3 realPos = pos + vec3(blockPos.x, blockPos.y - 1, blockPos.z);
	gl_Position = projMatrix * viewMatrix * vec4(realPos, 1);

	vec3 unitNormal = normalize(normal);
	vec3 unitLight = normalize(-sunDir);

	float nDot1 = dot(unitNormal,unitLight);
	float brightness = max(nDot1,0.4);
	diffuse = brightness * sunColor * vec3(exp(blockPos.y / 20));

	float column = blockType - (atlasSize * floor(blockType / atlasSize));
	float offsetX = column / atlasSize;

	float row = floor(blockType / atlasSize);
	float offsetY = row / atlasSize;

	vec2 texCoords = (textureCoords / atlasSize);

	pass_texCoords = vec2(texCoords.x + offsetX,texCoords.y + offsetY);

}
