package Game.World.WorldGen;

import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import Game.tools.Maths;

public class HeightsGenerator {

	private static final float AMPTITUDE = 5f;
	
	private static Random random = new Random();
	private static int seed;
	
	public HeightsGenerator(){
		seed = random.nextInt();
	}
	
	private static float generateBaseHeight(float x,float z){
		random.setSeed((long)(seed+(x*90844)+(z*28373)));
		float out = random.nextFloat();
		out = (out * 2f - 1f);
		return out;
	}
	
	private static float getInterPolatedNoise(float x,float z){
		int intx = (int)x;
		int intz = (int)z;
		float fracX = x - intx;
		float fracZ = z - intz;

		float v1 = generateHeight(intx, intz,1);
		float v2 = generateHeight(intx+1, intz,1);
		float v3 = generateHeight(intx, intz+1,1);
		float v4 = generateHeight(intx+1, intz+1,1);
		float i1 = interpolate(v1, v2, fracX);
		float i2 = interpolate(v3, v4, fracX);
		return interpolate(i1, i2, fracZ);
	}
	
	private static float interpolate(float a,float b,float blend){
		double theta = blend * Math.PI;
		float f = (float) ((1f-Math.cos(theta))*0.5);
		return a * (1f - f) + b * f;
	}
	
	public static int generateHeight(float x,float z){
		return (int)Math.round(((getInterPolatedNoise(x/12f, z/12f) * AMPTITUDE) + (getInterPolatedNoise(x/4f, z/4f) * AMPTITUDE/3f))) * 2;
	}
	
	private static float generateHeight(float x,float z,int a){
		float corners = (generateBaseHeight(x+1, z+1)+generateBaseHeight(x-1, z-1)+generateBaseHeight(x+1, z-1)+generateBaseHeight(x-1, z+1))/16f;
		float sides = (generateBaseHeight(x+1, z)+generateBaseHeight(x, z-1)+generateBaseHeight(x-1, z)+generateBaseHeight(x, z+1))/8f;
		float center = generateBaseHeight(x, z)/4f;
		return (center+sides+corners);
	}

}
