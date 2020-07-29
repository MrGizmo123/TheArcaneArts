package Game.tools;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.xml.sax.SAXParseException;

import Game.Entities.Camera;

public class Maths {

	public static Matrix4f createTransMatrix(Vector3f trans, float rx,float ry, float rz, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(trans, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0),matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0),matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1),matrix, matrix);
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrix,matrix);
		return matrix;
	}
	
	public static Matrix4f createTransMatrix(Vector2f trans,Vector2f scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(trans, matrix, matrix);
		Matrix4f.scale(new Vector3f(scale.x,scale.y,1),matrix, matrix);
		return matrix;
	}
	
	public static Vector2f getTextPositionFromNDC(Vector2f ndc){
		Vector2f result = new Vector2f(ndc.x + 1, ndc.y - 1);
		
		return result;
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.pitch), new Vector3f(1, 0, 0), viewMatrix,viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.roll), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.pos;
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        
        return viewMatrix;
	}
	
	public static Matrix4f createProjectionMatrix(float FAR,float NEAR){
		Matrix4f projectionMatrix = new Matrix4f();
		projectionMatrix.setIdentity();
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		
		float size = 20;
		
		float r = size;
		float t = size / aspectRatio;
		float l = -size;
		float b = -size / aspectRatio;
		
		projectionMatrix.m00 = 2 / (r - l);
		projectionMatrix.m11 = 2 / (t - b);
		projectionMatrix.m22 = -2 / (FAR - NEAR);
		projectionMatrix.m32 = - ((FAR + NEAR) / (FAR - NEAR));
		projectionMatrix.m31 = -((t + b) / (t - b));
		projectionMatrix.m30 = -((r + l) / (r - l));
		
		
        return projectionMatrix;
    }
	
	public static Matrix4f createZoomMatrix(float zoom){
		Matrix4f matrix = new Matrix4f();
		Matrix4f.scale(new Vector3f(zoom,zoom,zoom), matrix, matrix);
		return matrix;
	}
	
	public static boolean mouseOnButton(Vector2f mouseViewportCoords,Vector2f buttonPos,Vector2f buttonScale){
		float ndcWidth = (buttonScale.x * 2);
		float ndcHeight = (buttonScale.y * 2);
		Vector2f mouseNDC = viewportToNDC(mouseViewportCoords);
		float x_bound1 = buttonPos.x - (ndcWidth / 2);
		float x_bound2 = buttonPos.x + (ndcWidth / 2);
		float y_bound1 = buttonPos.y - (ndcHeight / 2);
		float y_bound2 = buttonPos.y + (ndcHeight / 2);
		if(mouseNDC.x > x_bound1 && mouseNDC.x < x_bound2 && mouseNDC.y > y_bound1 && mouseNDC.y < y_bound2){
			return true;
		}
		return false;
	}
	
	public static Vector2f viewportToNDC(Vector2f viewport){
		float NDCx = ((viewport.x / Display.getWidth()) * 2) - 1;
		float NDCy = ((viewport.y / Display.getHeight()) * 2) - 1;
		return new Vector2f(NDCx,NDCy);
	}
	
	public static float distance(Vector2f a,Vector2f b){
		 return (float) Math.sqrt(((b.x - a.x) * (b.x - a.x)) + ((b.y - a.y) * (b.y - a.y)));
	}
	
}
