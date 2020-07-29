package Game.Render.SubRenderers;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;

import Game.Render.DisplayManager;
import Game.Render.Shaders.GuiShader;
import Game.tools.GameResourcesAndSettings;
import Game.tools.Maths;
import Gui.Gui;
import Gui.TextRendering.TextMeshData;

public class GuiRenderer {

	private GuiShader shader;
	
	public GuiRenderer()
	{
		shader = new GuiShader();
	}
	
	private void bindGui(Gui g)
	{
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		shader.loadTexture(g.getTexture());
		shader.loadTransMatrix(Maths.createTransMatrix(g.getPositionInNDC(), g.getScale()));
		
		shader.loadAspectRatio(g.getAspect());
		shader.isSelected(g.hasFocus());
	}
	
	private void prepare()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	public void renderGuis(List<Gui> guis)
	{
		prepare();
		
		shader.start();
		
		GL30.glBindVertexArray(GameResourcesAndSettings.quadVAO);
		
		for(Gui g : guis)
		{
			bindGui(g);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
		}
		
		shader.stop();
	}
	
}
