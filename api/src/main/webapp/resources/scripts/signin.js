// signin.js - sign-in control script
// Copyright (C) 2018 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

"use strict";

((scriptId) => {
    let once = false;
    const init = () => {
        if (!once) {
            once = true;
            // Here goes initialization.
        }
    };

    let script = document.getElementById(scriptId);
    script.addEventListener("load", function onLoad() {
            init();
        });
    if ("gapi" in window) {
        init();
    }
})("gapi-script");
