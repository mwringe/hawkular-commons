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

import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Joel Takvorian
 */
public class ExtendedInventoryStructure {
    @JsonProperty("inventoryStructure")
    private final InventoryStructure<?> structure;
    @JsonProperty("typesIndex")
    private final Map<String, Collection<String>> typesIndex;
    @JsonProperty("metricTypesIndex")
    private final Map<String, Collection<String>> metricTypesIndex;

    @JsonCreator
    public ExtendedInventoryStructure(@JsonProperty("inventoryStructure") InventoryStructure<?> structure,
                                      @JsonProperty("typesIndex") Map<String, Collection<String>> typesIndex,
                                      @JsonProperty("metricTypesIndex") Map<String, Collection<String>> metricTypesIndex) {
        this.structure = structure;
        this.typesIndex = typesIndex;
        this.metricTypesIndex = metricTypesIndex;
    }

    public ExtendedInventoryStructure(InventoryStructure<?> structure) {
        this.structure = structure;
        this.typesIndex = null;
        this.metricTypesIndex = null;
    }

    public InventoryStructure<?> getStructure() {
        return structure;
    }

    public Map<String, Collection<String>> getTypesIndex() {
        return typesIndex;
    }

    public Map<String, Collection<String>> getMetricTypesIndex() {
        return metricTypesIndex;
    }
}
