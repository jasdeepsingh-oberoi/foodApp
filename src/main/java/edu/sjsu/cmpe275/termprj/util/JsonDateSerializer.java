package edu.sjsu.cmpe275.termprj.util;

/**
 * Created by wanghao on 5/1/16.
 */

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


    /**
     * Used to serialize Java.util.Date, which is not a common JSON
     * type, so we have to create a custom serialize method;.
     *
     * @author Loiane Groner
     * http://loianegroner.com (English)
     * http://loiane.com (Portuguese)
     */
    @Component
    public class JsonDateSerializer extends JsonSerializer<Date>{
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy  HH:mm a z");
        @Override
        public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
                throws IOException, JsonProcessingException {
            String formattedDate = dateFormat.format(date);
            gen.writeString(formattedDate);
        }
    }


