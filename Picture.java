
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public final class Picture implements ActionListener {
    private BufferedImage image;
    private JFrame frame;
    private String filename;
    private boolean isOriginUpperLeft = true;
    private final int width;
    private final int height;

    public Picture(int var1, int var2) {
        if (var1 <= 0) {
            throw new IllegalArgumentException("width must be positive");
        } else if (var2 <= 0) {
            throw new IllegalArgumentException("height must be positive");
        } else {
            this.width = var1;
            this.height = var2;
            this.image = new BufferedImage(var1, var2, 1);
        }
    }

    public Picture(Picture var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("constructor argument is null");
        } else {
            this.width = var1.width();
            this.height = var1.height();
            this.image = new BufferedImage(this.width, this.height, 1);
            this.filename = var1.filename;
            this.isOriginUpperLeft = var1.isOriginUpperLeft;

            for(int var2 = 0; var2 < this.width(); ++var2) {
                for(int var3 = 0; var3 < this.height(); ++var3) {
                    this.image.setRGB(var2, var3, var1.image.getRGB(var2, var3));
                }
            }

        }
    }

    public Picture(String var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("constructor argument is null");
        } else {
            this.filename = var1;

            try {
                File var2 = new File(var1);
                if (var2.isFile()) {
                    this.image = ImageIO.read(var2);
                } else {
                    URL var3 = this.getClass().getResource(this.filename);
                    if (var3 == null) {
                        var3 = this.getClass().getClassLoader().getResource(var1);
                    }

                    if (var3 == null) {
                        var3 = new URL(var1);
                    }

                    this.image = ImageIO.read(var3);
                }

                if (this.image == null) {
                    throw new IllegalArgumentException("could not read image: " + var1);
                } else {
                    this.width = this.image.getWidth((ImageObserver)null);
                    this.height = this.image.getHeight((ImageObserver)null);
                }
            } catch (IOException var4) {
                throw new IllegalArgumentException("could not open image: " + var1, var4);
            }
        }
    }

    public Picture(File var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("constructor argument is null");
        } else {
            try {
                this.image = ImageIO.read(var1);
            } catch (IOException var3) {
                throw new IllegalArgumentException("could not open file: " + String.valueOf(var1), var3);
            }

            if (this.image == null) {
                throw new IllegalArgumentException("could not read file: " + String.valueOf(var1));
            } else {
                this.width = this.image.getWidth((ImageObserver)null);
                this.height = this.image.getHeight((ImageObserver)null);
                this.filename = var1.getName();
            }
        }
    }

    public JLabel getJLabel() {
        if (this.image == null) {
            return null;
        } else {
            ImageIcon var1 = new ImageIcon(this.image);
            return new JLabel(var1);
        }
    }

    public void setOriginUpperLeft() {
        this.isOriginUpperLeft = true;
    }

    public void setOriginLowerLeft() {
        this.isOriginUpperLeft = false;
    }

    public void show() {
        if (this.frame == null) {
            this.frame = new JFrame();
            JMenuBar var1 = new JMenuBar();
            JMenu var2 = new JMenu("File");
            var1.add(var2);
            JMenuItem var3 = new JMenuItem(" Save...   ");
            var3.addActionListener(this);
            var3.setAccelerator(KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            var2.add(var3);
            this.frame.setJMenuBar(var1);
            this.frame.setContentPane(this.getJLabel());
            this.frame.setDefaultCloseOperation(2);
            if (this.filename == null) {
                this.frame.setTitle(this.width + "-by-" + this.height);
            } else {
                this.frame.setTitle(this.filename);
            }

            this.frame.setResizable(false);
            this.frame.pack();
            this.frame.setVisible(true);
        }

        this.frame.repaint();
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    private void validateRowIndex(int var1) {
        if (var1 < 0 || var1 >= this.height()) {
            int var10002 = this.height() - 1;
            throw new IllegalArgumentException("row index must be between 0 and " + var10002 + ": " + var1);
        }
    }

    private void validateColumnIndex(int var1) {
        if (var1 < 0 || var1 >= this.width()) {
            int var10002 = this.width() - 1;
            throw new IllegalArgumentException("column index must be between 0 and " + var10002 + ": " + var1);
        }
    }

    public Color get(int var1, int var2) {
        this.validateColumnIndex(var1);
        this.validateRowIndex(var2);
        int var3 = this.getRGB(var1, var2);
        return new Color(var3);
    }

    public int getRGB(int var1, int var2) {
        this.validateColumnIndex(var1);
        this.validateRowIndex(var2);
        return this.isOriginUpperLeft ? this.image.getRGB(var1, var2) : this.image.getRGB(var1, this.height - var2 - 1);
    }

    public void set(int var1, int var2, Color var3) {
        this.validateColumnIndex(var1);
        this.validateRowIndex(var2);
        if (var3 == null) {
            throw new IllegalArgumentException("color argument is null");
        } else {
            int var4 = var3.getRGB();
            this.setRGB(var1, var2, var4);
        }
    }

    public void setRGB(int var1, int var2, int var3) {
        this.validateColumnIndex(var1);
        this.validateRowIndex(var2);
        if (this.isOriginUpperLeft) {
            this.image.setRGB(var1, var2, var3);
        } else {
            this.image.setRGB(var1, this.height - var2 - 1, var3);
        }

    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (var1 == null) {
            return false;
        } else if (var1.getClass() != this.getClass()) {
            return false;
        } else {
            Picture var2 = (Picture)var1;
            if (this.width() != var2.width()) {
                return false;
            } else if (this.height() != var2.height()) {
                return false;
            } else {
                for(int var3 = 0; var3 < this.width(); ++var3) {
                    for(int var4 = 0; var4 < this.height(); ++var4) {
                        if (this.getRGB(var3, var4) != var2.getRGB(var3, var4)) {
                            return false;
                        }
                    }
                }

                return true;
            }
        }
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder();
        var1.append(this.width + "-by-" + this.height + " picture (RGB values given in hex)\n");

        for(int var2 = 0; var2 < this.height; ++var2) {
            for(int var3 = 0; var3 < this.width; ++var3) {
                boolean var4 = false;
                int var5;
                if (this.isOriginUpperLeft) {
                    var5 = this.image.getRGB(var3, var2);
                } else {
                    var5 = this.image.getRGB(var3, this.height - var2 - 1);
                }

                var1.append(String.format("#%06X ", var5 & 16777215));
            }

            var1.append("\n");
        }

        return var1.toString().trim();
    }

    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because pictures are mutable");
    }

    public void save(String var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("argument to save() is null");
        } else {
            this.save(new File(var1));
            this.filename = var1;
        }
    }

    public void save(File var1) {
        if (var1 == null) {
            throw new IllegalArgumentException("argument to save() is null");
        } else {
            this.filename = var1.getName();
            if (this.frame != null) {
                this.frame.setTitle(this.filename);
            }

            String var2 = this.filename.substring(this.filename.lastIndexOf(46) + 1);
            if (!"jpg".equalsIgnoreCase(var2) && !"png".equalsIgnoreCase(var2)) {
                System.out.println("Error: filename must end in .jpg or .png");
            } else {
                try {
                    ImageIO.write(this.image, var2, var1);
                } catch (IOException var4) {
                    var4.printStackTrace();
                }
            }

        }
    }

    public void actionPerformed(ActionEvent var1) {
        FileDialog var2 = new FileDialog(this.frame, "Use a .png or .jpg extension", 1);
        var2.setVisible(true);
        if (var2.getFile() != null) {
            String var10001 = var2.getDirectory();
            this.save(var10001 + File.separator + var2.getFile());
        }

    }

    public static void main(String[] var0) {
        Picture var1 = new Picture(var0[0]);
        System.out.printf("%d-by-%d\n", var1.width(), var1.height());
        var1.show();
    }
}
