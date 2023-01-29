package eu.usrv.yamcore.client.dynamicgui.helper;

public class NullQuadDrawer implements IQuadDrawer {

    @Override
    public void draw(int x, int y) {}

    @Override
    public void draw(int x, int y, int width, int height) {}

    @Override
    public void draw(int x, int y, int width, int height, float rotation) {}

    @Override
    public float getZLayer() {
        return 0;
    }

    @Override
    public IQuadDrawer setZLayer(float z) {
        return this;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public IQuadDrawer setWH(int width, int height) {
        return this;
    }
}
