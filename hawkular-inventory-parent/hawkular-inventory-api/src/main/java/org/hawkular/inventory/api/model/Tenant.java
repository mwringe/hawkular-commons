/*
 * Copyright 2014-2017 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.inventory.api.model;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.hawkular.inventory.paths.CanonicalPath;
import org.hawkular.inventory.paths.SegmentType;

import io.swagger.annotations.ApiModel;

/**
 * A tenant is a top level entity that owns everything else. Multiple tenants are not supposed to share anything between
 * each other.
 *
 * <p>Note that the tenant does not have a dedicated blueprint type (i.e. data required to create a new tenant
 * in some context), because the only data needed to create a new tenant is its ID, which can easily be modelled
 * by a {@code String}.
 *
 * @author Lukas Krejci
 * @since 0.0.1
 */
@ApiModel(description = "The tenants partition the data in the inventory graph." +
        " No relationships between entities from 2 different tenants can exist.",
        parent = Entity.class)
public final class Tenant extends Entity {

    public static final SegmentType SEGMENT_TYPE = SegmentType.t;

    public Tenant(CanonicalPath path) {
        super(path);
    }

    public Tenant(String name, CanonicalPath path) {
        super(name, path);
    }

    public Tenant(CanonicalPath path, Map<String, Object> properties) {
        super(path, properties);
    }

    public Tenant(String name, CanonicalPath path, Map<String, Object> properties) {
        super(name, path, properties);
    }

    @Override
    public <R, P> R accept(ElementVisitor<R, P> visitor, P parameter) {
        return visitor.visitTenant(this, parameter);
    }

    @ApiModel("TenantBlueprint")
    public static final class Blueprint extends Entity.Blueprint {

        public static Builder builder() {
            return new Builder();
        }

        //JAXB support
        @SuppressWarnings("unused")
        private Blueprint() {
        }

        public Blueprint(String id) {
            super(id, Collections.emptyMap());
        }

        public Blueprint(String id, Map<String, Object> properties) {
            super(id, properties);
        }

        public Blueprint(String id, Map<String, Object> properties,
                         Map<String, Set<CanonicalPath>> outgoing,
                         Map<String, Set<CanonicalPath>> incoming) {
            super(id, properties, outgoing, incoming);
        }

        public Blueprint(String id, String name, Map<String, Object> properties,
                         Map<String, Set<CanonicalPath>> outgoing,
                         Map<String, Set<CanonicalPath>> incoming) {
            super(id, name, properties, outgoing, incoming);
        }

        @Override
        public <R, P> R accept(ElementBlueprintVisitor<R, P> visitor, P parameter) {
            return visitor.visitTenant(this, parameter);
        }

        public static final class Builder extends Entity.Blueprint.Builder<Blueprint, Builder> {

            @Override
            public Blueprint build() {
                return new Blueprint(id, name, properties, outgoing, incoming);
            }
        }
    }
}
