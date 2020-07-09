package game;

import geomtry.Line;
import geomtry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type levels.GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> listOfCollidable;

    /**
     * Instantiates a new levels.GameLevel environment.
     *
     * @param listOfCollidable the list of collidable
     */
    public GameEnvironment(List<Collidable> listOfCollidable) {
        this.listOfCollidable = listOfCollidable;
    }

    /**
     * Instantiates a new levels.GameLevel environment.
     */
    public GameEnvironment() {
        this.listOfCollidable = new ArrayList<>();
    }

    /**
     * Add collidable.
     * add the given collidable to the environment.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.listOfCollidable.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.listOfCollidable.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> listOfCollidable1 = new ArrayList<>(this.listOfCollidable);
        CollisionInfo closestObject;
        List<CollisionInfo> collisionInfoList = new ArrayList<>();
        for (Collidable obstcale : listOfCollidable1) {
            Point p = trajectory.closestIntersectionToStartOfLine(obstcale.getCollisionRectangle());
            if (p != null) {
                collisionInfoList.add(new CollisionInfo(p, obstcale));
            }
        }
        if (collisionInfoList.isEmpty()) {
            return null;
        } else if (collisionInfoList.size() == 1) {
            return collisionInfoList.get(0);
        } else {
            closestObject = collisionInfoList.get(0);
            Point fixedPoint = trajectory.start();
            for (CollisionInfo info : collisionInfoList) {
                if (info.collisionPoint().distance(fixedPoint) < closestObject.collisionPoint().distance(fixedPoint)) {
                    closestObject = info;
                }
            }
            return closestObject;
        }
    }
}