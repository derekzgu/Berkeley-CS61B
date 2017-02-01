/**
 * Created by pwd on 2017/1/31.
 */
public class NBody {
    private static int numOfBody;
    private static double radius;

    public static double readRadius(String inPath) {
        In in = new In(inPath);
        numOfBody = in.readInt();
        radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String inPath) {
        In in = new In(inPath);
        numOfBody = in.readInt();
        radius = in.readDouble();
        Planet[] allPlanets = new Planet[numOfBody];
        for (int i = 0; i < numOfBody; ++i) {
            allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }
        return allPlanets;
    }


    public static void main(String[] args) {
        /* Collecting all needed input. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] allPlanets = readPlanets(filename);

        /* Drawing the background. */
        StdDraw.setScale(-radius, radius);
        String background = "./images/starfield.jpg";
        StdDraw.clear();
        StdDraw.picture(0, 0, background);

        /* Drawing all the planets. */
        for (Planet P : allPlanets) {
            P.draw();
        }
        StdDraw.show(10);

        /* Adding audio. */
        double[] audio = StdAudio.read("audio/2001.mid");
        StdAudio.play(audio);

        /* Creating an animation. */
        double time = 0;
        while (time <= T) {
            double xForces[] = new double[numOfBody];
            double yForces[] = new double[numOfBody];

            for (int i = 0; i < numOfBody; ++i) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i = 0; i < numOfBody; ++i) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Show update. */
            StdDraw.picture(0, 0, background);
            for (Planet P : allPlanets) {
                P.draw();
            }
            StdDraw.show(10);

            time += dt;
        }

        /* Printing the universe. */
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel, allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }

}
