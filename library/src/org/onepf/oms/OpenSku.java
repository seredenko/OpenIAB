/*******************************************************************************
 * Copyright 2013 One Platform Foundation
 *
 *       Licensed under the Apache License, Version 2.0 (the "License");
 *       you may not use this file except in compliance with the License.
 *       You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *       Unless required by applicable law or agreed to in writing, software
 *       distributed under the License is distributed on an "AS IS" BASIS,
 *       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *       See the License for the specific language governing permissions and
 *       limitations under the License.
 ******************************************************************************/

package org.onepf.oms;

/**
 * User: Boris Minaev
 * Date: 21.04.13
 * Time: 21:34
 */

import java.util.EnumMap;
import java.util.Map;

public class OpenSku {
    private Map<AppstoreType, String> storeToSku;

    public static class Sku {
        AppstoreType appstore;
        String sku;

        public Sku(AppstoreType appstore, String sku) {
            this.appstore = appstore;
            this.sku = sku;
        }
    }

    public OpenSku(Sku... skus) {
        storeToSku = new EnumMap<AppstoreType, String>(AppstoreType.class);
        for (Sku sku : skus) {
            storeToSku.put(sku.appstore, sku.sku);
        }
    }

    public String getSku(AppstoreType appstore) {
        return storeToSku.get(appstore);
    }

    public boolean sameAs(String someMarketSku) {
        for (AppstoreType appstore : storeToSku.keySet()) {
            String skuForAppstore = storeToSku.get(appstore);
            if (skuForAppstore.equals(someMarketSku))
                return true;
        }
        return false;
    }

    public boolean sameAs(String someMarketSku, AppstoreType appstore) {
        String skuForAppstore = storeToSku.get(appstore);
        return skuForAppstore == null || !skuForAppstore.equals(someMarketSku) ? false : true;
    }
}
