#version 120

uniform sampler2D sampler;

varying vec2 sprite_coords;

void main() {
	gl_FragColor = texture2D(sampler, sprite_coords);
}