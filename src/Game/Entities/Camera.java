package Game.Entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import Game.Game;
import Game.World.GameTickUpdater;

public class Camera {

	private static final float DISTANCE_FROM_PLAYER = 50f;
	private static final float ANGULAR_DAMPING = 0.98f;
	private static final float PITCH_DAMPING = 0.98f;
	
	public float distanceFromCenter = DISTANCE_FROM_PLAYER;
	
	private float angleAroundCenter;
	private float angularVelocity;
	
	private float pitchVelocity;
	
	private Vector3f center;
	
	public Vector3f pos;
	public float pitch;
	public float yaw;
	public float roll;
	
	public Camera(Vector3f center){
		this.center = center;
		this.pos = new Vector3f(0,0,0);
		pitch = 45;
		angularVelocity = 0;
	}
	
	public void update(Vector3f center){
		//calculateZoom();
		this.center = center;
		calculatePitchAndAngleAroundCenter();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPos(horizontalDistance, verticalDistance);
	}
	
	private float calculateHorizontalDistance(){
		return (float) (distanceFromCenter * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance(){
		return (float) (distanceFromCenter * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateZoom(){
		float zoom = Mouse.getDWheel() * 0.1f;
		distanceFromCenter -= zoom;
		//pos.x = pos.x * distanceFromCenter;
		//pos.y = pos.y * distanceFromCenter;
		//pos.z = pos.z * distanceFromCenter;
	}
	
	private void calculateCameraPos(float hor,float ver){
		float theta = angleAroundCenter;
		float offsetX = (float) (hor * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (hor * Math.cos(Math.toRadians(theta)));
		pos.x = center.x - offsetX;
		pos.y = center.y + ver;
		pos.z = center.z - offsetZ;
		yaw = 180 - angleAroundCenter;
	}
	
	private void calculatePitchAndAngleAroundCenter(){
		
		if(Mouse.isButtonDown(0) && Game.gameInput){
			float dPitch = Mouse.getDY() * 0.5f;
			float dAngle = Mouse.getDX() * 1f;
			
			this.pitchVelocity -= dPitch;
			this.angularVelocity -= dAngle;
		}
		
		this.pitch += pitchVelocity * GameTickUpdater.getFrameTime();
		this.pitchVelocity *= PITCH_DAMPING;
		
		this.angleAroundCenter += angularVelocity * GameTickUpdater.getFrameTime();
		this.angularVelocity *= ANGULAR_DAMPING;
	}
	
}
