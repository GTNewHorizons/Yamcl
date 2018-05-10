package eu.usrv.yamcore.client.dynamicgui.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Vector2f;

public class TexturedQuadDrawer implements IQuadDrawer
{
  static final float scale = 0.00390625F;
  public final float u0, v0, u1, v1, u2, v2, u3, v3;
  final Tessellator tessellator = Tessellator.instance;
  final TextureManager texMan = Minecraft.getMinecraft().getTextureManager();
  public int width, height;
  float zLevel = 0;
  ResourceLocation texture;
  private boolean hasWarned = false;
  
  public TexturedQuadDrawer(ResourceLocation texture, float uMin, float uMax, float vMin, float vMax)
  {
    u3 = u0 = uMin * scale;
    v1 = v0 = vMax * scale;
    u1 = u2 = uMax * scale;
    v2 = v3 = vMin * scale;
    this.texture = texture;
  }
  
  public TexturedQuadDrawer(ResourceLocation texture, float uMin, float uMax, float vMin, float vMax, int width, int height)
  {
    this(texture, uMin, uMax, vMin, vMax);
    this.width = width;
    this.height = height;
  }
  
  public TexturedQuadDrawer(ResourceLocation texture, Vector2f uv0, Vector2f uv1, Vector2f uv2, Vector2f uv3)
  {
    u0 = uv0.x;
    v0 = uv0.y;
    u1 = uv1.x;
    v1 = uv1.y;
    u2 = uv2.x;
    v2 = uv2.y;
    u3 = uv3.x;
    v3 = uv3.y;
    this.texture = texture;
  }
  
  @Override
  public void draw(int x, int y)
  {
    draw(x, y, width, height);
  }
  
  @Override
  public void draw(int x, int y, int drawWidth, int drawHeight)
  {
    if (texture == null && !hasWarned)
    {
      hasWarned = true;
    }
    texMan.bindTexture(texture);
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(x, y + drawHeight, zLevel, u0, v0);
    tessellator.addVertexWithUV(x + drawWidth, y + drawHeight, zLevel, u1, v1);
    tessellator.addVertexWithUV(x + drawWidth, y, zLevel, u2, v2);
    tessellator.addVertexWithUV(x, y, zLevel, u3, v3);
    tessellator.draw();
  }
  
  @Override
  public void draw(int x, int y, int drawWidth, int drawHeight, float rotation)
  {
    GL11.glPushMatrix();
    GL11.glRotatef(rotation, 0, 0, 1);
    draw(x, y, drawWidth, drawHeight);
    GL11.glPopMatrix();
  }
  
  @Override
  public float getZLayer()
  {
    return zLevel;
  }
  
  @Override
  public TexturedQuadDrawer setWH(int width, int height)
  {
    this.width = width;
    this.height = height;
    return this;
  }
  
  @Override
  public IQuadDrawer setZLayer(float z)
  {
    zLevel = z;
    return this;
  }
  
  @Override
  public int getWidth()
  {
    return width;
  }
  
  @Override
  public int getHeight()
  {
    return height;
  }
}
