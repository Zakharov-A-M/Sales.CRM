package com.andersenlab.crm.configuration.sqlformatter;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.hibernate.engine.jdbc.internal.Formatter;

/**
 * p6spy log formatter
 */
public class SimpleFormatter implements MessageFormattingStrategy {

    private static final Formatter HIBERNATE_SQL_FORMATTER = new BasicFormatterImpl();

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        if (sql.isEmpty()) {
            return "";
        }
        String batch = "batch".equals(category) ? " add to batch " : "";
        return String.format("Hibernate: %s %s {elapsed: %dms}", batch, HIBERNATE_SQL_FORMATTER.format(sql), elapsed);
    }

}