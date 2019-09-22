(function($) {
  $(document).ready(function() {
    $('[data-widget="delete"]').on('click', function() {
      if (confirm('Are you sure?') === false) {
        return false;
      }
      return true;
    });
  });
})(jQuery);
