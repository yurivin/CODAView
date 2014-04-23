package repository;

import android.content.Context;

/**
 * Created by Юрий on 20.04.2014.
 */
public interface PropertiesRepository {

    String find(String key, Context context);
}
