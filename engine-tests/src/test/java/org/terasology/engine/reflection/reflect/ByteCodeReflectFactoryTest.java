// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.engine.reflection.reflect;

import org.junit.jupiter.api.Test;
import org.terasology.engine.entitySystem.stubs.GetterSetterComponent;
import org.terasology.engine.entitySystem.stubs.IntegerComponent;
import org.terasology.engine.entitySystem.stubs.StringComponent;
import org.terasology.engine.logic.characters.events.AttackRequest;
import org.terasology.engine.logic.location.LocationComponent;
import org.terasology.math.geom.Vector3f;
import org.terasology.nui.reflection.reflect.FieldAccessor;
import org.terasology.nui.reflection.reflect.ObjectConstructor;
import org.terasology.nui.reflection.reflect.ReflectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 */
public class ByteCodeReflectFactoryTest {

    @Test
    public void testCreateConstructorObjectWithPublicConstructor() throws NoSuchMethodException {
        ReflectFactory reflectFactory = new ByteCodeReflectFactory();
        ObjectConstructor<LocationComponent> constructor = reflectFactory.createConstructor(LocationComponent.class);
        LocationComponent locationComponent = constructor.construct();
        assertNotNull(locationComponent);
    }

    @Test
    public void testCreateConstructorObjectWithProtectedConstructor() throws Exception {
        ReflectFactory reflectFactory = new ByteCodeReflectFactory();
        ObjectConstructor<AttackRequest> constructor = reflectFactory.createConstructor(AttackRequest.class);
        AttackRequest result = constructor.construct();
        assertNotNull(result);
    }

    @Test
    public void testCreateFieldAccessorWithGetterSetter() throws Exception {
        ReflectFactory reflectFactory = new ByteCodeReflectFactory();
        FieldAccessor<GetterSetterComponent, Vector3f> fieldAccessor
                = reflectFactory.createFieldAccessor(GetterSetterComponent.class, GetterSetterComponent.class.getDeclaredField("value"), Vector3f.class);
        GetterSetterComponent comp = new GetterSetterComponent();
        Vector3f newVal = new Vector3f(1, 2, 3);
        fieldAccessor.setValue(comp, newVal);
        assertTrue(comp.setterUsed);

        assertEquals(newVal, fieldAccessor.getValue(comp));
        assertTrue(comp.getterUsed);
    }

    @Test
    public void testCreateFieldAccessorDirectToField() throws Exception {
        ReflectFactory reflectFactory = new ByteCodeReflectFactory();
        FieldAccessor<StringComponent, String> fieldAccessor
                = reflectFactory.createFieldAccessor(StringComponent.class, StringComponent.class.getDeclaredField("value"), String.class);
        StringComponent comp = new StringComponent();
        fieldAccessor.setValue(comp, "String");
        assertEquals("String", fieldAccessor.getValue(comp));
    }

    @Test
    public void testAccessIntegerField() throws Exception {
        ReflectFactory reflectFactory = new ByteCodeReflectFactory();
        FieldAccessor fieldAccessor
                = reflectFactory.createFieldAccessor(IntegerComponent.class, IntegerComponent.class.getDeclaredField("value"));
        IntegerComponent comp = new IntegerComponent();
        fieldAccessor.setValue(comp, 1);
        assertEquals(1, fieldAccessor.getValue(comp));
    }

}