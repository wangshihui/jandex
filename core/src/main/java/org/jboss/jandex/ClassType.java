/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.jandex;

/**
 * Represents a standard raw class name.
 *
 * @author Jason T. Greene
 */
public final class ClassType extends Type {

    public static final ClassType OBJECT_TYPE = new ClassType(DotName.OBJECT_NAME);

    /**
     * Create an instance of a class type with given {@code name}.
     * <p>
     * Note that an inner class type enclosed in a parameterized type or in a type
     * annotated with a type annotation is represented as {@link ParameterizedType},
     * where the enclosing type is the owner of the parameterized type.
     *
     * @param name the name of this class type
     * @return the class type
     * @since 3.0.4
     */
    public static ClassType create(DotName name) {
        return new ClassType(name);
    }

    ClassType(DotName name) {
        this(name, null);
    }

    ClassType(DotName name, AnnotationInstance[] annotations) {
        super(name, annotations);
    }

    @Override
    public Kind kind() {
        return Kind.CLASS;
    }

    @Override
    public ClassType asClassType() {
        return this;
    }

    @Override
    Type copyType(AnnotationInstance[] newAnnotations) {
        return new ClassType(name(), newAnnotations);
    }

    ParameterizedType toParameterizedType() {
        return new ParameterizedType(name(), null, null, annotationArray());
    }
}
