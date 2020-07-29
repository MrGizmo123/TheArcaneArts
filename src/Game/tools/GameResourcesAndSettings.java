package Game.tools;

import org.lwjgl.opengl.Display;

import Game.Game;
import Game.Models.Model;
import Game.Models.ModelData;
import Game.Models.OBJLoader;
import Gui.TextRendering.Font;
import Gui.TextRendering.FontLoader;

public class GameResourcesAndSettings {
	
	//constants 
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	public static int BLOCK_ATLAS_SIZE = 2;
	
	public static int BONEFIRE_ANIMATION_SIZE = 5;
	public static int BONEFIRE_ANIMATION_SPEED = 2;
	
	//gui constants
	
	private static final float[] quadPositions = {  -1f, 1f, 0.5f,
													-1f, -1f, 0.5f,
												    1f, -1f, 0.5f,
												    1f, -1f, 0.5f,
												    1f, 1f, 0.5f,
												    -1f, 1f, 0.5f
												 };
	
	private static final float[] quadTextureCoords = { 1, 0,
													   0, 0,
													   0, 1,
													   0, 1,
													   1, 1,
													   1, 0
	 												 };
	
	public static final int quadVAO = Game.loader.loadToVAO3D(quadPositions, quadTextureCoords);
	
	
	//Gui textures
	public static final int GUI_DARK_GREY = Game.loader.loadTexture("grey_gui_bg");
	public static final int GUI_LIGHT_GREY = Game.loader.loadTexture("grey_ligh_grey");
	public static final int GUI_GREY = Game.loader.loadTexture("grey_button_bg");
	public static final int GUI_TRANSPARENT = Game.loader.loadTexture("transparent");
	public static final int GUI_LIGHT_BLUE = Game.loader.loadTexture("lightBlue_button_bg");
	public static final int GUI_PINK = Game.loader.loadTexture("pink_button_bg");
	public static final int GUI_GREEN = Game.loader.loadTexture("green_button_bg");
	
	public static final Font GAME_FONT = FontLoader.loadFont("C:\\Users\\HrushikeshP\\ArcaneArts\\TheArcaneArts\\res\\font.fnt", "font");
	public static final float LINE_HEIGHT = GAME_FONT.getMaxHeight() / Display.getHeight();
	
	//ANIMATIONS
	
	public static int MAX_WEIGHTS = 3;
	
	//textures
	public static int SAND_BLOCK = Game.loader.loadTexture("sand_block");
	public static int BLOCK_TEXTURE_ATLAS = Game.loader.loadTexture("block_atlas");
	
	public static int[] BONEFIRE_BLOCK_TEXTURES = {Game.loader.loadTexture("bonefire_1"), Game.loader.loadTexture("bonefire_2"), Game.loader.loadTexture("bonefire_3"), Game.loader.loadTexture("bonefire_4"), Game.loader.loadTexture("bonefire_5")};
	
	//models
	public static ModelData block_model_data = OBJLoader.loadOBJ("block");
	public static Model BLOCK_MODEL = Game.loader.loadToVAO(block_model_data.getIndices(), block_model_data.getVertices(), block_model_data.getTextureCoords(), block_model_data.getNormals());

	public static ModelData bonefire_model_data = OBJLoader.loadOBJ("boneFire");
	public static Model BONEFIRE_MODEL = Game.loader.loadToVAO(bonefire_model_data.getIndices(), bonefire_model_data.getVertices(), bonefire_model_data.getTextureCoords(), bonefire_model_data.getNormals());
	
}
