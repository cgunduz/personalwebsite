package com.cemgunduz.pw.model.constants;

/**
 * Created by cgunduz on 9/8/14.
 */
public enum Category {

    ALL,
    OTHER,
    TECHNOLOGY,
    PROGRAMMING,
    JAVA,
    FUTURE_TRENDS,
    SPACE;

    @Override
    public String toString()
    {
        return this.name().replace('_',' ');
    }
}
