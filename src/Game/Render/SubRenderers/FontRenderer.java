package Game.Render.SubRenderers;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;

import Game.Render.Shaders.FontShader;
import Game.tools.Maths;
import Gui.TextRendering.Font;
import Gui.TextRendering.Text;

public class FontRenderer {

	private FontShader shader;
	
	public FontRenderer()
	{
		shader = new FontShader();
	}
	
	private void prepare()
	{
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	private void bindText(Text t)
	{
		GL30.glBindVertexArray(t.getMesh().getVaoId());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		shader.loadTransMatrix(Maths.createTransMatrix(Maths.getTextPositionFromNDC(t.getPosNDC()), new Vector2f(1,1)));
	}
	
	public void renderTexts(List<Text> texts)
	{
		prepare();
		
		shader.start();
		
		for(Text t : texts)
		{
			shader.loadTexture(t.getFont().getGlyphTexture());
			bindText(t);
				
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, t.getMesh().getVertexCount());
		}
		
		shader.stop();
	}
	
}
