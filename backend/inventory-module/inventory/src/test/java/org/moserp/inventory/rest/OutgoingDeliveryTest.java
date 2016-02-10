/*******************************************************************************
 * Copyright 2013 Thomas Letsch (contact@thomas-letsch.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.moserp.inventory.rest;

import org.junit.Before;
import org.junit.Test;
import org.moserp.common.domain.Quantity;
import org.moserp.common.domain.RestUri;
import org.moserp.inventory.domain.DeliveryItem;
import org.moserp.inventory.domain.OutgoingDelivery;
import org.moserp.product.domain.ProductInstance;
import org.moserp.inventory.repository.DeliveryUtil;
import org.moserp.inventory.repository.InventoryItemUtil;
import org.moserp.inventory.repository.OutgoingDeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OutgoingDeliveryTest extends BaseWebInventoryTest {

    @Autowired
    private InventoryItemUtil inventoryUtil;

    @Autowired
    private DeliveryUtil deliveryUtil;

    private RestUri facility;

    private RestUri product;

    @Before
    public void setup() {
        facility = moduleRegistry.getBaseUriForResource("facilities").slash("1");
        product = moduleRegistry.getBaseUriForResource("products").slash("1");
        inventoryUtil.setupOneInventoryItem(product, facility);
        deliveryUtil.setup();
    }

    @Test
    public void testOutgoingDelivery() {
        DeliveryItem item = new DeliveryItem(new ProductInstance(product), new Quantity(55));
        OutgoingDelivery outgoingDelivery = new OutgoingDelivery(facility, item);

        restTemplate.postForLocation(testEnvironment.createRestUri(OutgoingDeliveryRepository.URL), outgoingDelivery);
        inventoryUtil.checkInventory(product, facility, 45);
    }

}
