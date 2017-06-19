package com.classified;

import com.classified.controller.CategoryController;
import com.classified.controller.MessageController;
import com.classified.controller.PostController;
import com.classified.controller.UserController;
import com.classified.model.UserPreference;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( UserController.class );
        h.add( PostController.class );
        h.add( CategoryController.class );
        h.add( MessageController.class );
        h.add( UserPreference.class );
        return h;
    }
}