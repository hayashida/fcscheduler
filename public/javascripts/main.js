(function($) {
  $(document).ready(function() {
    $('.btn-danger').on('click', function() {
      if (confirm('Are you sure?') === false) {
        return false;
      }
      return true;
    });
  });
})(jQuery);
