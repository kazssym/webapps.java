/*
  layout.js - script to adjust the layout
  Copyright (C) 2017 Kaz Nishimura

  Copying and distribution of this file, with or without modification, are
  permitted in any medium without royalty provided the copyright notice and
  this notice are preserved.  This file is offered as-is, without any warranty.
*/

(function () {
  "use strict";

  var fill = function () {
    var htmlRect = document.documentElement.getBoundingClientRect();
    var height = htmlRect.bottom - htmlRect.top;

    var top = document.getElementById("top");
    var topRect = top.getBoundingClientRect();
    height -= topRect.bottom - topRect.top;

    var content = document.getElementById("content");
    content.style.height = height + "px";
  };

  window.addEventListener("resize", function (/*event*/) {
    fill();
  });

  fill();
})();
