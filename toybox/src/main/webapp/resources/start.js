// init.js
// Copyright (C) 2020 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

// This file is a module script and shall be in strict mode by default.

let url = new URL(import.meta.url);
let context = url.hash.substring(1);

navigator.serviceWorker.register(`${context}/service.js`)
    .then((registration) => {
        console.debug("registered service worker: %o", registration);
    })
    .catch((reason) => {
        console.log(reason);
    });
