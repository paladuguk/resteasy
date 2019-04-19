package com.kiran;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@ApplicationPath("/rest")
public class App extends Application {
	
	
	//private final Set<Class<?>> resourceSet = new LinkedHashSet<Class<?>>();

	public App() {
		//resourceSet.add(HelloWorld.class);
	}

//	public Set<Class<?>> getClasses() {
//		return resourceSet;
//	}

	
	public Set<Object> getSingletons() {
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		mapper.registerModule(new CustomJsonDeserializerModule());
		AnnotationIntrospector primary = new JaxbAnnotationIntrospector();
		AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
		AnnotationIntrospector pair = AnnotationIntrospector.pair(primary, secondary);
		mapper.setAnnotationIntrospector(pair);
		
		
		JacksonJaxbJsonProvider jaxbProvider = new JacksonJaxbJsonProvider();
		jaxbProvider.setMapper(mapper);
		
		HashSet<Object> set = new HashSet<>();
		//set.add(jaxbProvider);
		return set;
	}

}


class CustomJsonDeserializerModule extends SimpleModule {
    

    public CustomJsonDeserializerModule() {
        addDeserializer(String.class, new StdScalarDeserializer<String>(String.class) {

            @Override
            public String deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException,
                    JsonProcessingException {
            	System.out.println(arg0);
                return StringUtils.trim((arg0.getText()));
            }
        });
    }

}
