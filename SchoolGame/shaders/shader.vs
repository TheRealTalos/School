#version 120

attribute vec3 vertices;
attribute vec2 sprites;

varying vec2 sprite_coords;

uniform mat4 projection;

void main() {
	sprite_coords = sprites;
	gl_Position = projection * vec4(vertices, 1);
}