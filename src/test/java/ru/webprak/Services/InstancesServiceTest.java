package ru.webprak.Services;

import ru.webprak.Models.*;
import org.junit.*;

import java.util.List;
import java.util.Set;

public class InstancesServiceTest {

    @Test
    public void testCreateInstance() {
        InstancesService instanceService = new InstancesService();
        Instances new_instance = new Instances(0, 1);
        instanceService.createInstance(new_instance);

        Instances check_instance = instanceService.readInstancesByID(new_instance.getInstance_id());

        Assert.assertEquals(new_instance, check_instance);

        instanceService.deleteInstance(new_instance);
    }

    @Test
    public void testDeleteIstance() {

    }

    @Test
    public void testUpdateInstance() {

    }

    @Test
    public void testReadInstanceByID() {

    }

    @Test
    public void testReadInstances() {

    }
}