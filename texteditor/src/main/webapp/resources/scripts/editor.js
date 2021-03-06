// editor.js - editor control script
// Copyright (C) 2018 Kaz Nishimura
//
// Copying and distribution of this file, with or without modification, are
// permitted in any medium without royalty provided the copyright notice and
// this notice are preserved.  This file is offered as-is, without any warranty.

"use strict";

((textAreaId, scriptId) => {
    let activate = () => {
        const CodeMirror = window.CodeMirror;

        let textArea = document.getElementById(textAreaId);
        if (textArea != null) {
            window.editor = CodeMirror.fromTextArea(textArea, {
                });
        }
    };

    // To cope with asynchronous script loading.
    if ('CodeMirror' in window) {
        activate();
    }
    else {
        let script = document.getElementById(scriptId);
        script.addEventListener("load", () => {
                activate();
            });
    }
})("editor-text", "codemirror-script");
