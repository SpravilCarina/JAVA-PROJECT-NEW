package tema2;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.type.*;

public class Json
{
    public static void scriereJson(Set<InstrumentMuzical> carti)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());

            mapper.writeValue(fisier_json, carti);
        }
        catch (Exception exception) { exception.printStackTrace(); }
    }

    public static Set<InstrumentMuzical> citireJson()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File fisier_json = new File("src/main/resources/instrumente.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());

            return mapper.readValue(fisier_json, new TypeReference<>(){});
        }
        catch (Exception exception) {}
        return new HashSet<>();
    }
}