package com.scrapy.common.log;

import com.scrapy.common.util.CommonUtil;
import com.scrapy.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);
    public static void trace(String pattern, Object ... args) {
        logger.trace(pattern, parameters(args));
    }

    public static void debug(String pattern, Object ... args) {
        logger.debug(pattern, parameters(args));
    }
    public static void info(String pattern, Object ... args) {
        logger.info(pattern, parameters(args));
    }

    public static void warn(String pattern, Object ... args) {
        logger.warn(pattern, parameters(args));
    }
    public static void error(String pattern, Object ... args) {
        logger.error(pattern, parameters(args));
    }


    protected static String getThreadId() {
        String threadId = String.valueOf(Thread.currentThread().getId());
        return threadId;
    }

    private static Object[] parameters(Object[] args) {

        int idx = 0;
        Object[] parameters = new Object[args.length + 1];

        try {
            //parameters[idx] = getThreadId();

            for (Object arg : args) {
                parameters[idx++] = BeanUtils.isSimpleValueType(arg.getClass()) ? arg : JsonUtil.toJson(arg);
            }

        } catch (Exception e) {
            logger.error("Log output error occurred, e : {}", e.getMessage());
        }

        return parameters;
    }

}
