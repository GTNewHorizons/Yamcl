package eu.usrv.yamcore.client.dynamicgui.helper;

public interface IQuadDrawer
{
  
  void draw(int x, int y);
  
  void draw(int x, int y, int width, int height);
  
  void draw(int x, int y, int width, int height, float rotation);
  
  float getZLayer();
  
  IQuadDrawer setZLayer(float z);
  
  int getWidth();
  
  int getHeight();
  
  IQuadDrawer setWH(int width, int height);
  
}