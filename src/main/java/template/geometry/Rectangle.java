package template.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 17-1-15.
 */
public class Rectangle {
    private final Point leftBottom, rightTop;
    private Point leftTop, rightBottom;

    public Rectangle(Point leftBottom, Point rightTop) {
        if (!(leftBottom.x < rightTop.x && leftBottom.y < rightTop.y)) throw new IllegalArgumentException();
        this.leftBottom = leftBottom;
        this.rightTop = rightTop;
    }

    public Point getLeftBottom() {
        return leftBottom;
    }

    public Point getRightTop() {
        return rightTop;
    }

    public Point getLeftTop() {
        if (leftTop == null) leftTop = new Point(leftBottom.x, rightTop.y);
        return leftTop;
    }

    public Point getRightBottom() {
        if (rightBottom == null) rightBottom = new Point(rightTop.x, leftBottom.y);
        return rightBottom;
    }

    public boolean overlapWith(Rectangle that) {
        boolean xo = !(rightTop.x <= that.leftBottom.x || that.rightTop.x <= leftBottom.x);
        boolean yo = !(rightTop.y <= that.leftBottom.y || that.rightTop.y <= leftBottom.y);
        return xo && yo;
    }

    public Rectangle overlap(Rectangle that) {
        double x1 = Math.max(leftBottom.x, that.leftBottom.x);
        double x2 = Math.min(rightTop.x, that.rightTop.x);
        double y1 = Math.max(leftBottom.y, that.leftBottom.y);
        double y2 = Math.min(rightTop.y, that.rightTop.y);
        return new Rectangle(new Point(x1, y1), new Point(x2, y2));
    }

    public boolean cover(Rectangle that) {
        boolean xc = leftBottom.x <= that.leftBottom.x && that.rightTop.x <= rightTop.x;
        boolean yc = leftBottom.y <= that.leftBottom.y && that.rightTop.y <= rightTop.y;
        return xc && yc;
    }


    public List<Rectangle> remove(Rectangle that) {
        List<Rectangle> res = new ArrayList<>();
        if (that.cover(this)) return res;
        if (!overlapWith(that)) {
            res.add(this);
            return res;
        }
        Rectangle removed = overlap(that);
        if (removed.getLeftTop().equals(getLeftTop()) && between(removed.getRightTop(), getLeftTop(), getRightTop()) && between(removed.getLeftBottom(), getLeftTop(), getLeftBottom())) {
            res.add(new Rectangle(removed.getRightBottom(), getRightTop()));
            res.add(new Rectangle(getLeftBottom(), new Point(getRightBottom().x, removed.getLeftBottom().y)));
            return res;
        }
        if (removed.getLeftTop().equals(getLeftTop()) && removed.getRightTop().equals(getRightTop()) && between(removed.getLeftBottom(), getLeftTop(), getLeftBottom()) && between(removed.getRightBottom(), getRightTop(), getRightBottom())) {
            res.add(new Rectangle(getLeftBottom(), removed.getRightBottom()));
            return res;
        }
        if (removed.getLeftTop().equals(getLeftTop()) && removed.getLeftBottom().equals(getLeftBottom()) && between(removed.getRightTop(), getLeftTop(), getRightTop()) && between(removed.getRightBottom(), getLeftBottom(), getRightBottom())) {
            res.add(new Rectangle(removed.getRightBottom(), getRightTop()));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftTop(), getRightTop()) && between(removed.getRightTop(), getLeftTop(), getRightTop()) && this.contains(removed.getLeftBottom())) {
            res.add(new Rectangle(getLeftBottom(), removed.getLeftTop()));
            res.add(new Rectangle(new Point(removed.getLeftBottom().x, getLeftBottom().y), removed.getRightBottom()));
            res.add(new Rectangle(new Point(removed.getRightBottom().x, getLeftBottom().y), getRightTop()));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftTop(), getRightTop()) && removed.getRightTop().equals(getRightTop()) && this.contains(removed.getLeftBottom())) {
            res.add(new Rectangle(getLeftBottom(), removed.getLeftTop()));
            res.add(new Rectangle(new Point(removed.getLeftBottom().x, getLeftBottom().y), removed.getRightBottom()));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftTop(), getRightTop()) && between(removed.getRightTop(), getLeftTop(), getRightTop()) && between(removed.getLeftBottom(), getLeftBottom(), getRightBottom())) {
            res.add(new Rectangle(getLeftBottom(), removed.getLeftTop()));
            res.add(new Rectangle(removed.getRightBottom(), getRightTop()));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftTop(), getRightTop()) && removed.getRightTop().equals(getRightTop()) && removed.getRightBottom().equals(getRightBottom())) {
            res.add(new Rectangle(getLeftBottom(), removed.getLeftTop()));
            return res;
        }

        if (between(removed.getLeftTop(), getLeftTop(), getLeftBottom()) && between(removed.getLeftBottom(), getLeftTop(), getLeftBottom()) && this.contains(removed.getRightTop())) {
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            res.add(new Rectangle(removed.getRightBottom(), new Point(getRightTop().x, removed.getRightTop().y)));
            res.add(new Rectangle(getLeftBottom(), new Point(getRightTop().x, removed.getRightBottom().y)));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftTop(), getLeftBottom()) && between(removed.getLeftBottom(), getLeftTop(), getLeftBottom()) && between(removed.getRightTop(), getRightBottom(), getRightTop())) {
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            res.add(new Rectangle(getLeftBottom(), removed.getRightBottom()));
            return res;
        }
        if (this.contains(removed.getLeftTop()) && this.contains(removed.getLeftBottom()) && this.contains(removed.getRightTop())) {
            res.add(new Rectangle(getLeftBottom(), new Point(removed.getLeftTop().x, getRightTop().y)));
            res.add(new Rectangle(removed.getLeftTop(), new Point(removed.getRightTop().x, getRightTop().y)));
            res.add(new Rectangle(new Point(removed.getLeftBottom().x, getLeftBottom().y), removed.getRightBottom()));
            res.add(new Rectangle(new Point(removed.getRightBottom().x, getRightBottom().y), getRightTop()));
            return res;
        }
        if (this.contains(removed.getLeftTop()) && this.contains(removed.getLeftBottom()) && between(removed.getRightTop(), getRightBottom(), getRightTop())) {
            res.add(new Rectangle(getLeftBottom(), new Point(removed.getLeftTop().x, getRightTop().y)));
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            res.add(new Rectangle(new Point(removed.getLeftBottom().x, getLeftBottom().y), removed.getRightBottom()));
            return res;
        }

        if (between(removed.getLeftTop(), getLeftBottom(), getLeftTop()) && removed.getLeftBottom().equals(getLeftBottom()) && this.contains(removed.getRightTop())) {
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            res.add(new Rectangle(removed.getRightBottom(), new Point(getRightBottom().x, removed.getRightTop().y)));
            return res;
        }
        if (between(removed.getLeftTop(), getLeftBottom(), getLeftTop()) && removed.getLeftBottom().equals(getLeftBottom()) && removed.getRightBottom().equals(getRightBottom())) {
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            return res;
        }
        if (this.contains(removed.getLeftTop()) && this.contains(removed.getRightTop()) && between(removed.getLeftBottom(), getLeftBottom(), getRightBottom())) {
            res.add(new Rectangle(getLeftBottom(), new Point(removed.getLeftBottom().x, getRightTop().y)));
            res.add(new Rectangle(removed.getLeftTop(), new Point(removed.getRightTop().x, getRightTop().y)));
            res.add(new Rectangle(removed.getRightBottom(), getRightTop()));
            return res;
        }
        if (this.contains(removed.getLeftTop()) && between(removed.getLeftBottom(), getLeftBottom(), getRightBottom()) && removed.getRightBottom().equals(getRightBottom())) {
            res.add(new Rectangle(getLeftBottom(), new Point(removed.getLeftBottom().x, getRightTop().y)));
            res.add(new Rectangle(removed.getLeftTop(), getRightTop()));
            return res;
        }
        throw new RuntimeException();
    }

    public double area() {
        return (rightTop.x - leftBottom.x) * (rightTop.y - leftBottom.y);
    }

    public boolean between(Point a, Point b, Point c) {
        if (b.equals(c)) throw new IllegalArgumentException();
        if (b.y == c.y && c.x < b.x) {
            Point swap = b;
            b = c;
            c = swap;
        }
        if (b.x == c.x && c.y > b.y) {
            Point swap = b;
            b = c;
            c = swap;
        }
        return  b.y == a.y && a.y == c.y && b.x < a.x && a.x < c.x ||
                b.x == a.x && a.x == c.x && c.y < a.y && a.y < b.y;
    }

    public boolean contains(Point a, boolean strict) {
        if (strict) return leftBottom.x < a.x && a.x < rightTop.x && leftBottom.y < a.y && a.y < rightTop.y;
        return leftBottom.x <= a.x && a.x <= rightTop.x && leftBottom.y <= a.y && a.y <= rightTop.y;
    }

    public boolean contains(Point a) {
        return contains(a, true);
    }
}

