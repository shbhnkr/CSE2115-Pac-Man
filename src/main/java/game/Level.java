package game;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Level {

    public transient int width;
    public transient int height;

    public transient Wall[][] walls;
    public transient Pellet[][] pellets;

    public Level(URL path) {
        this.width = 20;
        this.height = 15;
        char pixels[][] = new char[20][15];
        System.out.println(path.getFile());
        File file = new File(path.getFile());
        Scanner sc;
        try {
            sc = new Scanner(file);

            int n = 0;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s + " number " + n);
                for (int i = 0; i < s.length(); i++) {

                    pixels[i][n] = s.charAt(i);
                }
                n++;
            }
            walls = new Wall[20][15];
            pellets = new Pellet[20][15];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    switch (pixels[x][y]) {
                        case '#':
                            walls[x][y] = new Wall(x * 20, y * 20);
                            break;
                        case '.':
                            pellets[x][y] = new Pellet(x * 20, y * 20);
                            break;
                        case ' ':
                            System.out.println("nothing");
                            break;
                        default:
                            System.out.println("invalid");
                            break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    public void render(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (walls[x][y] != null)
                    walls[x][y].render(g);

                if (pellets[x][y] != null)
                    pellets[x][y].render(g);
            }
        }
    }
}

