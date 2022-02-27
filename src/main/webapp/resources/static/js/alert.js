window.setTimeout(function () {
  $(".alert").fadeTo(600, 0).slideUp(700, function () {
    $(this).remove();
  });
}, 4000);