// signin.js - sign-in control script
// Copyright (C) 2018 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

"use strict";

((scriptId) => {
    const init = () => {
        // Here goes initialization.
    };

    // The 'init' function shall be invoked later if the API script has not
    // been loaded yet.
    if ("gapi" in window) {
        init();
    }
    else {
        let script = document.getElementById(scriptId);
        script.addEventListener("load", function onLoad() {
                init();
            });
    }
})("gapi-script");
