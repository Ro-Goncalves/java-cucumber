package shouty.support;

import io.cucumber.java.ParameterType;
import shouty.Person;

public class PersonParameterTypes {
    private ShoutyWorld world;

    public PersonParameterTypes(ShoutyWorld world){
        this.world = world;
    }
    
    @ParameterType("Lucy|Sean|Larry")
    public Person person(String name){
        if(world.people.containsKey(name)){
            return world.people.get(name);
        }
        
        Person person = new Person(name, world.network, 0);
        world.people.put(name, person);
        return person;
    }
}
