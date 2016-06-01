package gallery;

/**
 * Abstract class to define image decorator.
 *
 * @author Aditee Nagle
 */
public abstract class ImagePanelDecorator extends ImagePanel {

    protected ImagePanel enclosedPanel;

    public ImageInfo getImageInfo() {
        return this.enclosedPanel.getImageInfo();
    }

    public void setImageInfo(ImageInfo info) {
        this.enclosedPanel.setImageInfo(info);
    }
}
