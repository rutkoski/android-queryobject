package com.devcafe.db;

import java.util.ArrayList;

import android.text.TextUtils;

public class QueryObject {

    protected ArrayList<String> fields = new ArrayList<String>();

    protected ArrayList<String> tables = new ArrayList<String>();

    protected ArrayList<String> joins = new ArrayList<String>();

    protected ArrayList<String> conditions = new ArrayList<String>();

    protected ArrayList<String> groupBy = new ArrayList<String>();

    protected ArrayList<String> having = new ArrayList<String>();

    protected ArrayList<String> orderBy = new ArrayList<String>();

    protected int limit = 0;

    protected int offset = 0;

    public static QueryObject query() {
        return new QueryObject();
    }

    public QueryObject select(String field) {
        if (!fields.contains(field)) {
            fields.add(field);
        }

        return this;
    }

    public QueryObject from(String table) {
        if (!tables.contains(table)) {
            tables.add(table);
        }

        return this;
    }

    public QueryObject join(String op, String join) {
        join = op + " " + join;

        if (!joins.contains(join)) {
            joins.add(join);
        }

        return this;
    }

    public QueryObject leftJoin(String join) {
        return join("LEFT JOIN", join);
    }

    public QueryObject innerJoin(String join) {
        return join("INNER JOIN", join);
    }

    public QueryObject where(String condition) {
        if (!conditions.contains(condition)) {
            conditions.add(condition);
        }

        return this;
    }

    public QueryObject groupBy(String s) {
        if (!groupBy.contains(s)) {
            groupBy.add(s);
        }

        return this;
    }

    public QueryObject having(String s) {
        if (!having.contains(s)) {
            having.add(s);
        }

        return this;
    }

    public QueryObject orderBy(String s) {
        if (!orderBy.contains(s)) {
            orderBy.add(s);
        }

        return this;
    }

    public QueryObject limit(int limit) {
        this.limit = limit;
        return this;
    }

    public QueryObject offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String buildQuery() {
        String q = "";

        q += "SELECT ";

        if (!fields.isEmpty()) {
            q += TextUtils.join(", ", fields) + " ";
        } else {
            q += "* ";
        }

        q += "FROM ";
        q += TextUtils.join(", ", tables) + " ";

        q += TextUtils.join(" ", joins) + " ";

        if (!conditions.isEmpty()) {
            q += "WHERE ";
            q += TextUtils.join(" ", conditions) + " ";
        }

        if (!groupBy.isEmpty()) {
            q += "GROUP BY ";
            q += TextUtils.join(", ", groupBy) + " ";

            if (!having.isEmpty()) {
                q += "HAVING ";
                q += TextUtils.join(" ", having) + " ";
            }
        }

        if (limit > 0) {
            if (offset > 0) {
                q += "LIMIT " + offset + ", " + limit;
            } else {
                q += "LIMIT " + limit;
            }
        }

        return q;
    }

    @Override
    public String toString() {
        return buildQuery();
    }

}
