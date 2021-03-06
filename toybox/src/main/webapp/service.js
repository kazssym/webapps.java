// service.js
// Copyright (C) 2020 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

"use strict";

const CACHE_NAME = "1.0";

self.addEventListener("install", (event) => {
    event.waitUntil(caches.open(CACHE_NAME)
        .then((cache) => {
            return cache.addAll([
                "service.js",
                "resources/start.js",
                "index.jsf",
                "ws/index.jsf",
                "resources/site.css",
                "resources/ws/init.js",
            ]);
        }));
});
