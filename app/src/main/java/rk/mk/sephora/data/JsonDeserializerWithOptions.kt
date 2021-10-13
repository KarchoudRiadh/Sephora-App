package rk.mk.sephora.data

import androidx.annotation.NonNull
import com.google.gson.*
import java.lang.reflect.Field
import java.lang.reflect.Type


class JsonDeserializerWithOptions<T> : JsonDeserializer<T> {
    /**
     * To mark required fields of the model:
     * json parsing will be failed if these fields won't be provided.
     */
    @Retention(AnnotationRetention.RUNTIME) // to make reading of this field possible at the runtime
    @Target(AnnotationTarget.FIELD) // to make annotation accessible throw the reflection
    annotation class FieldRequired

    /**
     * Called when the model is being parsed.
     *
     * @param je   Source json string.
     * @param type Object's model.
     * @param jdc  Unused in this case.
     *
     * @return Parsed object.
     *
     * @throws JsonParseException When parsing is impossible.
     */
    @Throws(JsonParseException::class)
    override fun deserialize(je: JsonElement?, type: Type?, jdc: JsonDeserializationContext?): T {
        // Parsing object as usual.
        val pojo: T = Gson().fromJson(je, type)

        // Getting all fields of the class and checking if all required ones were provided.
        checkRequiredFields(pojo!!::class.java.declaredFields, pojo)

        // Checking if all required fields of parent classes were provided.
        checkSuperClasses(pojo)

        // All checks are ok.
        return pojo
    }

    /**
     * Checks whether all required fields were provided in the class.
     *
     * @param fields Fields to be checked.
     * @param pojo   Instance to check fields in.
     *
     * @throws JsonParseException When some required field was not met.
     */
    @Throws(JsonParseException::class)
    private fun checkRequiredFields(@NonNull fields: Array<Field>, @NonNull pojo: Any) {
        // Checking nested list items too.
        if (pojo is List<*>) {
            for (pojoListPojo in pojo) {
                checkRequiredFields(pojoListPojo!!.javaClass.declaredFields, pojoListPojo)
                checkSuperClasses(pojoListPojo)
            }
        }
        for (f in fields) {
            // If some field has required annotation.
            if (f.getAnnotation(FieldRequired::class.java) != null) {
                try {
                    // Trying to read this field's value and check that it truly has value.
                    f.isAccessible = true
                    val fieldObject: Any = f.get(pojo)
                    if (fieldObject == null) {
                        // Required value is null - throwing error.
                        throw JsonParseException(
                            String.format(
                                "%1\$s -> %2\$s",
                                pojo.javaClass.simpleName,
                                f.name
                            )
                        )
                    } else {
                        checkRequiredFields(fieldObject.javaClass.declaredFields, fieldObject)
                        checkSuperClasses(fieldObject)
                    }
                } // Exceptions while reflection.
                catch (e: IllegalArgumentException) {
                    throw JsonParseException(e)
                } catch (e: IllegalAccessException) {
                    throw JsonParseException(e)
                }
            }
        }
    }

    /**
     * Checks whether all super classes have all required fields.
     *
     * @param pojo Object to check required fields in its superclasses.
     *
     * @throws JsonParseException When some required field was not met.
     */
    @Throws(JsonParseException::class)
    private fun checkSuperClasses(@NonNull pojo: Any) {
        var superclass: Class<*> = pojo.javaClass
        while (superclass.superclass.also { superclass = it } != null) {
            checkRequiredFields(superclass.declaredFields, pojo)
        }
    }
}