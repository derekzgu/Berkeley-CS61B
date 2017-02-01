/**
 * Created by pwd on 2017/1/31.
 */
public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet planet) {
        xxPos = planet.xxPos;
        yyPos = planet.yyPos;
        xxVel = planet.xxVel;
        yyVel = planet.yyVel;
        mass = planet.mass;
        imgFileName = planet.imgFileName;
    }

    public double calcDistance(Planet P) {
        return Math.sqrt(Math.pow((xxPos - P.xxPos), 2) + Math.pow((yyPos - P.yyPos), 2));
    }

    public static final double G = 6.67e-11; // gravitational constant G

    public double calcForceExertedBy(Planet P) {
        return G * mass * P.mass / Math.pow(calcDistance(P), 2);
    }

    public double calcForceExertedByX(Planet P) {
        return calcForceExertedBy(P) * (P.xxPos - xxPos) / calcDistance(P);
    }

    public double calcForceExertedByY(Planet P) {
        return calcForceExertedBy(P) * (P.yyPos - yyPos) / calcDistance(P);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        if (allPlanets == null) {
            throw new NullPointerException();
        }
        double netForceX = 0;

        for (Planet planet : allPlanets) {
            if (!this.equals(planet)) {
                netForceX += calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        if (allPlanets == null) {
            throw new NullPointerException();
        }
        double netForceY = 0;
        for (Planet planet : allPlanets) {
            if (!this.equals(planet)) {
                netForceY += calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aNetX = fX / mass;
        double aNetY = fY / mass;

        xxVel += dt * aNetX;
        yyVel += dt * aNetY;

        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }

}
